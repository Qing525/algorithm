import { Component, OnInit, Injector, ViewChild } from '@angular/core';

import { ComponentBase } from 'src/app/_shared/components/component-base';

import { SupervisionService } from '../../services/supervision.service';
import { TaskFormComponent } from '../../components/task-form/task-form.component';

@Component({
    selector: 'app-assignment',
    templateUrl: './assignment.component.html'
})
export class AssignmentComponent extends ComponentBase implements OnInit {
    // 从待分配的某一个任务跳转过来, 此时从 URL 知道这个任务的 level 和 supervisionId
    // 点击添加按钮 ( 任务拆分 ) modal title 应该显示任务编号
    // 页面仅仅展示这个二级任务拆分后的几个子任务 => 可以撤回 可以查看任务详情`
    @ViewChild('taskForm', { static: true }) taskForm: TaskFormComponent;
    pid: string;
    level: number;
    initiatorUserId: string;
    isVisible = false;
    subTasks = [];
    constructor(protected injector: Injector, private supervisionService: SupervisionService) {
        super(injector);
    }
    ngOnInit() {
        this.activatedRoute.queryParams.subscribe(res => {
            this.pid = res.pid; // 子任务的 pid
            this.level = res.level; // 判断是指派还是拆分
            this.initiatorUserId = res.initiatorUserId; // 填表单用
        });
        this.supervisionService.getChildSupervision(this.pid).subscribe(res => {
            if (res) {
                this.subTasks = res;
            } else {
                this.subTasks = null;
            }
        });
    }
    handleOk(): void {
        this.taskForm.submitTask(this.initiatorUserId, '2'); // 子任务 level = 2
    }
    // 监听 formPayload 事件
    onDispatchTask(formContent) {
        this.supervisionService.createTask(formContent, this.pid).subscribe(
            success => {
                this.subTasks.push(success);
                this.nzMessageService.create('success', '成功创建子任务');
                this.isVisible = false;
            },
            err => {
                this.nzMessageService.create('error', '创建子任务失败', err);
                this.isVisible = false;
            }
        );
    }
    retract(id) {
        this.supervisionService.taskRetract(id).subscribe(
            success => {
                this.ngOnInit();
                this.nzMessageService.create('success', '成功撤销任务');
            },
            err => {}
        );
    }
}
