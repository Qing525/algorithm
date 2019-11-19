import { Injectable, Injector } from '@angular/core';

import { Observable } from 'rxjs';
import { map, catchError } from 'rxjs/operators';

import { ServiceBase } from 'src/app/_shared/services/service-base';

@Injectable({
    providedIn: 'root'
})
export class SupervisionService extends ServiceBase {
    constructor(protected injector: Injector) {
        super(injector);
    }
    readonly supervisionApi = 'api/supervision';
    readonly userList = `${this.baseApi}/api/user/list`; // GET
    readonly create = `${this.baseApi}/${this.supervisionApi}/create`; // POST
    readonly assign = `${this.baseApi}/${this.supervisionApi}/assign`; // POST
    readonly retract = `${this.baseApi}/${this.supervisionApi}/retract`; // POST
    readonly review = `${this.baseApi}/${this.supervisionApi}/review`; // POST
    readonly initiate = `${this.baseApi}/${this.supervisionApi}/initiate`; // GET
    readonly queryChild = `${this.baseApi}/${this.supervisionApi}/query-child`; // GET /{ID}
    readonly queryDeatil = `${this.baseApi}/${this.supervisionApi}/detail`; // GET /{ID}
    readonly receiveBySelf = `${this.baseApi}/${this.supervisionApi}/receiveBySelf`; // GET
    readonly supervise = `${this.baseApi}/${this.supervisionApi}/supervise`; // GET
    readonly c_createCategory = `${this.baseApi}/${this.supervisionApi}/category/create`; // POST
    readonly c_deleteCategory = `${this.baseApi}/${this.supervisionApi}/category/delete`; // POST
    readonly c_updateCategory = `${this.baseApi}/${this.supervisionApi}/category/update`; // POST
    readonly c_listCategory = `${this.baseApi}/${this.supervisionApi}/category/list`; // GET
    readonly d_apply = `${this.baseApi}/${this.supervisionApi}/delay/apply`; // POST
    readonly d_detail = `${this.baseApi}/${this.supervisionApi}/delay/detail`; // POST
    readonly d_query = `${this.baseApi}/${this.supervisionApi}/delay/query`; // POST
    readonly d_review = `${this.baseApi}/${this.supervisionApi}/delay/review`; // POST
    // tools
    replaceNull(param: []) {
        return param.map((ele: any) => {
            for (const i in ele) {
                // tslint:disable-next-line: triple-equals
                if (ele[i] == undefined) {
                    ele[i] = '无';
                }
            }
            return ele;
        });
    }
    treeMode(nodes) {
        return nodes.map(e => {
            if (e.children.length) {
                return { title: e.title, key: e.id, children: this.treeMode(e.children) };
            } else {
                return { title: e.title, key: e.id, isLeaf: true };
            }
        });
    }
    /**
     *
     * @param status 0 1 2
     */
    getInitiat(status: number): Observable<any> {
        return this.http.get(this.initiate, this.params({ status })).pipe(
            map(this.extractData),
            map(this.replaceNull),
            catchError(this.handleError)
        );
    }
    /**
     *
     * @param supervisionId 任务 id
     */
    getChildSupervision(supervisionId: string): Observable<any> {
        return this.http.get(this.queryChild, this.params({ supervisionId })).pipe(
            map(this.extractData),
            map(this.replaceNull),
            catchError(this.handleError)
        );
    }
    /**
     *
     * @param supervisionId 任务 id
     */
    getDetail(supervisionId: string): Observable<any> {
        return this.http.get(this.queryDeatil, this.params({ supervisionId })).pipe(
            map(this.extractData),
            catchError(this.handleError)
        );
    }
    getSupervise(status): Observable<any> {
        return this.http.get(this.supervise, this.params({ status })).pipe(
            map(this.extractData),
            map(this.replaceNull),
            catchError(this.handleError)
        );
    }
    getSelfReceive(): Observable<any> {
        return this.http.get(this.receiveBySelf).pipe(
            map(this.extractData),
            catchError(this.handleError)
        );
    }
    getAllUser(): Observable<any> {
        return this.http.get(this.userList).pipe(
            map(this.extractData),
            map(data => data.list.map(e => ({ name: e.username, id: e.id }))),
            catchError(this.handleError)
        );
    }
    /**
     *
     * @param data form data
     * @param pid optional
     */
    createTask(data, pid?: string): Observable<SuperviseModel> {
        // 任务创建
        return this.http.post(this.create, { ...data, pid }).pipe(
            map(this.extractData),
            catchError(this.handleError)
        );
    }
    /**
     *
     * @param supervisionId 任务 id
     */
    taskRetract(supervisionId: string): Observable<any> {
        return this.http.post(this.retract, null, this.params({ supervisionId })).pipe(
            map(this.extractData),
            catchError(this.handleError)
        );
    }
    /**
     *
     * @param supervisionId  任务 id
     * @param status 0 审核中 1 通过 2 驳回
     */
    taskReview(supervisionId: string, status): Observable<any> {
        return this.http.post(this.review, null, this.params({ supervisionId, status })).pipe(
            map(this.extractData),
            catchError(this.handleError)
        );
    }
    /**
     *
     * @param supervisionId 任务 id
     * @param receiveId 接收者 id
     */
    taskTransfer(supervisionId: string, receiveId: string): Observable<any> {
        return this.http.post(this.review, null, this.params({ supervisionId, receiveId })).pipe(
            map(this.extractData),
            catchError(this.handleError)
        );
    }

