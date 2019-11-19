import { Component, Injector, OnInit } from '@angular/core';
import { ComponentBase } from 'src/app/_shared/components/component-base';
import { AccountService } from 'src/app/_shared/services/account.service';

@Component({
  templateUrl: './find-password.component.html',
  styleUrls: ['../../../styles/find-password.component.less']
})
export class FindPasswordComponent extends ComponentBase implements OnInit {
  email = null;
  constructor(
    protected injector: Injector,
    protected accountService: AccountService
  ) {
    super(injector);
  }

  ngOnInit() {}

  sendEmail() {
    if (!this.email || !this.email.trim()) {
      this.nzMessageService.warning('请输入您的注册邮箱！');
      return;
    }

    this.nzmodalService.confirm({
      nzTitle: '找回密码 ',
      nzContent: '您是否要找回密码？ ',
      nzOnOk: () => {
        this.isLoading = true;
        this.accountService
          .sendChangePasswordEmailConfirmation({ email: this.email })
          .subscribe(
            res => {
              this.isLoading = false;
              this.nzmodalService.success({
                nzTitle: '重置密码',
                nzContent:
                  '已向您的注册邮箱发送重置密码邮件，请点击您邮件链接重置密码！\n 如果您不能收到重置密码邮件，请发送邮件反馈至wangzgms@outlook.com',
                nzOnOk: () => {}
              });
            },
            error => {
              this.nzMessageService.warning(error);
              this.isLoading = false;
            }
          );
      }
    });
  }
}
