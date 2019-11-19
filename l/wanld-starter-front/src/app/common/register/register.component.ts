import { Component, Injector, OnInit } from '@angular/core';
import { FormControl, FormGroup, Validators } from '@angular/forms';
import { Router } from '@angular/router';
import { ComponentBase } from 'src/app/_shared/components/component-base';
import { AccountService } from 'src/app/_shared/services/account.service';
import { AppValidators } from 'src/app/_shared/utils/app-validators';

@Component({
  templateUrl: './register.component.html',
  styleUrls: ['../../../styles/register.component.less']
})
export class RegisterComponent extends ComponentBase implements OnInit {
  registerForm: FormGroup = new FormGroup({
    nickname: new FormControl(null, [
      Validators.maxLength(16),
      AppValidators.nickName
    ]),
    emailAddress: new FormControl(null, [
      Validators.required,
      Validators.maxLength(64),
      Validators.email
    ]),
    password: new FormControl(null, [
      Validators.required,
      Validators.minLength(6),
      Validators.maxLength(64)
    ]),
    confirmPassword: new FormControl(null, [
      this.confirmationValidator.bind(this)
    ])
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
    } else if (control.value !== this.registerForm.controls.password.value) {
      return { confirm: true, error: true };
    }
  }

  get nickname() {
    return this.registerForm.get('nickname');
  }
  get emailAddress() {
    return this.registerForm.get('emailAddress');
  }
  get password() {
    return this.registerForm.get('password');
  }
  get confirmPassword() {
    return this.registerForm.get('confirmPassword');
  }

  ngOnInit(): void {}

  register() {
    if (this.registerForm.invalid) {
      return;
    }

    this.account.userRegister(this.registerForm.value).subscribe(
      res => {
        this.nzmodalService.success({
          nzTitle: '注册成功！',
          nzContent: '欢迎注册使用，请及时至您的邮箱确认注册！',
          nzOnOk: () => {
            this.router.navigate(['/login']);
          }
        });
      },
      error => {
        this.nzmodalService.warning({
          nzTitle: '注册失败',
          nzContent: error.message
        });
      }
    );
  }
}
