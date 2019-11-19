import { Component, Injector, OnInit } from '@angular/core';
import { ComponentPageBase } from 'src/app/_shared/components/component-page-base';
import { RoleDto, RoleService } from 'src/app/_shared/services/back/role.service';

@Component({
    templateUrl: './back-role.component.html',
    styleUrls: ['../../../styles/back-role.component.less']
})
export class BackRoleComponent extends ComponentPageBase<{}> implements OnInit {
    pageSize = 10;
    constructor(protected injector: Injector, protected roleService: RoleService) {
        super(injector);
    }

    ngOnInit(): void {
        this.getAll();
    }

    getAll(reset: boolean = false) {
        if (reset) {
            this.pageIndex = 1;
        }
        this.roleService.getAll(this.pageIndex, this.pageSize).subscribe(res => {
            this.dataSet = res;
        });
    }

    delete(role: RoleDto) {
        this.nzmodalService.confirm({
            nzTitle: '删除确认',
            nzContent: '您确定要删除该角色吗',
            nzOnOk: () => {
                this.roleService.delete(role.id).subscribe(
                    res => {
                        this.getAll();
                    },
                    () => {
                        this.nzMessageService.error('删除失败！');
                    }
                );
            }
        });
    }
}