    getCategory(): Observable<any> {
        return this.http.get(this.c_listCategory).pipe(
            map(this.extractData),
            map(data => this.treeMode(data)),
            catchError(this.handleError)
        );
    }

    /**
     *
     * @param data:id?:number; key:string; pid:number; sort:number; title:string;
     */
    upCategory(data: CategoryModel): Observable<any> {
        return this.http.post(this.c_updateCategory, data).pipe(
            map(this.extractData),
            catchError(this.handleError)
        );
    }
    /**
     *
     * @param id string
     */
    delCategory(id: string): Observable<any> {
        return this.http.post(this.c_deleteCategory, null, this.paramId(id)).pipe(
            map(this.extractData),
            catchError(this.handleError)
        );
    }
    /**
     *
     * @param data id?:number; key:string; pid:number; sort:number; title:string;
     * @param id number
     */
    addCategory(data: CategoryModel): Observable<any> {
        return this.http.post(this.c_createCategory, data).pipe(
            map(this.extractData),
            catchError(this.handleError)
        );
    }
    getDelay(status: number): Observable<any> {
        return this.http.get(this.d_query, this.params({ status })).pipe(
            map(this.extractData),
            catchError(this.handleError)
        );
    }
}

export class SuperviseModel {
    id?: string;
    numberNo?: string; // COMMENT '任务编号' 后端生成,
    type: number; // 任务类型 0定期任务 1周期任务',
    title: string; // 标题
    content: string; // 督办内容
    level: number; // 1 拆分   0不拆分',
    initiatorUserId: number | string; // '发起人id',
    initiateTime: Date | string; // '发起时间',
    superviseUserId: number | string; // '督办人',
    superviseTime: Date | string; // '督办时间',
    endTime: Date | string; // '计划完成时间',
    commitTime: Date | string; // '提交时间',
    isComplete: number; // 是否完成
    isReceive: number; // 是否被接收
    remark?: string; // 督办备注
    retractTime: Date | string; // '撤回时间',
    supervisionCategoryId: number[]; // '任务分类id，用户自定义分类',
    reviewStatus: number; // 审核状态   0 未审核 1通过   2驳回',
    pid?: string; // '父任务 id',
    supervisionStatusId: string;
    performance: string; // '绩效',
    receiveUserId: number | string; // 接收者 id
}
export class SupervisionDTO {
    content: string;
    level: number;
    performance: string;
    remark: string;
    title: string;
    type: 0;
}
export class CategoryModel {
    id?: string; // 只有更新时候用 id
    title: string;
    pid?: string;
    children?: CategoryModel[];
}
// 一个任务的所有状态
export enum ReviewStatus {
    reviewed = 0, // 审核
    approved, // 通过
    rejected // 驳回
}
