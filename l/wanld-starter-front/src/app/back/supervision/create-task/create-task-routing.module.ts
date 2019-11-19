import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { InTakeComponent } from './in-take/in-take.component';
import { ResultPageComponent } from './result-page/result-page.component';
import { SetupComponent } from './setup/setup.component';

const routes: Routes = [
    {
        path: 'intake',
        component: InTakeComponent
    },
    {
        path: 'setup',
        component: SetupComponent
    },
    {
        path: 'result',
        component: ResultPageComponent
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class CreateTaskRoutingModule {}
