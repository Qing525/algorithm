import { NgModule } from '@angular/core';

import { SharedModule } from '../_shared/shared.module';
import { BackServiceProxyModule } from '../_shared/services/back/back-service-proxy.module';

import { BackRoutingModule } from './back-routing.module';
import { BackComponent } from './back.component';

import { BackRoleComponent } from './role/back-role.component';
import { BackUserComponent } from './user/back-user.component';
import { BackRoleAddComponent } from './role/add/back-role-add.component';
import { BackRoleEditComponent } from './role/edit/back-role-edit.component';
import { BackUserEditComponent } from './user/edit/back-user-edit.component';

@NgModule({
    imports: [SharedModule, BackRoutingModule, BackServiceProxyModule],
    declarations: [
        BackComponent,
        BackUserComponent,
        BackUserEditComponent,
        BackRoleComponent,
        BackRoleAddComponent,
        BackRoleEditComponent
    ],
    providers: []
})
export class BackModule {}
