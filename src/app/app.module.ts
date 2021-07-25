import { BrowserModule } from '@angular/platform-browser';
import { BrowserAnimationsModule } from '@angular/platform-browser/animations';
import { NgModule } from '@angular/core';

import { AgmCoreModule } from '@agm/core';

import { routing } from './app.routing';
import { AppSettings } from './app.settings';

import { AppComponent } from './app.component';
import { NotFoundComponent } from './pages/errors/not-found/not-found.component';
import {HTTP_INTERCEPTORS, HttpClientModule} from "@angular/common/http";
import {JwtInterceptor} from "./helpers/jwt-interceptor";
import {ErrorInterceptor} from "./helpers/error-interceptor";
import {ToastrModule} from "ngx-toastr";
import {PartageModule} from "./pages/partage/partage.module";




@NgModule({
  declarations: [
    AppComponent,
    NotFoundComponent
  ],
  imports: [
    BrowserModule,
    BrowserAnimationsModule,
    HttpClientModule,
    PartageModule,
    ToastrModule.forRoot(),
    AgmCoreModule.forRoot({
      apiKey: 'AIzaSyDe_oVpi9eRSN99G4o6TwVjJbFBNr58NxE'
    }),
    routing
  ],
  providers: [ AppSettings,
    { provide: HTTP_INTERCEPTORS, useClass: JwtInterceptor, multi: true },
    { provide: HTTP_INTERCEPTORS, useClass: ErrorInterceptor, multi: true }
    ],
  bootstrap: [ AppComponent ]
})
export class AppModule { }
