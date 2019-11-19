import { Component, OnInit } from '@angular/core';

import { SupervisionService } from '../../services/supervision.service';

@Component({
    selector: 'app-delay',
    templateUrl: './delay.component.html'
})
export class DelayComponent implements OnInit {
    delayList = [];
    constructor(private supervisionService: SupervisionService) {}
    ngOnInit() {
        this.fetchDelay(0);
    }
    fetchDelay(status) {
        this.supervisionService.getDelay(status).subscribe(res => {
            if (res) {
                this.delayList = res;
            } else {
                this.delayList = null;
            }
        });
    }
}
