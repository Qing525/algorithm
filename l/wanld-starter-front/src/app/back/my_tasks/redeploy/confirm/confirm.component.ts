import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { TasksService } from 'src/app/_shared/services/back/tasks.service';
import { UserService } from 'src/app/_shared/services/back/user.service';

@Component({
    selector: 'app-confirm',
    templateUrl: './confirm.component.html',
    styleUrls: ['./confirm.component.less']
})
export class ConfirmComponent implements OnInit {
    supervision: any = [];
    receiver: any = [];
    infor: any;

    constructor(private router: ActivatedRoute, private tasksService: TasksService, private userService: UserService) {}

    ngOnInit() {
        this.router.queryParams.subscribe(data => {
            this.infor = data;
        });
        this.tasksService.getTask(this.infor.taskname).subscribe(res => {
            this.supervision = res['0'].supervisionDto;
        });
        this.userService.get(this.infor.receiver).subscribe(res => {
            this.receiver = res;
        });
    }

    // 提交转派信息
    submit() {
        this.tasksService.transfer(this.infor.taskname, this.infor.receiver).subscribe();
    }
}
