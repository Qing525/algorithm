import { HttpClient, HttpErrorResponse, HttpParams } from '@angular/common/http';
import { Injector, isDevMode } from '@angular/core';
import { Router } from '@angular/router';

import { Observable, of, throwError } from 'rxjs';

import { NzMessageService, NzModalService } from 'ng-zorro-antd';

import { tap } from 'rxjs/operators';

import { CookieService } from 'ngx-cookie-service';

import { MockResponse } from './mock-response';

export class ServiceBase {
    isMockData = false;
    protected baseApi = isDevMode() ? 'http://localhost:8080' : '';
    protected cookieService: CookieService;
    protected http: HttpClient;
    protected router: Router;

    public nzModalService: NzModalService;

    public nzMessageService: NzMessageService;

    constructor(protected injector: Injector) {
        this.cookieService = injector.get(CookieService);
        this.http = injector.get(HttpClient);
        this.router = injector.get(Router);
        this.nzMessageService = injector.get(NzMessageService);
        this.nzModalService = injector.get(NzModalService);
    }
    protected mockData(data, code = 200, message = '请求成功', success = true) {
        return new MockResponse(code, data, message, success);
    }
    protected extractData(res: any) {
        if (res.code !== 200 && res.code !== '200') {
            throw new HttpErrorResponse({ error: res, status: res.code });
        }
        return res['result'] || res['data'];
    }
    protected handleError(errorResponse: HttpErrorResponse) {
        if (errorResponse.error instanceof ErrorEvent) {
            console.error('An error occurred:', errorResponse.error.message);
            return throwError(errorResponse.error.message);
        } else {
            if (errorResponse.status === 401) {
                this.nzModalService.confirm({
                    nzTitle: '未登录的访问：',
                    nzContent: '您当前的操作需要登录，点击确定登陆！',
                    nzOnOk: () => {
                        this.router.navigate(['/', 'login'], {
                            queryParams: { url: this.router.url }
                        });
                    }
                });
            } else if (errorResponse.status === 403) {
                this.nzMessageService.warning('403无访问权限');
            } else if (errorResponse.status === 404) {
                this.nzMessageService.warning('404找不到请求！');
            }
            return throwError(errorResponse.error.error);
        }
    }

    protected params(params: {}): any {
        let httpParams = new HttpParams();

        for (const key in params) {
            if (params.hasOwnProperty(key)) {
                httpParams = httpParams.append(key, params[key]);
            }
        }
        return { params: httpParams };
    }
    protected paramId(id: any): any {
        const httpParams = new HttpParams().set('id', id);
        return { params: httpParams };
    }
}
export class ResponseWrap<T> {
    error: any;
    result: T;
    success: boolean;
    targetUrl: string;
    unAuthorizedRequest: boolean;
}

export class PagedResultDto<T> {
    public totalCount: number;

    public list: T[];

    public id: number;

    public name?: number;

    public username?: String;

    public icon?: String;

    public nickName?: String;

    public note?: String;

    public createTime: Date;

    public loginTime?: Date;

    public status: number;

    public description?: String;

    public userCount?: number;
}

export class CacheData<T> {
    static allCacheDatas: CacheData<any>[] = [];
    constructor() {
        this.data = null;
        CacheData.allCacheDatas.push(this);
    }
    data: T;
}
/**
 * @param cache  数据结果，包裹在此类内。
 * @param request http.get() or http.post()
 * @param reload 是否跳过缓存
 */
export function cacheService<T>(cache: CacheData<T>, request: Observable<any>, reload?: boolean): Observable<any> {
    if (cache.data && !reload) {
        return of(cache.data);
    }
    return request.pipe(
        tap(res => {
            cache.data = res;
        })
    );
}
