import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { TasksService } from 'src/app/_shared/services/back/tasks.service';

@Component({
    selector: 'app-details',
    templateUrl: './details.component.html',
    styleUrls: ['./details.component.less']
})
export class DetailsComponent implements OnInit {
    task: any = [];
    supervision: any = [];
    id: any;
    show = false;
    isComplete = false;

    constructor(private router: ActivatedRoute, protected tasksService: TasksService) {}

    ngOnInit() {
        this.router.queryParams.subscribe(res => {
            this.id = res;
        });
        this.getTask(this.id);
    }

    // 获取指定任务信息
    getTask(id) {
        this.tasksService.getTask(id['0']).subscribe(res => {
            this.task = res['0'];
            this.supervision = res['0'].supervisionDto;

            // 判断是否逾期
            const now = new Date();
            if (now > new Date(this.supervision.endTime)) {
                this.show = true;
            }
        });
    }

    // 完成任务
    complete(id) {
        this.tasksService.complete(id['0']).subscribe(success => {
            this.isComplete = true;
            this.show = false;
        });
    }
}
