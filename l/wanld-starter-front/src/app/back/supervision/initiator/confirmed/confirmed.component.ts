import { Component, OnInit } from '@angular/core';

import { SupervisionService } from '../../services/supervision.service';

@Component({
    selector: 'app-confirmed',
    templateUrl: './confirmed.component.html'
})
export class ConfirmedComponent implements OnInit {
    // 这个组件名字要重新命名
    tasks = [];
    ReviewStatus = 1; // 1 通过, 2 驳回
    constructor(private supervisionService: SupervisionService) {}
    ngOnInit() {
        this.getInitiatTasks(this.ReviewStatus);
    }
    getInitiatTasks(status: number): void {
        this.supervisionService.getInitiat(status).subscribe(
            resp => {
                this.tasks = resp.map(e => ({ ...e, reviewStatus: this.ReviewStatus }));
            },
            err => console.log('err : ', err)
        );
    }
    selectChange(event) {
        // TODO: 能不能把数据 cache 下来
        if (this.ReviewStatus === 1) {
            this.ReviewStatus = 2;
            this.ngOnInit();
        } else {
            this.ReviewStatus = 1;
            this.ngOnInit();
        }
    }
}
