import { HTTP_INTERCEPTORS } from '@angular/common/http';
import { NgModule } from '@angular/core';
import { CookieService } from 'ngx-cookie-service';

import { AccountService } from './account.service';
import { AuthGuardPermission } from './auth-guard-permission.service';
import { AuthGuard } from './auth-guard.service';
import { AuthInterceptor } from './auth-interceptor.service';
import { AuthService } from './auth.service';

@NgModule({
    providers: [
        { provide: HTTP_INTERCEPTORS, useClass: AuthInterceptor, multi: true },
        AuthGuard,
        AuthGuardPermission,
        AuthService,
        CookieService,
        AccountService,
    ]
})


export class ServiceProxyModule {


}
