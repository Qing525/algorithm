import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators } from '@angular/forms';
import { Router } from '@angular/router';

import { UserService } from 'src/app/_shared/services/back/user.service';
import { TasksService } from 'src/app/_shared/services/back/tasks.service';

@Component({
    selector: 'app-redeploy',
    templateUrl: './redeploy.component.html',
    styleUrls: ['./redeploy.component.less']
})
export class RedeployComponent implements OnInit {
    option = [];
    nameList: any = [];

    turnForm: FormGroup;

    constructor(private router: Router, private tasksService: TasksService, private userService: UserService) {
        this.turnForm = new FormGroup({
            taskname: new FormControl('', Validators.required),
            receiver: new FormControl('', Validators.required),
            infor: new FormControl('', Validators.required)
        });
    }

    ngOnInit() {
        this.getReceive();
        this.getUser();
    }

    // 获取我接收的任务
    getReceive() {
        this.tasksService.getReceive(1, 0).subscribe(res => {
            this.option = res;
        });
    }

    // 获取所有用户
    getUser() {
        this.userService.getUser().subscribe(success => {
            this.nameList = success.list.map(res => {
                return { id: res.id, username: res.username };
            });
        });
    }

    // 提交表单信息
    submitForm() {
        if (this.turnForm.invalid) {
            return;
        }
        this.router.navigate(['/b/mytask/process'], {
            queryParams: this.turnForm.value
        });
    }
}
