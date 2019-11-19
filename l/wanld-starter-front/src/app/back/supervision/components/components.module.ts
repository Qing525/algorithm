import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { RouterModule } from '@angular/router';

import { SharedModule } from 'src/app/_shared/shared.module';

import { AuditCardComponent } from './audit-card/audit-card.component';
import { AuditDashboardComponent } from './audit-dashboard/audit-dashboard.component';
import { StatusBadgeComponent } from './status-badge/status-badge.component';
import { TaskAssignListComponent } from './task-assign-list/task-assign-list.component';
import { TaskFormComponent } from './task-form/task-form.component';
import { TaskLevelIconComponent } from './task-level-icon/task-level-icon.component';
import { TaskListComponent } from './task-list/task-list.component';

@NgModule({
    declarations: [
        AuditCardComponent,
        AuditDashboardComponent,
        StatusBadgeComponent,
        TaskAssignListComponent,
        TaskFormComponent,
        TaskLevelIconComponent,
        TaskListComponent
    ],
    imports: [CommonModule, SharedModule, RouterModule],
    exports: [
        AuditCardComponent,
        AuditDashboardComponent,
        StatusBadgeComponent,
        TaskAssignListComponent,
        TaskFormComponent,
        TaskLevelIconComponent,
        TaskListComponent
    ]
})
export class ComponentsModule {}
