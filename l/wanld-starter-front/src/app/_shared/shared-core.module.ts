import { HttpClientModule } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { FormsModule, ReactiveFormsModule } from '@angular/forms';
import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { RouterModule } from '@angular/router';
import { NgZorroAntdModule } from 'ng-zorro-antd';

import { AppRoutingModule } from '../app-routing.module';
import { ServiceProxyModule } from './services/service-proxy.module';
import { SharedCommonModule } from './shared-common.module';

// primeng
@NgModule({
    imports: [
        BrowserModule,
        BrowserAnimationsModule,
        HttpClientModule,
        FormsModule,
        ReactiveFormsModule,
        RouterModule,
        AppRoutingModule,
        NgZorroAntdModule,
        ServiceProxyModule,
        SharedCommonModule
    ],
    exports: [
        BrowserModule,
        BrowserAnimationsModule,
        HttpClientModule,
        FormsModule,
        ReactiveFormsModule,
        RouterModule,
        AppRoutingModule,
        NgZorroAntdModule,
        ServiceProxyModule,
        SharedCommonModule
    ]
})

/**
 *  为顶级根模块所引用的公共模块
 */
export class SharedCoreModule {}
