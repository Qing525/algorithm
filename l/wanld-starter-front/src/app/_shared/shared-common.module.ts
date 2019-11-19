import { CommonModule } from '@angular/common';
import { NgModule } from '@angular/core';
import { RouterModule } from '@angular/router';
import { NgZorroAntdModule } from 'ng-zorro-antd';
import { ReactiveFormsModule, FormsModule } from '@angular/forms';
import { BreadcrumbComponent } from './components/breadcrumb/breadcrumb.component';
import { FooterComponent } from './components/footer/footer.component';
import { NavUserComponent } from './components/nav-user/nav-user.component';

@NgModule({
    imports: [RouterModule, NgZorroAntdModule, CommonModule, ReactiveFormsModule, FormsModule],
    declarations: [NavUserComponent, BreadcrumbComponent, FooterComponent],
    exports: [NavUserComponent, BreadcrumbComponent, FooterComponent]
})
export class SharedCommonModule {}
