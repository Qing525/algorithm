import { Component, EventEmitter, Injector, OnInit, Output } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';

import { AppValidators } from 'src/app/_shared/utils/app-validators';
import { ComponentBase } from 'src/app/_shared/components/component-base';
import { UserDto, UserService } from 'src/app/_shared/services/back/user.service';

@Component({
    templateUrl: './back-user-edit.component.html',
    selector: 'app-back-user-edit'
})
export class BackUserEditComponent extends ComponentBase implements OnInit {
    isVisible = false;
    @Output() modalSave: EventEmitter<any> = new EventEmitter<any>();

    roles: any[] = [];
    formGroup: FormGroup;
    editUser: UserDto;
    userRoles: any[] = [];

    constructor(public userService: UserService, injector: Injector) {
        super(injector);
    }

    ngOnInit() {
        // 进入页面获得所有角色
        this.userService.getRoles().subscribe(resp => {
            this.roles = resp.map(a => {
                const newObj = {};
                newObj['name'] = a.name;
                newObj['id'] = a.id;
                newObj['description'] = a.description;
                return newObj;
            });
        });

        this.formGroup = new FormGroup({
            username: new FormControl('', [Validators.maxLength(32), AppValidators.username]),
            nickName: new FormControl('', [Validators.maxLength(8), AppValidators.nickName]),
            surname: new FormControl('', [Validators.maxLength(4), AppValidators.chinese]),
            name: new FormControl('', [Validators.required, Validators.maxLength(8), AppValidators.chinese]),
            email: new FormControl('', [Validators.required, Validators.email, Validators.maxLength(32)]),
            isActive: new FormControl(true)
        });
    }

    get username() {
        return this.formGroup.get('username');
    }
    get nickName() {
        return this.formGroup.get('nickName');
    }
    get surname() {
        return this.formGroup.get('surname');
    }
    get name() {
        return this.formGroup.get('name');
    }
    get email() {
        return this.formGroup.get('email');
    }

    public hideModal(): void {
        this.isVisible = false;
    }

    public showModal(item: UserDto): void {
        this.editUser = item;
        this.username.setValue(item.username);
        this.surname.setValue(item.surname);
        this.name.setValue(item.name);
        this.email.setValue(item.email);
        this.nickName.setValue(item.nickName);
        // 获取指定用户的信息
        this.userService.get(item.id).subscribe(res => {
            this.roles.forEach(element => {
                if (element.id === this.userRoles.map(ele => ele.id)) {
                    element.isActive = true;
                } else {
                    element.isActive = false;
                }
            });
        });
        //获取用户角色
        this.userService.getUserRole(item.id).subscribe(result => {
            this.userRoles = result.map(ele => ({
                key: ele.id,
                name: ele.name,
                description: ele.description
            }));
        });
        this.isVisible = true;
    }

    save() {
        this.formGroup.value['userId'] = this.editUser.id;
        this.formGroup.value['id'] = this.editUser.id;
        this.formGroup.value['isActive'] = this.editUser.isActive;
        this.formGroup.value['roleIds'] = this.roles.filter(ele => ele.isActive).map(ele => ele.id);

        this.isLoading = true;
        this.userService.update(this.formGroup.value).subscribe(
            resp => {
                this.hideModal();
                this.modalSave.emit();
                this.isLoading = false;
            },
            error => {
                this.isLoading = false;
                this.nzMessageService.error(error.message ? error.message : '更新用户信息失败！');
            }
        );

        this.userService.updateRoles(this.formGroup.value).subscribe(
            resp => {
                this.hideModal();
                this.modalSave.emit();
                this.isLoading = false;
            },
            error => {
                this.isLoading = false;
                this.nzMessageService.error(error.message ? error.message : '更新用户角色失败！');
            }
        );
    }
}
