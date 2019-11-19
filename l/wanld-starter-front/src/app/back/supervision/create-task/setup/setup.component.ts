import { Component, OnInit, ViewChild, Injector } from '@angular/core';

import { ComponentBase } from 'src/app/_shared/components/component-base';

import { SupervisionService } from '../../services/supervision.service';
import { TaskFormComponent } from '../../components/task-form/task-form.component';

@Component({
    selector: 'app-setup',
    templateUrl: './setup.component.html',
    styleUrls: ['./setup.component.less']
})
export class SetupComponent extends ComponentBase implements OnInit {
    @ViewChild('taskForm', { static: true }) taskForm: TaskFormComponent;
    constructor(protected injector: Injector, private supervisionService: SupervisionService) {
        super(injector);
    }
    ngOnInit() {}
    onFormPayload(ev) {
        this.supervisionService.createTask(ev).subscribe(
            resp => {
                // TODO: 怎么把 resp 的数据带到 result 页面
                this.router.navigate(['b/s/result'], { queryParams: { id: resp.id, title: resp.title } });
            },
            err => {
                this.router.navigate(['b/s/result']);
            }
        );
    }
}
