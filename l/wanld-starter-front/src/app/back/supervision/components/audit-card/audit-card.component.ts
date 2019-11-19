import { Component, OnInit, Input, Output, EventEmitter } from '@angular/core';

import { SuperviseModel, SupervisionService } from '../../services/supervision.service';

@Component({
    selector: 'app-audit-card',
    templateUrl: './audit-card.component.html',
    styleUrls: ['./audit-card.component.less']
})
export class AuditCardComponent implements OnInit {
    isPass: boolean;
    @Output() superviseEvent = new EventEmitter();
    @Output() errorEvent = new EventEmitter();
    @Input() hasAction: boolean;
    @Input() dataProp;
    constructor(private supervisionService: SupervisionService) {}
    ngOnInit() {}
    // TODO: 审批的时候更新 commitTime
    onReject(id: string) {
        console.log('Id : ', id);
        this.supervisionService.taskReview(id, 2).subscribe(
            res => {
                this.isPass = false;
                this.superviseEvent.emit('falid');
            },
            err => {
                this.errorEvent.emit(err);
            }
        );
    }
    onPass(id: string) {
        console.log('Id : ', id);
        this.supervisionService.taskReview(id, 1).subscribe(
            res => {
                this.isPass = true;
                this.superviseEvent.emit('success');
            },
            err => {
                this.errorEvent.emit(err);
            }
        );
    }
    taskInfo(id) {
        this.supervisionService.getDetail(id).subscribe(resp => {
            this.dataProp = { ...this.dataProp, ...resp[0] };
            console.log('this.dataProp : ', this.dataProp);
        });
    }
}
