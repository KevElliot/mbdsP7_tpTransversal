import { NgModule } from '@angular/core';
import { BrowserModule } from '@angular/platform-browser';
import { Routes, RouterModule } from '@angular/router';

import { AppComponent } from './app.component';
import { ParieComponent } from './parie/parie.component';
import { LoginComponent } from './login/login.component';
import { NavigationComponent } from './shared/navigation/navigation.component';
import { DetailParieComponent } from './parie/detail-parie/detail-parie.component';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import {MatCardModule} from '@angular/material/card';
import { MatButtonModule } from '@angular/material/button';
import {MatIconModule} from '@angular/material/icon';
import { FormsModule,ReactiveFormsModule } from '@angular/forms';
import {MatStepperModule} from '@angular/material/stepper';
import { MatInputModule } from '@angular/material/input';
import { MatFormFieldModule } from "@angular/material/form-field";
import { HistoriqueParieComponent } from './parie/historique-parie/historique-parie.component';
import { ProfilComponent } from './profil/profil.component';

const routes:Routes = [
  {
    path:"",
    component:LoginComponent
  },
  {
    // http://localhost:4200/home
    path:"home",
    component:ParieComponent
  },
  {
    path:"parie/:id",
    component:DetailParieComponent
  },
  {
    path:"historique",
    component:HistoriqueParieComponent
  },
  {
    path:"profil",
    component:ProfilComponent
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
    RouterModule.forRoot(routes),
    BrowserAnimationsModule,
    MatCardModule,
    MatButtonModule,MatIconModule,
    FormsModule, ReactiveFormsModule,
    MatInputModule,MatStepperModule,
    MatFormFieldModule
  ],
  providers: [],
  bootstrap: [AppComponent]
})
export class AppModule { }
