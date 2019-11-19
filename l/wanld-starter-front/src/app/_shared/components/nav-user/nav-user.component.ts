import { Component, Injector, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { AppConstant } from '../../app-constant';
import { AccountService } from '../../services/account.service';
import { AuthService, LoginUser } from '../../services/auth.service';
import { ComponentBase } from '../component-base';

@Component({
    selector: 'app-nav-user',
    templateUrl: './nav-user.component.html',
    styleUrls: ['./nav-user.component.less']
})
export class NavUserComponent extends ComponentBase implements OnInit {
    open = true;
    constructor(
        protected injector: Injector,
        protected authService: AuthService,
        protected accountService: AccountService,
        protected router: Router) { super(injector); }

    user: LoginUser;

    ngOnInit(): void {
        this.authService.getLoginInfo(true).subscribe(res => {
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
