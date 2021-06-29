import {Component, OnInit, ViewChild, ViewEncapsulation} from '@angular/core';
import {Match} from "../../../modele/match";
import {ModalDirective} from "ngx-bootstrap/modal";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {MatchService} from "../../../service/match.service";
import {EquipeService} from "../../../service/equipe.service";

@Component({
  selector: 'app-liste-matchs',
  templateUrl: './liste-matchs.component.html',
  styleUrls: ['./liste-matchs.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class ListeMatchsComponent implements OnInit {
  matchs: Match[]=[];
  pager: any;
  showSearch: boolean = false;
  @ViewChild('largeModal') public largeModal: ModalDirective;
  @ViewChild('smallModal') public smallModal: ModalDirective;
  nouveau: boolean;
  recherche: FormGroup;

  constructor(private builder: FormBuilder, private matchService: MatchService,private equipeService: EquipeService) {
    this.recherche = this.builder.group({
      equipe: new FormControl('', []),
      date: new FormControl('', []),
      fini: new FormControl(1, [])
    });
  }

  ngOnInit() {
  }

  ajouter() {
    
  }
  

  countMatch() {
    
  }

  setPage(number: number) {
    
  }

  afficheSearch() {
    if (this.showSearch) {
      this.showSearch = false;
    } else {
      this.showSearch = true;
    }
  }
}
