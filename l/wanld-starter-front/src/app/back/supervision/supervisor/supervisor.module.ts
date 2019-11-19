import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SharedModule } from 'src/app/_shared/shared.module';

import { ComponentsModule } from '../components/components.module';

import { SupervisorRoutingModule } from './supervisor-routing.module';

import { ApproveComponent } from './approve/approve.component';
import { AssignmentComponent } from './assignment/assignment.component';
import { RejectComponent } from './reject/reject.component';
import { TodoComponent } from './todo/todo.component';

@NgModule({
    declarations: [AssignmentComponent, ApproveComponent, RejectComponent, TodoComponent],
    imports: [CommonModule, SupervisorRoutingModule, ComponentsModule, SharedModule]
})
export class SupervisorModule {}
