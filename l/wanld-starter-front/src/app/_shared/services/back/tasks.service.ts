import { Injectable, Injector } from '@angular/core';
import { map } from 'rxjs/operators';
import { ServiceBase } from '../service-base';

@Injectable()
export class TasksService extends ServiceBase {
    constructor(protected injector: Injector) {
        super(injector);
    }

    // 获取所有与我相关的任务
    public getAll() {
        const url = this.baseApi + '/api/supervision/receive';
        return this.http.get(url).pipe(map(this.extractData));
    }

    // 获取所有已接收的任务
    public getReceive(isReceive: number, isComplete: number) {
        const url = this.baseApi + '/api/supervision/receive';
        return this.http
            .get(url, this.params({ isReceive, isComplete }))
            .pipe(map(this.extractData));
    }

    // 修改任务状态
    update(supervisionId: string) {
        console.log(supervisionId);
        const url = this.baseApi + '/api/supervision/accept';
        return this.http
            .post(url, null, this.params({ supervisionId }))
            .pipe(map(this.extractData));
    }

    // 获取指定任务信息
    public getTask(supervisionId: number) {
        const url = this.baseApi + '/api/supervision/detail';
        return this.http.get(url, this.params({ supervisionId })).pipe(map(this.extractData));
    }

    // 提交转派信息
    public transfer(supervisionId: string, receiveId: string) {
        const url = this.baseApi + '/api/supervision/transfer';
        return this.http
            .post(url, null, this.params({ supervisionId, receiveId }))
            .pipe(map(this.extractData));
    }

    // 查询督办任务转派详情
    public transferDetail(supervisionId: string) {
        const url = this.baseApi + '/api/supervision/transfer_detail';
        return this.http.get(url, this.params({ supervisionId })).pipe(map(this.extractData));
    }

    // 完成任务
    public complete(supervisionId: string) {
        const url = this.baseApi + '/api/supervision/complete';
        return this.http
            .post(url, null, this.params({ supervisionId }))
            .pipe(map(this.extractData));
    }

    // 逾期申请
    public apply(DelayDto) {
        const url = this.baseApi + '/api/supervision/delay/apply';
        return this.http.post(url, DelayDto).pipe(map(this.extractData));
    }

    // 查看延期申请详情
    public delayDetail(id: string) {
        const url = this.baseApi + '/api/supervision/delay/detail';
        return this.http.get(url, this.params({ id })).pipe(map(this.extractData));
    }
}

export class TasksDto {
    id: string;
    pid?: string;
    commitTime: Date;
    content: string;
    endTime: Date;
    initiateTime: Date;
    intitiatorUserId?: string;
    level: number;
    numberNo: string;
    receiveUserId: string;
    remark: string;
    retractTime: Date;
    reviewStatus: number;
    superviseCategoryId: string;
    supervisionStatusId: string;
    title: string;
    type: number;
}
