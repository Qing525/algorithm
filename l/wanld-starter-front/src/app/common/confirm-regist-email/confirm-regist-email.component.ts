import { Component, Injector, OnInit } from '@angular/core';
import { ComponentBase } from 'src/app/_shared/components/component-base';
import { AccountService } from 'src/app/_shared/services/account.service';

@Component({
    templateUrl: 'confirm-regist-email.component.html'
})

export class ConfirmRegistEmailComponent extends ComponentBase implements OnInit {
    token = null;
    email = null;

    error = null;
    constructor(
        protected injector: Injector,
        protected accountService: AccountService) {
        super(injector);
        this.token = this.activatedRoute.snapshot.queryParamMap.get('token');
    }

    ngOnInit() {
        if (this.token) {
            this.accountService.confirmEmailOfRegist({ token: this.token }).subscribe(res => {
                this.email = res;

            }, (error) => {
                this.error = error;
                this.nzMessageService.warning(error.message);
            });
        } else {
            this.router.navigate(['/']);
        }
    }

    close() {
        window.close();
    }
}
