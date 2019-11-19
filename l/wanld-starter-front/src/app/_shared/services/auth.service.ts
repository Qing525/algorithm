import { Injectable, Injector } from '@angular/core';
import { Observable, of } from 'rxjs';
import { catchError, map, tap } from 'rxjs/operators';

import { AppEnum } from '../app-constant';
import { Permission } from '../permission';
import { UserDto } from './back/user.service';
import { CacheData, cacheService, ServiceBase } from './service-base';


@Injectable()
export class AuthService extends ServiceBase {

    private user = new CacheData<CurrentLoginInfo>();
    private grantedPermissions = new CacheData<object>();

    constructor(protected injector: Injector) {
        super(injector);
    }
    // 登录
    getCaptchaId() {
        const url = this.baseApi + '/api/auth/captcha/init';
        return this.http.get(url).pipe(map(this.extractData));
    }

    checkInputCaptcha(code, captchaId: string): Observable<any> {
        const url = this.baseApi + `/api/auth/captcha/check`;
        return this.http.post(url, null, { params: { code, captchaId } });
    }




    login(model: any): Observable<LoginResultModel> {
        this.logout();
        if (this.isMockData) {
            const mockData = this.mockData({
                'accessToken': 'eyJhbGciOiJIUzI1NiIsInR',
                'expireInSeconds': 31536000,
            });

            return of(mockData)
                .pipe(map(this.extractData), catchError(this.handleError))
                .pipe(tap((data) => {
                    this.saveTokenToCookie(data, model.rememberClient);
                }));
        }
        const url = this.baseApi + '/api/auth/login';
        return this.http.post(url, model)
            .pipe(map(this.extractData), catchError(this.handleError))
            .pipe(tap((data) => {
                this.saveTokenToCookie(data, true);
            }));
    }
    //登出
    logout() {
        this.cookieService.delete(AppEnum.CookieNameOfAuthToken, '/');
        this.cookieService.delete(AppEnum.encrptedAuthTokenName, '/');
        // 清掉所有的缓存的数据。
        for (const item of CacheData.allCacheDatas) {
            item.data = null;
        }
    }
    //获取当前登录用户信息
    public getLoginInfo(reload?: boolean): Observable<CurrentLoginInfo> {

        if (this.isMockData) {
            return cacheService(this.user, of(this.mockData(new CurrentLoginInfo({}, {
                name: 'user', surname: 'mock', username: 'mockUser', avatar: '', email: 'mock@mock.com', gender: true, nickName: 'mock'
            }))).pipe(map(this.extractData), catchError(this.handleError)), reload);
        }

        const url = this.baseApi + '/api/auth/getLoginInfo';
        return cacheService(this.user, this.http.get(url).pipe(map(this.extractData), catchError(this.handleError)), reload);
    }
    //获取当前登录用户权限信息
    getPermissions(reload?: boolean): Observable<Permission[]> {
        let url = this.isMockData ? (this.baseApi + 'assets/mock-data/authorization/login.json')
            : (this.baseApi + '/AbpUserConfiguration/GetAll');

        url = this.baseApi + '/api/auth/getPermissions';
        return cacheService(this.grantedPermissions, this.http.get(url).pipe(map(this.extractData), catchError(this.handleError)), reload);
    }

    public getAuthorizationToken(): string {
        const token = this.cookieService.get(AppEnum.CookieNameOfAuthToken);
        return token;
    }

    private saveTokenToCookie(data: LoginResultModel, rememberMe: boolean): void {
        const tokenExpireDate = rememberMe ? data.expireInSeconds / (3600 * 24) : 0;
        this.cookieService.set(AppEnum.CookieNameOfAuthToken, data.accessToken, tokenExpireDate, '/');
    }
    //用户注册
    public register(user: UserDto): Observable<any> {
        const url = this.baseApi + '/api/auth/register';
        return this.http.post(url, user).pipe(map(this.extractData), catchError(this.handleError));
    }

    public captchaUri(captchaId: string): string {
        if (captchaId) {
            return this.baseApi + '/api/auth/captcha/' + captchaId;
        }
        return '';
    }
}

export class LoginResultModel {
    accessToken: string;
    expireInSeconds: number;
}

export class CurrentLoginInfo {
    application: {
        version: string;
        features: { [key: string]: boolean; };
    };

    user: LoginUser;

    permissions: Permission[];

    constructor(app, u: LoginUser) {
        this.application = app;
        this.user = u;
    }
}

export class LoginUser {
    public id?: string;
    public username: string;
    public email: string;
    public nickName: string;


    public name: string;
    public surname: string;
    public avatar: string;
    public gender: boolean;

}


