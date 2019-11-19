import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
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

const routes: Routes = [
    {
        path: '',
        children: [
            { path: 'oneLadder', component: OneLadderComponent },
            { path: 'oneFold', component: OneFoldComponent },
            { path: 'twoCurve', component: TwoCurveComponent },
            { path: 'twoFold', component: TwoFoldComponent },
            { path: 'twoLadder', component: TwoLadderComponent },
            { path: 'pie', component: PieChartComponent },
            { path: 'ring', component: RingChartComponent },
            { path: 'radarBlock', component: RadarBlockComponent },
            { path: 'radarLine', component: RadarLineComponent },
            { path: 'simpleBar', component: SimpleBarComponent },
            { path: 'doubleBar', component: DoubleBarComponent },
            { path: 'stackBar', component: StackBarComponent },
            { path: 'intervalBar', component: IntervalBarComponent },
            { path: 'percentBar', component: PercentBarComponent }
        ]
    }
];

@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})
export class ViserRoutingModule {}
