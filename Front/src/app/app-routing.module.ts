import { Component, NgModule } from '@angular/core';
import { RouterModule, Routes } from '@angular/router';
import { BodyAdminComponent } from './BackOffice/body-admin/body-admin.component';
import { AllTemplatesAdminComponent } from './BackOffice/all-templates-admin/all-templates-admin.component';
import { AllTemplateUserComponent } from './FrontOffice/all-template-user/all-template-user.component';
import { BodyUserComponent } from './FrontOffice/body-user/body-user.component';
import {LoginComponent} from "./login/login.component";
import {AuthenticationGuard} from "./guards/authentication.guard";
import {ProfileComponent} from "./User/profile/profile.component";
import {AuthorizationGuard} from "./guards/authorization.guard";
import {NotAuthorizedComponent} from "./not-authorized/not-authorized.component";
import { UsersComponent } from './User/users/users.component';
import { CoursesComponent } from './courses/courses.component';
import { CareerComponent } from './career/career.component';
import { OffreComponent } from './offre/offre.component';

const routes: Routes = [
  {
    path: 'login',
    component: LoginComponent
  },
  {
    path: 'admin',
    component: AllTemplatesAdminComponent,canActivate:[AuthenticationGuard],data :{role:"ADMIN"},
    children: [
      {
        path: 'home',
        component: BodyAdminComponent,
      },
      {
        path: 'profile',
        component: ProfileComponent,
      },
      {
        path:'users',
        component:UsersComponent,
      },
      {
        path:'courses',
        component:CoursesComponent,
      },
      {
        path:'carrers',
        component:CareerComponent,
      },
      {
        path:'jobs',
        component:OffreComponent,
      }
    ]
  },
  {
    path: 'user',
    component: AllTemplateUserComponent,canActivate:[AuthenticationGuard],data :{role:"USER"},
    children: [
      {
        path: 'profile',
        component: ProfileComponent,
      },
      {
        path: 'home',
        component: BodyUserComponent,
      }
    ]
  },
  {
    path: '**',
    redirectTo: "/login"
  }
];

@NgModule({
  imports: [RouterModule.forRoot(routes)],
  exports: [RouterModule]
})
export class AppRoutingModule { }
