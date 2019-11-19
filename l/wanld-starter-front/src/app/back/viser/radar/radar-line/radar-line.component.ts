import { Component, OnInit } from '@angular/core';
import DataSet from '@antv/data-set';

const sourceData = [
    { item: 'Design', a: 70, b: 30 },
    { item: 'Development', a: 60, b: 70 },
    { item: 'Marketing', a: 50, b: 60 },
    { item: 'Users', a: 40, b: 50 },
    { item: 'Test', a: 60, b: 70 },
    { item: 'Language', a: 70, b: 50 },
    { item: 'Technology', a: 50, b: 40 },
    { item: 'Support', a: 30, b: 40 },
    { item: 'Sales', a: 60, b: 40 },
    { item: 'UX', a: 50, b: 60 }
];

const dv = new DataSet.View().source(sourceData);
dv.transform({
    type: 'fold',
    fields: ['a', 'b'],
    key: 'user',
    value: 'score'
});
const data = dv.rows;

const scale = [
    {
        dataKey: 'score',
        min: 0,
        max: 80
    }
];

const axis1GridOpts = {
    lineStyle: {
        lineDash: null
    },
    hideFirstLine: false
};

const axis2GridOpts = {
    type: 'polygon',
    lineStyle: {
        lineDash: null
    }
};

@Component({
    selector: 'app-radar-line',
    templateUrl: './radar-line.component.html'
})
export class RadarLineComponent implements OnInit {
    forceFit = true;
    height = 500;
    data = data;
    padding = [20, 20, 95, 20];
    scale = scale;
    axis1GridOpts = axis1GridOpts;
    axis2GridOpts = axis2GridOpts;

    constructor() {}

    ngOnInit() {}
}
