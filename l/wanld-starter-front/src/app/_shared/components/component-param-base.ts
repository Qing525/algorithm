import { Injector } from '@angular/core';

import { ComponentBase } from './component-base';

export class ComponentParamBase extends ComponentBase {
    id: string | number | undefined;

    constructor(protected injector: Injector) {
        super(injector);
        this.id = this.activatedRoute.snapshot.paramMap.get('id');
    }
}

