import { Component, OnInit, Input } from '@angular/core';
const levelList = ['一级', '二级', '子任务'];
const colorList = ['#f56a00', '#7265e6', '#ffbf00'];
@Component({
    selector: 'app-task-level-icon',
    templateUrl: './task-level-icon.component.html',
    styleUrls: ['./task-level-icon.component.less']
})
export class TaskLevelIconComponent implements OnInit {
    @Input() level: number;
    @Input() isSquire: boolean;
    isChild: boolean;
    text: string;
    color: string;
    constructor() {}
    ngOnInit() {
        this.text = levelList[this.level];
        this.color = colorList[this.level];
        if (this.text === '子任务') {
            this.isChild = true;
        }
    }
}
