import { Component, OnInit, Input } from '@angular/core';

@Component({
    selector: 'app-status-badge',
    templateUrl: './status-badge.component.html',
    styleUrls: ['./status-badge.component.less']
})
export class StatusBadgeComponent implements OnInit {
    @Input() statusProp: number;
    // 0未审核  1通过   2驳回  3复审中',
    status;
    constructor() {}

    ngOnInit() {
        this.status = this.statusProp;
    }
}
