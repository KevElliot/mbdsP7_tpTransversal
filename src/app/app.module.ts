import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { Routes, RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { ParieComponent } from './parie/parie.component';
import { LoginComponent } from './login/login.component';
import { HttpClientModule } from '@angular/common/http';
import { NavigationComponent } from './shared/navigation/navigation.component';
import { DetailParieComponent } from './parie/detail-parie/detail-parie.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {DataTablesModule} from 'angular-datatables';
import {MatCardModule} from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import {MatStepperModule} from '@angular/material/stepper';
import { MatInputModule } from '@angular/material/input';
import {MatSlideToggleModule} from '@angular/material/slide-toggle';
import { MatFormFieldModule } from "@angular/material/form-field";
import { HistoriqueParieComponent } from './parie/historique-parie/historique-parie.component';
import { ProfilComponent } from './profil/profil.component';
import { NgxQRCodeModule } from '@techiediaries/ngx-qrcode';
import {MatProgressSpinnerModule} from '@angular/material/progress-spinner';
import { AuthGuard } from './shared/auth.guard';

const routes:Routes = [
  {
    // http://localhost:4200/home
    path:"",
    component:ParieComponent
  },
  {
    path:"login",
    component:LoginComponent
  },
  {
    path:"pari",
    component:DetailParieComponent
  },
  {
    path:"historique",
    component:HistoriqueParieComponent,
    canActivate : [AuthGuard]
  },
  {
    path:"profil/:id",
    component:ProfilComponent,
    canActivate : [AuthGuard]
  }
]

@NgModule({
  declarations: [
    AppComponent,
    ParieComponent,
    LoginComponent,
    NavigationComponent,
    DetailParieComponent,
    HistoriqueParieComponent,
    ProfilComponent
  ],
  imports: [
    BrowserModule,
    HttpClientModule,
    RouterModule.forRoot(routes),
    BrowserAnimationsModule,
    MatCardModule,
    MatButtonModule,MatIconModule,
    FormsModule, ReactiveFormsModule,
    MatInputModule,MatStepperModule,
    MatFormFieldModule,
    MatSlideToggleModule,
    DataTablesModule,
    NgxQRCodeModule,
    MatProgressSpinnerModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
