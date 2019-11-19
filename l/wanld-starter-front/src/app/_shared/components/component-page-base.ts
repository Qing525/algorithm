import { Injector } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';

import { ComponentBase } from './component-base';

export class ComponentPageBase<T> extends ComponentBase {
    dataTotal = 0;
    dataSet: T[] = [];
    dataSetDisplay: T[] = [];
    sortName = null;
    /** 排序升降序 */
    sortValue = null;
    sortMap: any = null;
    protected cookieService: CookieService;
    constructor(protected injector: Injector) {
        super(injector);
        // 从paramMap查询
        const index = this.activatedRoute.snapshot.paramMap.get('pageIndex');
        const size = this.activatedRoute.snapshot.paramMap.get('pageSize');
        this.pageIndex = index ? parseInt(index, 10) : this.pageIndex;
        this.pageSize = size ? parseInt(size, 10) : this.pageSize;

        // 从queryParam查询
        const i = this.activatedRoute.snapshot.queryParamMap.get('pageIndex');
        const s = this.activatedRoute.snapshot.queryParamMap.get('pageSize');
        this.pageIndex = i ? parseInt(i, 10) : this.pageIndex;
        this.pageSize = s ? parseInt(s, 10) : this.pageSize;
    }

    sortData(sort: { key: string, value: string }): void {
        this.sortName = sort.key;
        this.sortValue = sort.value;

        if (this.sortMap) {
            for (const key in this.sortMap) {
                if (this.sortMap[key]) {
                    this.sortMap[key] = (key === sort.key ? sort.value : null);
                }
            }
        }
        this.filterData();
    }

    filterFun(item: any): any {
        return true;
    }

    filterData(): void {
        const data = this.dataSet.filter(item => this.filterFun(item));
        if (this.sortName && this.sortValue) {
            this.dataSetDisplay = data.sort((a, b) =>
                (this.sortValue === 'ascend') ? (a[this.sortName] > b[this.sortName] ? 1 : -1)
                    : (b[this.sortName] > a[this.sortName] ? 1 : -1));
        } else {
            this.dataSetDisplay = data;

        }
    }

}

