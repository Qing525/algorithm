import { Component, Injector, OnInit } from '@angular/core';
import { ComponentPageBase } from 'src/app/_shared/components/component-page-base';
import { AccountService } from 'src/app/_shared/services/account.service';
import { LoginUser } from 'src/app/_shared/services/auth.service';

@Component({
    templateUrl: './feature.component.html',
})
export class FeatureComponent extends ComponentPageBase<any> implements OnInit {

    genderLoding = false;
    user: LoginUser;
    constructor(
        protected injector: Injector,
        protected accountService: AccountService
    ) { super(injector); }


    ngOnInit(): void {
        this.getUserInfo();

    }

    getUserInfo() {
        this.authService.getLoginInfo(true).subscribe(res => {
            this.user = res.user;
        });
    }
    changeGender() {
        this.genderLoding = true;
        this.accountService.changeGender().subscribe(res => {
            this.user.gender = res;
            this.genderLoding = false;
        }, error => {
            this.genderLoding = false;
        });
    }
}
