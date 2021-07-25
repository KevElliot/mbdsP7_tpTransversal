import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ConfirmationComponent } from './confirmation/confirmation.component';
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";

@NgModule({
  imports: [
    NgbModule.forRoot()
  ],
  declarations: [ConfirmationComponent],
  exports: [ConfirmationComponent]
})
export class PartageModule { }
