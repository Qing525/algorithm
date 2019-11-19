import { Component, OnInit } from '@angular/core';
import { TasksService } from 'src/app/_shared/services/back/tasks.service';
import { Router } from '@angular/router';

@Component({
    selector: 'app-ongoing',
    templateUrl: './ongoing.component.html',
    styleUrls: ['./ongoing.component.less']
})
export class OngoingComponent implements OnInit {
    data: any = [];
    show = false;

    constructor(protected tasksService: TasksService, protected router: Router) {}

    ngOnInit() {
        this.getReceive();
    }

    // 获取进行中任务列表
    getReceive() {
        this.tasksService.getReceive(1, 0).subscribe(res => {
            this.data = res;
            if (this.data != null) {
                this.show = true;
            }
        });
    }

    // 查看指定任务详情
    details(id) {
        this.router.navigate(['/b/mytask/details'], {
            queryParams: id
        });
    }
}
