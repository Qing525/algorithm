import { NgModule } from '@angular/core';

import { RoleService } from './role.service';
import { UserService } from './user.service';
import { TasksService } from './tasks.service';

@NgModule({
  providers: [UserService, RoleService, TasksService]
})
export class BackServiceProxyModule {}
