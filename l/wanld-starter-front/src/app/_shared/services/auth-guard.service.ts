import { Injectable, Injector } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot } from '@angular/router';
import { Observable, of } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

import { AuthService } from './auth.service';
import { ServiceBase } from './service-base';


@Injectable()
export class AuthGuard extends ServiceBase implements CanActivate {
    constructor(
        protected injector: Injector,
        protected authService: AuthService) {
        super(injector);
    }
    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | boolean {
        return this.authService.getLoginInfo(true).pipe(map(data => {
            if (data && data.user) {
                return true;
            } else {
                this.router.navigate(['']);
                this.authService.nzMessageService.warning('未登录!');
                return false;
            }

        }), catchError(() => {
            this.router.navigate(['']);
            this.authService.nzMessageService.warning('未登录!');
            return of(false);
        }));
    }
}
