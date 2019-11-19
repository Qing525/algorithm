import { Component, OnInit } from '@angular/core';

const data = [
  { x: '分类一', y: [ 76, 100 ] },
  { x: '分类二', y: [ 56, 108 ] },
  { x: '分类三', y: [ 38, 129 ] },
  { x: '分类四', y: [ 58, 155 ] },
  { x: '分类五', y: [ 45, 120 ] },
  { x: '分类六', y: [ 23, 99 ] },
  { x: '分类七', y: [ 18, 56 ] },
  { x: '分类八', y: [ 18, 34 ] },
];

@Component({
  selector: 'app-interval-bar',
  templateUrl: './interval-bar.component.html'
})
export class IntervalBarComponent implements OnInit {

  forceFit = true;
  height = 400;
  data = data;

  constructor() { }

  ngOnInit() {
  }

}
