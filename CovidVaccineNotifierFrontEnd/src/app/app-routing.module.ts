import { NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { AddUserDetailComponent } from './add-user-detail/add-user-detail.component';

const routes: Routes = [
  {path:'adduser', component:AddUserDetailComponent},
  {path:'', redirectTo: 'adduser', pathMatch: 'full'}
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
