import { Component, Injector, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ComponentBase } from 'src/app/_shared/components/component-base';
import { AccountService } from 'src/app/_shared/services/account.service';

@Component({
    templateUrl: './change-password.component.html',
    selector: 'app-change-password'
})
export class ChangePasswordComponent extends ComponentBase implements OnInit {

    changePasswordForm: FormGroup = new FormGroup({
        'oldPassword': new FormControl(null, [Validators.required, Validators.minLength(6), Validators.maxLength(64)]),
        'password': new FormControl(null, [Validators.required, Validators.minLength(6), Validators.maxLength(64)]),
        'confirmPassword': new FormControl(null, [this.confirmationValidator.bind(this)]),
    });
    constructor(
        protected injector: Injector,
        protected router: Router,
        protected account: AccountService
    ) {
        super(injector);

    }

    confirmationValidator(control: FormControl) {
        if (!control.value) {
            return { required: true };
        } else if (control.value !== this.changePasswordForm.controls.password.value) {
            return { confirm: true, error: true };
        }
    }

    get oldPassword() { return this.changePasswordForm.get('oldPassword'); }
    get password() { return this.changePasswordForm.get('password'); }
    get confirmPassword() { return this.changePasswordForm.get('confirmPassword'); }

    ngOnInit(
    ): void { }

    update() {

        if (this.changePasswordForm.invalid) {
            return;
        }
        this.isLoading = true;
        this.account.changePassword({ oldPassword: this.oldPassword.value, newPassword: this.password.value }).subscribe(res => {
            this.isLoading = false;
            this.nzMessageService.success('修改密码成功！');

        }, (error) => {
            this.isLoading = false;
            this.nzMessageService.error('修改密码失败：' + error.message);
        });
    }
}
