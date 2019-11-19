import { Injectable, Injector } from '@angular/core';

import { Observable } from 'rxjs';
import { catchError, map } from 'rxjs/operators';

import { ServiceBase } from '../service-base';
import { Permission } from '../../permission';

@Injectable()
export class RoleService extends ServiceBase {
    constructor(protected injector: Injector) {
        super(injector);
    }

    getRoleForEdit(roleId: number): Observable<Permission[]> {
        const url = this.baseApi + '/api/role/permission/' + roleId;
        return this.http.get(url).pipe(
            map(this.extractData.bind(this)),
            catchError(this.handleError.bind(this))
        );
    }
    //修改角色
    update(role: RoleDto): Observable<RoleDto> {
        const url = this.baseApi + '/api/role/update/' + role.id;
        return this.http.post(url, role).pipe(
            map(this.extractData.bind(this)),
            catchError(this.handleError.bind(this))
        );
    }
    //添加角色
    create(role: RoleDto): Observable<any> {
        const url = this.baseApi + '/api/role/create';
        return this.http.post(url, role).pipe(
            map(this.extractData.bind(this)),
            catchError(this.handleError.bind(this))
        );
    }
    //删除角色
    delete(ids: number): Observable<any> {
        const url = this.baseApi + '/role/delete' + '?ids=' + ids;
        return this.http.post(url, this.paramId(ids)).pipe(
            map(this.extractData.bind(this)),
            catchError(this.handleError.bind(this))
        );
    }
    //获取角色权限列表
    getPermissionTreeList(): Observable<Permission[]> {
        const url = this.baseApi + '/api/permission/tree-list';
        return this.http.get(url).pipe(
            map(this.extractData.bind(this)),
            catchError(this.handleError.bind(this))
        );
    }
    //获取所有角色
    public getAll(PageIndex: number, PageSize: number): Observable<RoleDto[]> {
        const url = this.baseApi + '/api/role/list';
        return this.http
            .get(
                url,
                this.params({
                    PageIndex: PageIndex,
                    PageSize: PageSize
                })
            )
            .pipe(
                map(this.extractData.bind(this)),
                catchError(this.handleError.bind(this))
            );
    }
    //更改角色权限
    updatePermission(role: RoleDto): Observable<RoleDto> {
        const url = this.baseApi + '/api/role/update/' + role.id;
        return this.http.post(url, role).pipe(
            map(this.extractData.bind(this)),
            catchError(this.handleError.bind(this))
        );
    }
}
export class RoleDto {
    id: number;
    roleId?: number[];
    name: string;
    value?: string;

    description: string;
    isStatic?: boolean;
    permissionNames?: string[];
    permissionIds: number[];
    children?: [];
    isLeaf?: boolean;
    isDisabled?: boolean;
}
