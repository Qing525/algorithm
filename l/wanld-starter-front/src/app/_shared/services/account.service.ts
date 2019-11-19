import { Injectable, Injector } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { CacheData, cacheService, ServiceBase } from './service-base';

@Injectable()
export class AccountService extends ServiceBase {
    constructor(protected injector: Injector) {
        super(injector);
    }

    userRegister(model: any): Observable<any> {
        if (this.isMockData) {
            return of(this.mockData({})).pipe(
                map(this.extractData),
                catchError(this.handleError)
            );
        }
        // FIXME: 后端地址报错 404
        const url = this.baseApi + '/api/auth/register';
        return this.http.post(url, model).pipe(
            map(this.extractData),
            catchError(this.handleError)
        );
    }

    getEmailByToken(token: string): Observable<string> {
        const url = this.baseApi + '/api/services/app/Account/GetEmailByToken';
        return this.http.get(url, this.params({ token: token })).pipe(
            map(this.extractData),
            catchError(this.handleError)
        );
    }

    getEmailByChangePasswordToken(token: string): Observable<string> {
        const url = this.baseApi + '/api/services/app/Account/GetEmailByChangePasswordToken';
        return this.http.get(url, this.params({ token: token })).pipe(
            map(this.extractData),
            catchError(this.handleError)
        );
    }

    confirmEmailOfRegist(model: { token: string }): Observable<any> {
        const url = this.baseApi + '/api/services/app/Account/ConfirmEmailOfRegist';
        return this.http.post(url, model).pipe(
            map(this.extractData),
            catchError(this.handleError)
        );
    }

    changePasswordByEmailConfirmation(model: { token: string; password: string }): Observable<any> {
        const url = this.baseApi + '/api/services/app/Account/ChangePasswordByEmailConfirmation';
        return this.http.post(url, model).pipe(
            map(this.extractData),
            catchError(this.handleError)
        );
    }
    sendChangePasswordEmailConfirmation(model: { email: string }): Observable<any> {
        const url = this.baseApi + '/api/services/app/Account/SendChangePasswordEmailConfirmation';
        return this.http.post(url, model).pipe(
            map(this.extractData),
            catchError(this.handleError)
        );
    }

    changeGender(): Observable<any> {
        const url = this.baseApi + '/api/services/app/Account/ChangeGender';
        return this.http.post(url, null).pipe(
            map(this.extractData),
            catchError(this.handleError)
        );
    }
    changePassword(model: any): Observable<any> {
        const url = this.baseApi + '/api/services/app/Account/ChangePassword';
        return this.http.post(url, model).pipe(
            map(this.extractData),
            catchError(this.handleError)
        );
    }

    // setBgColor(color: string) {
    //     document.body.style.backgroundColor = color;
    //     this.cookieService.set(AppEnum.CookieNameOfBodyBgColor, color, AppEnum.CookieExpireDay * AppEnum.DaySeconds);
    // }

    // getBgColor() {
    //     const color = this.cookieService.get(AppEnum.CookieNameOfBodyBgColor);
    //     if (color) {
    //         document.body.style.backgroundColor = color;
    //     }
    //     return color;
    // }
}
