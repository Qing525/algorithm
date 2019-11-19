import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ApproveComponent } from './approve/approve.component';
import { ConfirmedComponent } from './confirmed/confirmed.component';
import { DelayComponent } from './delay/delay.component';

// 我发起的项目

const routes: Routes = [
    {
        path: '',
        children: [
            {
                path: 'approve',
                component: ApproveComponent
            },
            {
                path: 'confirmed',
                component: ConfirmedComponent
            },
            {
                path: 'delay',
                component: DelayComponent
            }
        ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class InitiatorRoutingModule {}
