import { Component, OnInit } from '@angular/core';

import { SupervisionService } from '../../services/supervision.service';

@Component({
    selector: 'app-todo',
    templateUrl: './todo.component.html'
})
export class TodoComponent implements OnInit {
    approvedList = [];
    constructor(private supervisionService: SupervisionService) {}
    ngOnInit() {
        this.getApproved();
    }
    getApproved() {
        // 审核通过待分配
        this.supervisionService.getInitiat(1).subscribe(
            resp => {
                if (resp.length !== 0) {
                    this.approvedList = resp;
                } else {
                    this.approvedList = null;
                }
            },
            err => {}
        );
    }
}
