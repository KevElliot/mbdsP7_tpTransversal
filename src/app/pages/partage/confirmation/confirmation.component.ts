import {Component, Input, OnInit, ViewEncapsulation} from '@angular/core';
import {NgbActiveModal} from "@ng-bootstrap/ng-bootstrap";

@Component({
  selector: 'app-confirmation',
  templateUrl: './confirmation.component.html',
  styleUrls: ['./confirmation.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class ConfirmationComponent implements OnInit {

  @Input() title: string;
  @Input() message: string;
  @Input() object: any;
  @Input() btnOkText: string;
  @Input() btnCancelText: string;
  constructor(private activeModal: NgbActiveModal) { }

  ngOnInit() {
  }
  public decline() {
    this.activeModal.close(false);
  }

  public accept() {
    this.activeModal.close(true);
  }

  public dismiss() {
    this.activeModal.dismiss();
  }

}
