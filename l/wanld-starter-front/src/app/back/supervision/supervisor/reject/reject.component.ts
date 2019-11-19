import { Component, OnInit } from '@angular/core';

import { SupervisionService } from '../../services/supervision.service';

@Component({
    selector: 'app-reject',
    templateUrl: './reject.component.html'
})
export class RejectComponent implements OnInit {
    rejectList = [];
    constructor(private superviseService: SupervisionService) {}

    ngOnInit() {
        this.getSupervise('2');
    }
    getSupervise(status: string) {
        this.superviseService.getSupervise(status).subscribe(res => {
            if (res.length !== 0) {
                this.rejectList = res.map(e => ({ ...e, reviewStatus: 2 }));
            }
        });
    }
}
