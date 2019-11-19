import { Injectable, Injector } from '@angular/core';
import { Observable } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

import { ServiceBase } from '../service-base';

@Injectable()
export class AuthorService extends ServiceBase {
    constructor(protected injector: Injector) {
        super(injector);
    }
    //修改权限
    update(id: number): Observable<any> {
        const url = this.baseApi + '/permission/update/{id}';
        return this.http.put(url, id).pipe(
            map(this.extractData),
            catchError(this.handleError)
        );
    }
    //添加权限
    add(author: AuthorDto): Observable<any> {
        const url = this.baseApi + '/permission/create';
        return this.http.post(url, author).pipe(
            map(this.extractData),
            catchError(this.handleError)
        );
    }
    //删除权限
    delete(id: number): Observable<any> {
        const url = this.baseApi + '/permission/delete';
        return this.http.post(url, id).pipe(
            map(this.extractData),
            catchError(this.handleError)
        );
    }
    //获取所有权限列表
    getPermissionList(PageNum: number, PageSize: number): Observable<any> {
        const url = this.baseApi + '/permission/list';
        return this.http.get(url, this.params({
            PageNum: PageNum,
            PageSize: PageSize
        })).pipe(map(this.extractData), catchError(this.handleError));
    }
    getAuthorByName(name: string): Observable<AuthorDto[]> {
        const url = this.baseApi + '/api/services/app/Author/GetAuthorByName';
        return this.http.get(url, this.params({ name: name })).pipe(
            map(this.extractData),
            catchError(this.handleError)
        );
    }
}

export class AuthorDto {
    id: number;
    name: string;
    dynasty: number | undefined;

    desc: string;
    creationTime: Date;
}






