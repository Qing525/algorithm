import { Injectable, Injector } from '@angular/core';

import { Observable, of } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

import { MockResponse } from '../mock-response';
import { PagedResultDto, ServiceBase } from '../service-base';

import { RoleDto } from './role.service';

@Injectable()
export class UserService extends ServiceBase {
    constructor(protected injector: Injector) {
        super(injector);
    }
    // 获取指定用户信息
    public get(id: number): Observable<UserDto> {
        const url = this.baseApi + '/api/user/' + id;
        return this.http.get(url).pipe(
            map(this.extractData.bind(this)),
            catchError(this.handleError.bind(this))
        );
    }
    //获取所有用户信息
    public getUser(): Observable<UserDto> {
        const url = this.baseApi + '/api/user/list';
        return this.http.get(url).pipe(
            map(this.extractData),
            catchError(this.handleError)
        );
    }
    //获取用户角色
    public getUserRole(id: number): Observable<UserDto[]> {
        const url = this.baseApi + '/api/user/get-user-role/' + id;
        return this.http.get(url).pipe(
            map(this.extractData.bind(this)),
            catchError(this.handleError.bind(this))
        );
    }
    //根据用户名或姓名分页获取用户列表\
    /**
     *
     * @param PageIndex
     * @param PageSize
     * @param search
     * @param sortKey
     * @param sortValue
     */
    public getAllSearch(
        PageIndex?: number,
        PageSize?: number,
        search: string = null,
        sortKey = null,
        sortValue = null
    ): Observable<PagedResultDto<UserDto>> {
        if (this.isMockData) {
            const page = new PagedResultDto();
            page.list = [
                {
                    id: 1,
                    userName: 'admin',
                    email: 'eee.qq.com'
                }
            ];

            const mockData: MockResponse = this.mockData(page);

            return of(mockData).pipe(
                map(this.extractData.bind(this)),
                catchError(this.handleError.bind(this))
            );
        }
        const url = this.baseApi + '/api/user/list';

        return this.http
            .get(
                url,
                this.params({
                    PageIndex: PageIndex,
                    PageSize: PageSize,
                    name: search,
                    sortKey: sortKey,
                    sortValue: sortValue
                })
            )
            .pipe(
                map(this.extractData.bind(this)),
                catchError(this.handleError.bind(this))
            );
    }
    // 获取所有角色
    public getRoles(): Observable<RoleDto[]> {
        const url = this.baseApi + '/api/role/list';
        return this.http.get(url).pipe(
            map(this.extractData),
            catchError(this.handleError)
        );
    }
    //修改指定用户信息
    /**
     *
     * @param user:UserDto
     */
    update(user: UserDto): Observable<UserDto> {
        const url = this.baseApi + '/api/user/update/' + user.id;

        return this.http.post(url, user).pipe(
            map(this.extractData.bind(this)),
            catchError(this.handleError.bind(this))
        );
    }
    // 删除指定用户信息（根据id）
    delete(user: UserDto): Observable<any> {
        const url = this.baseApi + '/api/user/delete/' + user.id;

        return this.http.post(url, this.paramId(user.id)).pipe(
            map(this.extractData.bind(this)),
            catchError(this.handleError.bind(this))
        );
    }
    // 更新用户角色信息
    updateRoles(user: UserDto): Observable<UserDto> {
        const url = this.baseApi + '/api/user/role/update' + '?userId=' + user.userId + '&' + 'roleIds=' + user.roleIds;
        return this.http.post(url, user).pipe(
            map(this.extractData.bind(this)),
            catchError(this.handleError.bind(this))
        );
    }
}

export class UserDto {
    id: number;
    userId?: number;
    username: string;
    surname?: string;
    name?: string;
    nickName: string;
    email: string;
    isActive?: boolean;
    loginTime?: Date;
    createTime?: Date;
    roleNames?: string[];
    status?: number;
    noto?: string;
    description?: string;
    roleIds?: number[];
    list: any;
}
