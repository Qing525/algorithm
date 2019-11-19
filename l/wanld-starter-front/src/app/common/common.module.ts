import { NgModule } from '@angular/core';

import { SharedModule } from '../_shared/shared.module';
import { AboutComponent } from './about/about.component';
import { AppFrontNavComponent } from './app-front-nav/app-front-nav.component';
import { CommonRoutingModule } from './common-routing.module';
import { ConfirmRegistEmailComponent } from './confirm-regist-email/confirm-regist-email.component';
import { ChangePasswordComponent } from './feature/change-password/change-password.component';
import { FeatureComponent } from './feature/feature.component';
import { FindPasswordComponent } from './find-password/find-password.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';

@NgModule({
    declarations: [
        HomeComponent,
        LoginComponent,
        RegisterComponent,
        AppFrontNavComponent,
        AboutComponent,
        FeatureComponent,
        ConfirmRegistEmailComponent,
        FindPasswordComponent,
        ResetPasswordComponent,
        ChangePasswordComponent,


    ],
    imports: [

        SharedModule,
        CommonRoutingModule],
    exports: [],
    providers: []
})
export class CommonModule { }
