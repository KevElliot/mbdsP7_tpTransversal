import { Injectable } from '@angular/core';
import {ConfirmationComponent} from "../pages/partage/confirmation/confirmation.component";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";

@Injectable({
  providedIn: 'root'
})
export class ConfirmationService {

  constructor(private modalService: NgbModal) { }

  public confirm(
      title: string,
      message: string,
      object: any,
      btnOkText: string = 'OK',
      btnCancelText: string = 'Annuler',
      dialogSize: 'sm'|'lg' = 'sm'): Promise<boolean> {
    const modalRef = this.modalService.open(ConfirmationComponent, { size: dialogSize });
    modalRef.componentInstance.title = title;
    modalRef.componentInstance.message = message;
    modalRef.componentInstance.btnOkText = btnOkText;
    modalRef.componentInstance.object = object;
    modalRef.componentInstance.btnCancelText = btnCancelText;

    return modalRef.result;
  }
}
