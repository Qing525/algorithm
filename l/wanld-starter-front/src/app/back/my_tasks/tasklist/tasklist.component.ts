import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

import { NzMessageService } from 'ng-zorro-antd';

import { TasksService } from 'src/app/_shared/services/back/tasks.service';

@Component({
    selector: 'app-tasklist',
    templateUrl: './tasklist.component.html',
    styleUrls: ['./tasklist.component.less']
})
export class TasklistComponent implements OnInit {
    tasklist: any = [];
    show = false;
    isShow = false;

    constructor(
        protected tasksService: TasksService,
        protected router: Router,
        private nzMessageService: NzMessageService
    ) {}

    ngOnInit() {
        this.getAll();
    }

    // 获取所有数据
    getAll() {
        this.tasksService.getAll().subscribe(res => {
            this.tasklist = res;
            if (this.tasklist != null) {
                this.show = true;
            }

            // // 截止时间
            // const now = new Date();
            // const list = this.tasklist.map(e => ({
            //     endTime: new Date(e.endTime)
            // }));
            // list.forEach(e => {
            //     if (e.endTime < now) {
            //         this.isShow = true;
            //     } else {
            //         this.isShow = false;
            //     }
            // });
        });
    }

    // 接收任务，将任务从待接收状态变成已接收
    receive(id) {
        this.tasksService.update(id).subscribe(
            success => {
                this.success();
                this.getAll();
            },
            err => {}
        );
    }

    // 成功提示
    success(): void {
        this.nzMessageService.info('任务接收成功');
    }

    // 查看已接收任务
    process() {
        this.router.navigate(['/b/mytask/ongoing']);
    }

    //逾期申请
    apply(id) {
        this.router.navigate(['/b/mytask/details'], {
            queryParams: id
        });
    }
}
