import { Component, EventEmitter, Injector, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { forkJoin } from 'rxjs';

import { NzTreeNodeOptions } from 'ng-zorro-antd';

import { Permission } from 'src/app/_shared/permission';
import { AppValidators } from 'src/app/_shared/utils/app-validators';
import { ComponentBase } from 'src/app/_shared/components/component-base';
import { RoleDto, RoleService } from 'src/app/_shared/services/back/role.service';

@Component({
    templateUrl: './back-role-edit.component.html',
    selector: 'app-back-role-edit'
})
export class BackRoleEditComponent extends ComponentBase implements OnInit {
    isVisible = false;
    @Output() modalSave: EventEmitter<any> = new EventEmitter<any>();
    permissions = Permission.getPermissions();

    roles: any[] = [];
    formGroup: FormGroup;
    editRole: RoleDto;
    permissionNames;
    rolePermissions;
    getPermission: any[] = [];

    constructor(public roleService: RoleService, injector: Injector) {
        super(injector);
    }

    ngOnInit() {
        this.formGroup = new FormGroup({
            name: new FormControl('', AppValidators.optional({ required: true, maxLength: 8, zhEnN_: true })),
            value: new FormControl('', AppValidators.optional({ required: true, maxLength: 32, en_captical: true })),
            description: new FormControl('', AppValidators.optional({ required: true, maxLength: 100 })),
            isDisabled: new FormControl(true)
        });
    }

    get description() {
        return this.formGroup.get('description');
    }
    get name() {
        return this.formGroup.get('name');
    }
    get isDisabled() {
        return this.formGroup.get('isDisabled');
    }

    public hideModal(): void {
        this.isVisible = false;
    }

    public showModal(item: RoleDto): void {
        this.editRole = item;
        this.formGroup.get('name').setValue(item.name);
        this.formGroup.get('value').setValue(item.value);
        this.formGroup.get('description').setValue(item.description);
        this.formGroup.get('isDisabled').setValue(item.isDisabled);

        forkJoin(this.roleService.getPermissionTreeList(), this.roleService.getRoleForEdit(item.id)).subscribe(
            arr => {
                const ps = arr[1].map(element => element.id);

                this.permissionNames = arr[0].map(ele => ({
                    key: ele.id,
                    title: ele.name,
                    checked: !!ps.find(or => or === ele.id),
                    children: ele.children.map(<Nodes>(e) => ({
                        key: e.id,
                        title: e.name,
                        isLeaf: true,
                        checked: !!ps.find(or => or === ele.id)
                    }))
                }));

                this.isVisible = true;
            },
            error => {
                this.nzMessageService.error('获取数据错误，无法进行编辑！');
            }
        );
    }

    save() {
        this.formGroup.value['id'] = this.editRole.id;
        this.formGroup.value['permissionIds'] = Permission.getCheckedPermissions(this.permissionNames);

        this.roleService.update(this.formGroup.value).subscribe(
            resp => {
                if (resp) {
                    this.hideModal();
                    this.modalSave.emit();
                }
            },
            error => {
                this.nzMessageService.error('更新角色信息失败！');
            }
        );
    }
}
