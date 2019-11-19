import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { AuthGuard } from './_shared/services/auth-guard.service';

const routes: Routes = [
  { path: '', pathMatch: 'full', redirectTo: '' },
  { path: '', loadChildren: () => import('./common/common.module').then(m => m.CommonModule), data: { name: '' } },
  { path: 'b', canActivate: [AuthGuard], loadChildren: () => import('./back/back.module').then(m => m.BackModule) }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule {}
