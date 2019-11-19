import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { SharedModule } from 'src/app/_shared/shared.module';

import { SupervisionRoutingModule } from './supervision-routing.module';

import { ComponentsModule } from './components/components.module';
import { CreateTaskModule } from './create-task/create-task.module';

@NgModule({
    declarations: [],
    imports: [CommonModule, SupervisionRoutingModule, CreateTaskModule, ComponentsModule, SharedModule]
})
export class SupervisionModule {}
