import { Component, OnInit } from '@angular/core';

const data = [
  { year: '1951 年', sales: 38 },
  { year: '1952 年', sales: 52 },
  { year: '1956 年', sales: 61 },
  { year: '1957 年', sales: 145 },
  { year: '1958 年', sales: 48 },
  { year: '1959 年', sales: 38 },
  { year: '1960 年', sales: 38 },
  { year: '1962 年', sales: 38 },
];

const scale = [{
  dataKey: 'sales',
  tickInterval: 20,
}];

@Component({
  selector: 'app-simple-bar',
  templateUrl: './simple-bar.component.html'
})
export class SimpleBarComponent implements OnInit {

  forceFit = true;
  height = 400;
  data = data;
  scale = scale;

  constructor() { }

  ngOnInit() {
  }

}
