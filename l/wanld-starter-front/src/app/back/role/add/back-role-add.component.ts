import { Component, EventEmitter, Injector, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ComponentBase } from 'src/app/_shared/components/component-base';
import { RoleService } from 'src/app/_shared/services/back/role.service';
import { Permission } from 'src/app/_shared/permission';
import { AppValidators } from 'src/app/_shared/utils/app-validators';

@Component({
    templateUrl: './back-role-add.component.html',
    selector: 'app-back-role-add'
})
export class BackRoleAddComponent extends ComponentBase implements OnInit {
    isVisible = false;
    @Output() modalSave: EventEmitter<any> = new EventEmitter<any>();

    permissions: any[] = Permission.getPermissions();
    formGroup: FormGroup;
    permissionNames;

    constructor(
        public roleService: RoleService,
        injector: Injector) {
        super(injector);
    }
    ngOnInit() {
        this.formGroup = new FormGroup({
            'name': new FormControl('', AppValidators.optional({ required: true, maxLength: 8, zhEnN_: true })),
            'value': new FormControl('', AppValidators.optional({ required: true, maxLength: 32, en_captical: true })),
            'description': new FormControl('', AppValidators.optional({ required: true, maxLength: 100 })),
            'isDisabled': new FormControl(true)
        });
    }

    get description() { return this.formGroup.get('description'); }
    get name() { return this.formGroup.get('name'); }
    get isDisabled() { return this.formGroup.get('isDisabled'); }

    public hideModal(): void {
        this.isVisible = false;
    }

    public showModal(): void {
        console.log(this.isVisible);

        //权限列表
        this.roleService.getPermissionTreeList().subscribe(result => {
            this.permissionNames = result.map(ele => ({
                key: ele.id,
                title: ele.name,
                children: ele.children.map(<Nodes>(e) => ({
                    //FIXME: 加入类型断言
                    key: e.id,
                    title: e.name
                }))
            }));
        });
        this.isVisible = true;
    }

    save() {
        this.formGroup.value['permissionIds'] = Permission.getCheckedPermissions(this.permissionNames);
        this.roleService.create(this.formGroup.value).subscribe(resp => {
            if (resp) {
                this.hideModal();
                this.modalSave.emit();
            }
        }, error => {
            this.nzMessageService.error(error.message);
        });
    }
}
