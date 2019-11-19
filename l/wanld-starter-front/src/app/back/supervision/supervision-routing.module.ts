import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

const routes: Routes = [
    {
        path: '', // b/s/
        children: [
            {
                path: 'initiator', // b/s/initiator
                loadChildren: () => import('./initiator/initiator.module').then(m => m.InitiatorModule)
            },
            {
                path: 'supervisor', // b/s/supervisor
                loadChildren: () => import('./supervisor/supervisor.module').then(m => m.SupervisorModule)
            }
        ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class SupervisionRoutingModule {}
