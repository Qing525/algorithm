import { Component, OnInit } from '@angular/core';

import { NzNotificationService } from 'ng-zorro-antd';

import { SupervisionService, SuperviseModel } from '../../services/supervision.service';
@Component({
    selector: 'app-approve',
    templateUrl: './approve.component.html',
    styleUrls: ['./approve.component.less']
})
export class ApproveComponent implements OnInit {
    data: SuperviseModel[];

    constructor(private supervisionService: SupervisionService, private notification: NzNotificationService) {}

    ngOnInit() {
        this.supervisionService.getSupervise('0').subscribe(
            resp => {
                if (resp.length !== 0) {
                    this.data = resp;
                } else {
                    this.data = null;
                }
            },
            err => {}
        );
    }
}
