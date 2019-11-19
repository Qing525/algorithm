import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { InitiatorRoutingModule } from './initiator-routing.module';
import { ApproveComponent } from './approve/approve.component';
import { ConfirmedComponent } from './confirmed/confirmed.component';
import { ComponentsModule } from '../components/components.module';
import { SharedModule } from 'src/app/_shared/shared.module';
import { DelayComponent } from './delay/delay.component';

@NgModule({
    declarations: [ApproveComponent, ConfirmedComponent, DelayComponent],
    imports: [CommonModule, InitiatorRoutingModule, ComponentsModule, SharedModule]
})
export class InitiatorModule {}
