import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { AppRoutingModule } from './app-routing.module';
import { AppComponent } from './app.component';
import { HeaderAdminComponent } from './BackOffice/header-admin/header-admin.component';
import { BodyAdminComponent } from './BackOffice/body-admin/body-admin.component';
import { FotterAdminComponent } from './BackOffice/fotter-admin/fotter-admin.component';
import { SideBarAdminComponent } from './BackOffice/side-bar-admin/side-bar-admin.component';
import { AllTemplatesAdminComponent } from './BackOffice/all-templates-admin/all-templates-admin.component';
import { AllTemplateUserComponent } from './FrontOffice/all-template-user/all-template-user.component';
import { BodyUserComponent } from './FrontOffice/body-user/body-user.component';
import { FotterUserComponent } from './FrontOffice/fotter-user/fotter-user.component';
import { HeaderUserComponent } from './FrontOffice/header-user/header-user.component';
import { LoginComponent } from './login/login.component';
import {FormsModule} from "@angular/forms";
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {AppHttpInterceptor} from "./Interceptors/app-http.interceptor";
import {MatSnackBarModule} from "@angular/material/snack-bar";
import { ProfileComponent } from './User/profile/profile.component';
import { NotAuthorizedComponent } from './not-authorized/not-authorized.component';
import { UsersComponent } from './User/users/users.component';
import { CoursesComponent } from './courses/courses.component';
import { CareerComponent } from './career/career.component';
import { OffreComponent } from './offre/offre.component';

@NgModule({
  declarations: [
    AppComponent,
    HeaderAdminComponent,
    BodyAdminComponent,
    FotterAdminComponent,
    SideBarAdminComponent,
    AllTemplatesAdminComponent,
    AllTemplateUserComponent,
    BodyUserComponent,
    FotterUserComponent,
    HeaderUserComponent,
    LoginComponent,
    ProfileComponent,
    NotAuthorizedComponent,
    UsersComponent,
    CoursesComponent,
    CareerComponent,
    OffreComponent
  ],
  imports: [
    BrowserModule,
    AppRoutingModule,
    FormsModule,
    HttpClientModule,
    MatSnackBarModule,

  ],
  providers: [
    {provide : HTTP_INTERCEPTORS , useClass : AppHttpInterceptor , multi  : true}
  ],
  bootstrap: [AppComponent]
})
export class AppModule { }
