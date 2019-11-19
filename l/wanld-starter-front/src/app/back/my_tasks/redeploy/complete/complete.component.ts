import { Component, OnInit } from '@angular/core';
import { ActivatedRoute } from '@angular/router';
import { TasksService } from 'src/app/_shared/services/back/tasks.service';

@Component({
    selector: 'app-complete',
    templateUrl: './complete.component.html',
    styleUrls: ['./complete.component.less']
})
export class CompleteComponent implements OnInit {
    constructor(private router: ActivatedRoute, private tasksService: TasksService) {}

    ngOnInit() {
        // this.id = this.router.snapshot.queryParams['id'];
        // this.tasksService.transferDetail(this.id).subscribe(res => {
        //     this.data = res;
        //     if (this.data != null) {
        //         this.show = true;
        //     }
        // });
    }
}
