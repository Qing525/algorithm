import { Component, OnInit } from '@angular/core';

@Component({
  templateUrl: 'home.component.html',
  styleUrls: ['../../../styles/home.component.less']
})
export class HomeComponent implements OnInit {

  public array: any = [{
    pic: '../../../assets/images/list1.jpg'
  }, {
    pic: '../../../assets/images/list2.jpg'
  }, {
    pic: '../../../assets/images/list3.jpg'
  }, {
    pic: '../../../assets/images/list4.jpg'
  }, {
    pic: '../../../assets/images/list5.jpg'
  },
];
  effect = 'scrollx';
  constructor() {}

  ngOnInit() {}
}
