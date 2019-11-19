import { Component, OnInit } from '@angular/core';

const data = [
  { month: 'Jan', value: 51 },
  { month: 'Feb', value: 91 },
  { month: 'Mar', value: 34 },
  { month: 'Apr', value: 47 },
  { month: 'May', value: 63 },
  { month: 'June', value: 58 },
  { month: 'July', value: 56 },
  { month: 'Aug', value: 77 },
  { month: 'Sep', value: 99 },
  { month: 'Oct', value: 106 },
  { month: 'Nov', value: 88 },
  { month: 'Dec', value: 56 },
];

const scale = [{
  dataKey: 'month',
  min: 0,
  max: 1,
}];

@Component({
  selector: 'app-one-ladder',
  templateUrl: './one-ladder.component.html'
})
export class OneLadderComponent implements OnInit {

  forceFit = true;
  height = 400;
  data = data;
  scale = scale;

  constructor() { }

  ngOnInit() {
  }

}
