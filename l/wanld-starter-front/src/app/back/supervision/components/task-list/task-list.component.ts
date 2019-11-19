import { Component, OnInit, Input } from '@angular/core';

import { SuperviseModel } from '../../services/supervision.service';

@Component({
    selector: 'app-task-list',
    templateUrl: './task-list.component.html'
})
export class TaskListComponent implements OnInit {
    @Input() listProp: SuperviseModel[];
    constructor() {}
    ngOnInit() {}
}
