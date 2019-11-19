import { BreakpointObserver, Breakpoints } from '@angular/cdk/layout';
import { Injector } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { NzMessageService, NzModalService } from 'ng-zorro-antd';
import { Permission } from '../permission';
import { AuthService } from '../services/auth.service';

export class ComponentBase {
    breakpointXS = false;

    /** 页面加载中 */
    isLoading = false;
    pageSize = 6;
    pageIndex = 1;
    menuTree = Permission.menuTree;

    protected router: Router;
    protected activatedRoute: ActivatedRoute;
    protected authService: AuthService;
    protected nzMessageService: NzMessageService;
    protected nzmodalService: NzModalService;
    protected breakpointObserver: BreakpointObserver;

    constructor(protected injector: Injector) {
        this.router = injector.get(Router);
        this.activatedRoute = injector.get(ActivatedRoute);
        this.nzmodalService = injector.get(NzModalService);
        this.nzMessageService = injector.get(NzMessageService);
        this.authService = injector.get(AuthService);
        this.breakpointObserver = injector.get(BreakpointObserver);
        this.breakpointObserver.observe([Breakpoints.HandsetLandscape, Breakpoints.XSmall]).subscribe(result => {
            this.breakpointXS = result.matches;
        });
    }

    poemString(content): string {
        return content.replace(/<\/?[^>]+(>|$)/g, '');
    }
    poemArray(content): string[] {
        if (!content) {
            return [];
        }
        return content.split('<br>');
    }
}
