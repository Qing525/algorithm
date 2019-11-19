import { Component, OnInit, Injector } from '@angular/core';
import { ActivatedRoute, Router, NavigationEnd } from '@angular/router';
import { filter } from 'rxjs/operators';

@Component({
  selector: 'app-breadcrumb',
  templateUrl: './breadcrumb.component.html'
})
export class BreadcrumbComponent implements OnInit {
  breadcrumbs = [];
  constructor(protected route: ActivatedRoute, private router: Router) {}
  ngOnInit(): void {
    this.router.events
      .pipe(filter(event => event instanceof NavigationEnd))
      .subscribe(event => {
        this.assembleBreadcrumbs();
      });
    this.assembleBreadcrumbs();
  }
  private assembleBreadcrumbs() {
    this.breadcrumbs = [];
    let currentRoute = this.route.root,
      url = '';
    do {
      const childrenRoutes = currentRoute.children;
      currentRoute = null;
      childrenRoutes.forEach(routes => {
        if (routes.outlet === 'primary') {
          const routeSnapshot = routes.snapshot;
          url += '/' + routeSnapshot.url.map(segment => segment.path).join('/');
          if (
            !this.breadcrumbs.find(a => a.title === routes.snapshot.data.title)
          ) {
            this.breadcrumbs.push({
              title: routes.snapshot.data.title,

              url: url
            });
          }
          currentRoute = routes;
        }
      });
    } while (currentRoute);
  }
}
