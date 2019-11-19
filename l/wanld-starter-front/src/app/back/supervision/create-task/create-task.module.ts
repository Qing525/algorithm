import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { CreateTaskRoutingModule } from './create-task-routing.module';
import { RouterModule } from '@angular/router';
import { SetupComponent } from './setup/setup.component';
import { InTakeComponent } from './in-take/in-take.component';
import { SharedModule } from 'src/app/_shared/shared.module';
import { ComponentsModule } from '../components/components.module';
import { ResultPageComponent } from './result-page/result-page.component';

@NgModule({
    declarations: [SetupComponent, InTakeComponent, ResultPageComponent],
    imports: [CommonModule, CreateTaskRoutingModule, RouterModule, SharedModule, ComponentsModule]
})
export class CreateTaskModule {}
