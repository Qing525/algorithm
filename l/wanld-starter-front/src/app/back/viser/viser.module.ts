import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { ViserRoutingModule } from './viser-routing.module';
import { OneLadderComponent } from './line/one-ladder/one-ladder.component';
import { OneFoldComponent } from './line/one-fold/one-fold.component';
import { TwoCurveComponent } from './line/two-curve/two-curve.component';
import { TwoFoldComponent } from './line/two-fold/two-fold.component';
import { TwoLadderComponent } from './line/two-ladder/two-ladder.component';
import { PieChartComponent } from './pie/pie-chart/pie-chart.component';
import { RingChartComponent } from './pie/ring-chart/ring-chart.component';
import { RadarBlockComponent } from './radar/radar-block/radar-block.component';
import { RadarLineComponent } from './radar/radar-line/radar-line.component';
import { SimpleBarComponent } from './bar/simple-bar/simple-bar.component';
import { DoubleBarComponent } from './bar/double-bar/double-bar.component';
import { StackBarComponent } from './bar/stack-bar/stack-bar.component';
import { IntervalBarComponent } from './bar/interval-bar/interval-bar.component';
import { PercentBarComponent } from './bar/percent-bar/percent-bar.component';
import { SharedModule } from 'src/app/_shared/shared.module';

@NgModule({
    declarations: [
        OneLadderComponent,
        OneFoldComponent,
        TwoCurveComponent,
        TwoFoldComponent,
        TwoLadderComponent,
        PieChartComponent,
        RingChartComponent,
        RadarBlockComponent,
        RadarLineComponent,
        SimpleBarComponent,
        DoubleBarComponent,
        StackBarComponent,
        IntervalBarComponent,
        PercentBarComponent
    ],
    imports: [CommonModule, ViserRoutingModule, SharedModule]
})
export class ViserModule {}
