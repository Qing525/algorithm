import { registerLocaleData } from '@angular/common';
import zh from '@angular/common/locales/zh';
import { NgModule, LOCALE_ID } from '@angular/core';
import { NZ_I18N, zh_CN } from 'ng-zorro-antd';

import { SharedCoreModule } from './_shared/shared-core.module';
import { AppComponent } from './app.component';
import { AppRoutingModule } from './app-routing.module';

registerLocaleData(zh);

@NgModule({
    declarations: [
        AppComponent
    ],
    imports: [
        SharedCoreModule,
        AppRoutingModule
    ],
    providers: [
        { provide: NZ_I18N, useValue: zh_CN },
        { provide: LOCALE_ID, useValue: 'zh-Hans' }
    ],
    bootstrap: [AppComponent]
})
export class AppModule {



}
