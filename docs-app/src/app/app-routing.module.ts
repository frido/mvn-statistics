import { NgModule } from '@angular/core';
import { Routes, RouterModule } from '@angular/router';
import { LibrabryComponent } from './library.component';
import { StatisticsComponent } from './statistics.component';

const routes: Routes = [
  {path: '', redirectTo: 'libraries', pathMatch: 'full'},
  {path: 'libraries', component: LibrabryComponent},
  {path: 'statistics', component: StatisticsComponent}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
