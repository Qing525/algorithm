import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { Permission } from '../_shared/permission';
import { AuthGuardPermission } from '../_shared/services/auth-guard-permission.service';
import { BackComponent } from './back.component';
import { BackRoleComponent } from './role/back-role.component';
import { BackUserComponent } from './user/back-user.component';

const menus = Permission.menus;
const routes: Routes = [
    {
        path: '',
        component: BackComponent,
        children: [
            {
                path: menus.user.path,
                component: BackUserComponent,
                canActivate: [AuthGuardPermission],
                data: { title: menus.user.name, permission: menus.user.value }
            },
            {
                path: menus.role.path,
                component: BackRoleComponent,
                canActivate: [AuthGuardPermission],
                data: { title: menus.role.name, permission: menus.role.value }
            },
            {
                // 督办系统
                path: menus.supervision.path, // b/s
                loadChildren: () => import('./supervision/supervision.module').then(m => m.SupervisionModule)
            },
            {
                // viser
                path: menus.viser.path,
                loadChildren: () => import('./viser/viser.module').then(m => m.ViserModule)
            },
            {
                // viser
                path: 'mytask',
                loadChildren: () => import('./my_tasks/my-tasks.module').then(m => m.MyTasksModule)
            }
        ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class BackRoutingModule {}
