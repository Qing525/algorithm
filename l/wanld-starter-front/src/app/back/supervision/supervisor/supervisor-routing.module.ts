import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';

import { ApproveComponent } from './approve/approve.component';
import { AssignmentComponent } from './assignment/assignment.component';
import { RejectComponent } from './reject/reject.component';
import { TodoComponent } from './todo/todo.component';

// 我督办的项目

const routes: Routes = [
    {
        path: '',
        children: [
            {
                path: 'approve', // 等待审批
                component: ApproveComponent
            },
            {
                path: 'todo', // 待分配
                component: TodoComponent
            },
            {
                path: 'assignment', // 指派二级任务
                component: AssignmentComponent
            },
            {
                path: 'reject',
                component: RejectComponent
            }
        ]
    }
];
@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class SupervisorRoutingModule {}
