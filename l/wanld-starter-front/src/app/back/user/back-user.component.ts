import { Component, Injector, OnInit } from '@angular/core';
import { ComponentPageBase } from 'src/app/_shared/components/component-page-base';
import { UserDto, UserService } from 'src/app/_shared/services/back/user.service';
import { FormControl } from '@angular/forms';
import { debounceTime } from 'rxjs/operators';

@Component({
    templateUrl: './back-user.component.html'
})
export class BackUserComponent extends ComponentPageBase<UserDto> implements OnInit {
    pageSize = 10;
    searchName = new FormControl('');
    sortMap = {};
    constructor(protected injector: Injector, private userService: UserService) {
        super(injector);
    }

    ngOnInit(): void {
        this.getUserPage();
        this.searchName.valueChanges.pipe(debounceTime(500)).subscribe(res => {
            this.getUserPage(true);
        });
    }

    sortData($event: any) {
        this.sortMap = $event;
        this.getUserPage();
    }

    getUserPage(reset: boolean = false) {
        if (reset) {
            this.pageIndex = 1;
        }
        this.isLoading = true;
        this.userService
            .getAllSearch(
                this.pageIndex,
                this.pageSize,
                this.searchName.value,
                this.sortMap['key'],
                this.sortMap['value']
            )
            .subscribe(
                res => {
                    this.dataSet = res.list;
                    this.dataTotal = res.totalCount;
                    this.isLoading = false;
                },
                error => {
                    this.isLoading = false;
                }
            );
    }

    delete(user: UserDto) {
        this.nzmodalService.confirm({
            nzTitle: '删除确认：',
            nzContent: '您确定要删除该用户吗？',
            nzOnOk: () => {
                this.userService.delete(user).subscribe(
                    res => {
                        this.getUserPage();
                    },
                    () => {
                        this.nzMessageService.error('删除失败！');
                    }
                );
            }
        });
    }
}
