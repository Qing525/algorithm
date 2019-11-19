import { Component, OnInit } from '@angular/core';

import { NzNotificationService } from 'ng-zorro-antd';

import { SupervisionService, SuperviseModel } from '../../services/supervision.service';

@Component({
    selector: 'app-approve',
    templateUrl: './approve.component.html'
})
export class ApproveComponent implements OnInit {
    data: SuperviseModel[];

    constructor(private supervisionService: SupervisionService, private notification: NzNotificationService) {}

    ngOnInit() {
        this.supervisionService.getInitiat(0).subscribe(
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
    onEventHandler(event) {
        if (event === 'success') {
            this.notification.create('success', '成功审核该项目', '');
        } else {
            this.notification.create('warning', '已经驳回此项目', '');
        }
        this.ngOnInit();
    }
    onError(ev) {
        this.notification.create('error', '非发起人不能进行审核', '');
    }
}
