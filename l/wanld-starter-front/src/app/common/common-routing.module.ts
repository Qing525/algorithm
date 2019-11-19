import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';

import { Menu } from '../_shared/permission';
import { AboutComponent } from './about/about.component';
import { ConfirmRegistEmailComponent } from './confirm-regist-email/confirm-regist-email.component';
import { FeatureComponent } from './feature/feature.component';
import { FindPasswordComponent } from './find-password/find-password.component';
import { HomeComponent } from './home/home.component';
import { LoginComponent } from './login/login.component';
import { RegisterComponent } from './register/register.component';
import { ResetPasswordComponent } from './reset-password/reset-password.component';



export const routes: Routes = [
    { path: 'home', component: HomeComponent },
    { path: 'login', component: LoginComponent },
    { path: 'register', component: RegisterComponent },
    { path: 'about', component: AboutComponent },
    { path: 'feature', component: FeatureComponent },
    { path: 'confirm-regist-email', component: ConfirmRegistEmailComponent },
    { path: 'find-password', component: FindPasswordComponent },
    { path: 'reset-password', component: ResetPasswordComponent },
    { path: '', redirectTo: 'home', pathMatch: 'full' }

];


@NgModule({
    imports: [RouterModule.forChild(routes)],
    exports: [RouterModule]
})

export class CommonRoutingModule { }

