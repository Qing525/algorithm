import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { TasksService } from 'src/app/_shared/services/back/tasks.service';
import { ActivatedRoute } from '@angular/router';
import { NzMessageService } from 'ng-zorro-antd/message';

@Component({
    selector: 'app-apply',
    templateUrl: './apply.component.html',
    styleUrls: ['./apply.component.less']
})
export class ApplyComponent implements OnInit {
    option: any = [];
    id: any;
    isShow = false;
    task: any = [];

    applyForm: FormGroup;

    constructor(
        private tasksService: TasksService,
        private router: ActivatedRoute,
        private message: NzMessageService
    ) {
        this.applyForm = new FormGroup({
            endTime: new FormControl('', Validators.required),
            cause: new FormControl('', Validators.required),
            supervisionId: new FormControl('', Validators.required),
            title: new FormControl('', Validators.required)
        });
    }

    ngOnInit() {
        this.getExpired();
    }

    // 获取我逾期的任务
    getExpired() {
        this.id = this.router.snapshot.queryParams['id'];
        this.tasksService.getTask(this.id).subscribe(res => {
            this.option = res['0'].supervisionDto;
            this.applyForm.get('title').setValue(this.option.title);
        });
    }

    // 提交申请信息信息
    submitForm() {
        if (this.applyForm.invalid) {
            return;
        }
        this.tasksService.apply(this.applyForm.value).subscribe(
            success => {
                this.task = success;
                this.message.success('申请提交成功，等待督办人审核！', {
                    nzDuration: 10000
                });
                this.isShow = true;
            },
            error => {
                this.message.error('当前申请未审核，请勿重复提交', {
                    nzDuration: 10000
                });
                this.isShow = false;
            }
        );
    }

    // 查看申请详情
    applyDetail() {}
}
