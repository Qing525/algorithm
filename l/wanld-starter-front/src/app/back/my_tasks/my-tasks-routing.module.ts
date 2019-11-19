import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { Permission } from 'src/app/_shared/permission';

import { ApplyComponent } from './apply/apply.component';
import { OngoingComponent } from './ongoing/ongoing.component';
import { RedeployComponent } from './redeploy/redeploy.component';
import { TasklistComponent } from './tasklist/tasklist.component';
import { ApplydetailComponent } from './apply/applydetail/applydetail.component';
import { DetailsComponent } from './ongoing/details/details.component';
import { CompleteComponent } from './redeploy/complete/complete.component';
import { ConfirmComponent } from './redeploy/confirm/confirm.component';

const routes: Routes = [
    {
        path: '', // b/mytask/
        children: [
            {
                // 我的任务-任务列表
                path: 'tasklist', // mytask/tasklist
                component: TasklistComponent
            },
            {
                // 我的任务-正在进行
                path: 'ongoing',
                component: OngoingComponent
            },
            {
                // 我的任务-任务转派
                path: 'redeploy',
                component: RedeployComponent
            },
            { path: 'details', component: DetailsComponent },
            { path: 'redeploy', component: RedeployComponent },
            { path: 'ongoing', component: OngoingComponent },
            { path: 'process', component: ConfirmComponent },
            { path: 'complete', component: CompleteComponent },
            { path: 'tasklist', component: TasklistComponent },
            { path: 'applyDetail', component: ApplydetailComponent },
            { path: 'apply', component: ApplyComponent }
        ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class MyTasksRoutingModule {}
