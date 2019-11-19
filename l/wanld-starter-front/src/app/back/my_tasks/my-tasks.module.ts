import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

import { SharedModule } from 'src/app/_shared/shared.module';

import { ComponentsModule } from '../supervision/components/components.module';

import { MyTasksRoutingModule } from './my-tasks-routing.module';

import { ApplyComponent } from './apply/apply.component';
import { OngoingComponent } from './ongoing/ongoing.component';
import { RedeployComponent } from './redeploy/redeploy.component';
import { TasklistComponent } from './tasklist/tasklist.component';
import { ApplydetailComponent } from './apply/applydetail/applydetail.component';
import { DetailsComponent } from './ongoing/details/details.component';
import { CompleteComponent } from './redeploy/complete/complete.component';
import { ConfirmComponent } from './redeploy/confirm/confirm.component';

@NgModule({
    imports: [CommonModule, MyTasksRoutingModule, SharedModule, RouterModule, ComponentsModule],
    declarations: [
        // 任务接收
        OngoingComponent,
        TasklistComponent,
        DetailsComponent,
        // 任务转派
        RedeployComponent,
        ConfirmComponent,
        CompleteComponent,
        ApplyComponent,
        ApplydetailComponent
    ],
    providers: []
})
export class MyTasksModule {}
