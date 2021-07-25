import { Component, ViewEncapsulation } from '@angular/core';
import {FormBuilder, FormGroup} from "@angular/forms";
import {ToastrService} from "ngx-toastr";
import {NgbModal} from "@ng-bootstrap/ng-bootstrap";
import {MatchService} from "../../../service/match.service";
import {EquipeService} from "../../../service/equipe.service";
import {JoueurService} from "../../../service/joueur.service";
import {DatePipe} from "@angular/common";

@Component({
  selector: 'app-google-maps',
  templateUrl: './google-maps.component.html',
  encapsulation: ViewEncapsulation.None
})
export class GoogleMapsComponent{
  tableauLieu=[{"ville":"Paris","lat":48.8534,"lng":2.3488,"zoom":7},{"ville":"Madrid","lat":40.4167,"lng":-3.70325,"zoom":7},{"ville":"Munich","lat":48.1351253,"lng":11.5819806,"zoom":7},{"ville":"Liverpool","lat":53.4083714,"lng":-2.9915726,"zoom":7}];
  lat: number = 48.8534;
  lng: number = 2.3488;
  zoom: number = 7;
  public form: FormGroup;
  constructor(public fb: FormBuilder) {
    this.form = this.fb.group({
      ville: "Paris",
    })
  }

  map() {
    let ville = this.tableauLieu.filter(value => value.ville==this.form.get('ville').value)[0]
    this.lat=ville.lat;
    this.lng=ville.lng;
  }
}
