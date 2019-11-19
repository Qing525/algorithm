import { Component, OnInit, Input } from '@angular/core';

import { SuperviseModel, SupervisionService } from '../../services/supervision.service';

@Component({
    selector: 'app-task-assign-list',
    templateUrl: './task-assign-list.component.html',
    styleUrls: ['./task-assign-list.component.less']
})
export class TaskAssignListComponent implements OnInit {
    @Input() dataProp;
    show = false;
    constructor(private supervisionService: SupervisionService) {}

    ngOnInit() {
        this.getDetail();
    }
    onExpand(ev) {
        this.show = !this.show;
    }
    getDetail() {
        const { id } = this.dataProp;
        this.supervisionService.getDetail(id).subscribe(res => {
            this.dataProp = { ...this.dataProp, ...res[0] };
        });
    }
}
