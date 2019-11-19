import { LayoutModule } from '@angular/cdk/layout';
import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { RouterModule } from '@angular/router';
import { NgZorroAntdModule } from 'ng-zorro-antd';
import { ViserModule } from 'viser-ng';

import { DateDiffPipe } from './pips/date-diff.pipe';
import { GenderPipe } from './pips/gender.pipe';
import { SafeHtmlPipe } from './pips/safe-html.pipe';
import { SharedCommonModule } from './shared-common.module';

/**
 * 此公共模块只能由特性模块导入。
 * 包含第三方插件
 */
@NgModule({
    imports: [
        CommonModule,
        FormsModule,
        RouterModule,
        ReactiveFormsModule,
        NgZorroAntdModule,
        SharedCommonModule
    ],
    declarations: [DateDiffPipe, SafeHtmlPipe, GenderPipe],

    exports: [
        CommonModule,
        FormsModule,
        ReactiveFormsModule,
        NgZorroAntdModule,
        ViserModule,
        DateDiffPipe,
        SafeHtmlPipe,
        GenderPipe,
        SharedCommonModule,
        LayoutModule
    ]
})
export class SharedModule {}
