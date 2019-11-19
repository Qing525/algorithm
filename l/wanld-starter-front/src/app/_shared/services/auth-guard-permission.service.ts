import { Injectable, Injector } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot } from '@angular/router';
import { Observable, of } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

import { AuthService } from './auth.service';
import { ServiceBase } from './service-base';


@Injectable()
export class AuthGuardPermission extends ServiceBase implements CanActivate {
    constructor(
        protected injector: Injector,
        protected authService: AuthService) {
        super(injector);
    }
    canActivate(route: ActivatedRouteSnapshot, state: RouterStateSnapshot): Observable<boolean> | boolean {
        if (!route.data['permission']) {
            return of(true);
        }


        return this.authService.getPermissions(true).pipe(map(data => {
            // console.log(data.find(a => a.value === route.data['permission']));

            if (data && !!data.find(a => a.value === route.data['permission'])) {
                return true;
            } else {
                this.nzMessageService.warning('您没有该功能权限！');
                return false;
            }
        }), catchError(() => {
            this.router.navigate(['']);
            this.nzMessageService.warning('授权失败！');
            return of(false);
        }));
    }
}
