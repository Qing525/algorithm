import { Component, Injector, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { AppConstant } from 'src/app/_shared/app-constant';
import { ComponentBase } from 'src/app/_shared/components/component-base';
import { AccountService } from 'src/app/_shared/services/account.service';
import { AuthService, LoginUser } from 'src/app/_shared/services/auth.service';

@Component({
    selector: 'app-front-nav',
    styleUrls: ['./app-front-nav.component.less'],
    templateUrl: './app-front-nav.component.html',
})
export class AppFrontNavComponent extends ComponentBase implements OnInit {
    open = true;
    constructor(
        protected injector: Injector,
        protected authService: AuthService,
        protected accountService: AccountService,
        protected router: Router
    ) {
        super(injector);
    }


    user: LoginUser;

    ngOnInit(): void {
        this.authService.getLoginInfo(true).subscribe(res => {
            console.log(res);
            this.user = res.user;
        });
    }
    getAvatarUrl() {

        const avatarUrl = '/assets/avatar/girl.png';
        if (!this.user || !this.user.avatar) {

            return this.user.gender ? '/assets/avatar/boy.png' : '/assets/avatar/girl.png';
        } else {
            return `/assets/avatar/${this.user.avatar}.png`;
        }
    }

    login() {

        AppConstant.targetUrl = this.router.url;
        this.router.navigate(['/login']);
    }

    logout() {
        this.user = null;
        this.authService.logout();
        this.router.navigate(['']);
    }



}
