import { Component, OnInit, Injector } from '@angular/core';

import { ComponentBase } from 'src/app/_shared/components/component-base';

@Component({
    selector: 'app-result-page',
    templateUrl: './result-page.component.html'
})
export class ResultPageComponent extends ComponentBase implements OnInit {
    // 从 params 里查询任务创建成功与否的状态
    isSuccess = true;
    id: string;
    title: string;
    constructor(protected injector: Injector) {
        super(injector);
    }
    ngOnInit() {
        this.activatedRoute.queryParams.subscribe(res => {
            this.id = res.id;
            this.title = res.title;
        });
    }
}
