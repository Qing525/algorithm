import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { TasksService } from 'src/app/_shared/services/back/tasks.service';

@Component({
    selector: 'app-applydetail',
    templateUrl: './applydetail.component.html',
    styleUrls: ['./applydetail.component.less']
})
export class ApplydetailComponent implements OnInit {
    data: any = [];
    supervision: any = [];
    delay: any = [];
    id: any;

    constructor(private router: ActivatedRoute, protected tasksService: TasksService) {}

    ngOnInit() {
        this.id = this.router.snapshot.queryParams['id'];
        this.getDelayDetail(this.id);
    }

    // 获取延期申请详情
    getDelayDetail(id) {
        this.tasksService.delayDetail(id).subscribe(res => {
            this.data = res['0'];
            this.supervision = res['0'].supervisionDto;
            this.delay = res['0'].delay;
        });
    }
}
