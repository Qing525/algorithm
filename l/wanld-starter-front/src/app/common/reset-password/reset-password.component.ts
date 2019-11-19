import { Component, Injector, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { ComponentBase } from 'src/app/_shared/components/component-base';
import { AccountService } from 'src/app/_shared/services/account.service';

@Component({
    templateUrl: 'reset-password.component.html'
})

export class ResetPasswordComponent extends ComponentBase implements OnInit {
    token = null;
    email: string = null;
    registerForm: FormGroup = new FormGroup({
        'password': new FormControl(null, [Validators.required, Validators.minLength(6), Validators.maxLength(64)]),
        'confirmPassword': new FormControl(null, [this.confirmationValidator.bind(this)]),
    });

    get password() { return this.registerForm.get('password'); }
    get confirmPassword() { return this.registerForm.get('confirmPassword'); }

    constructor(
        protected injector: Injector,
        protected accountService: AccountService) {
        super(injector);
        this.token = this.activatedRoute.snapshot.queryParamMap.get('token');
        console.log(this.token);
    }
    confirmationValidator(control: FormControl) {
        if (!control.value) {
            return { required: true };
        } else if (control.value !== this.registerForm.controls.password.value) {
            return { confirm: true, error: true };
        }
    }
    ngOnInit() {
        this.accountService.getEmailByChangePasswordToken(encodeURIComponent(this.token)).subscribe(res => {
            this.email = res;
        }, (error) => {
            this.nzmodalService.warning({
                nzTitle: '提示',
                nzContent: error.message,
                nzOnOk: () => {

                }
            });
        });
    }

    resetPassword() {
        if (this.registerForm.valid && this.email) {
            this.accountService.changePasswordByEmailConfirmation({ token: this.token, password: this.password.value }).subscribe(res => {
                this.nzmodalService.success({
                    nzTitle: '重置密码',
                    nzContent: '您已成功重置密码！',
                    nzOnOk: () => {
                        this.router.navigate(['/login']);
                    }
                });
            }, (error) => {
                this.nzMessageService.error(error.message);
            });
        } else {
            this.nzMessageService.warning('表单验证失败');
        }
    }
}
