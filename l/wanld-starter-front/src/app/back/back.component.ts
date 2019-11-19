import { Component, Injector, OnInit } from '@angular/core';

import { ComponentBase } from '../_shared/components/component-base';
import { Permission } from '../_shared/permission';

@Component({
  selector: 'app-back',
  templateUrl: './back.component.html',
  styleUrls: ['./back.component.less']
})
export class BackComponent extends ComponentBase implements OnInit {
  isCollapsed = false;
  constructor(protected injector: Injector) {
    super(injector);
  }
  permissions: Permission[] = [];
  ngOnInit() {
    // this.authService.getPermissions().subscribe(res => {
    //     // 将menus形成一个菜单树。
    //     let menusTree = res.filter(a => a.type === 1);
    //     for (const item of menusTree) {
    //         if (item.pid !== 0) {
    //             const pMenu = menusTree.find(c => c.id === item.pid);
    //             if (pMenu) {
    //                 pMenu.children.push(item);
    //             }
    //         }
    //     }
    //     menusTree = menusTree.filter(a => a.pid === 0);
    //     this.permissions = res;
    // });
  }
}
