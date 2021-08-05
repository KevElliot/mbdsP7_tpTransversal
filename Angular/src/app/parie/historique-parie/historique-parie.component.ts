import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subject } from 'rxjs';
import { DataTablesModule } from 'angular-datatables';
import { ApiService } from '../../shared/apiGrails.service';
import { Historique } from '../../model/historique.model';
import { Paris } from '../../model/paris.model';
import { DetailsParis } from '../../model/detailsParis.model';

@Component({
  selector: 'app-historique-parie',
  templateUrl: './historique-parie.component.html',
  styleUrls: ['./historique-parie.component.css']
})
export class HistoriqueParieComponent implements OnInit {
  idUser = sessionStorage.getItem("userActive");
  historique: Historique[];
  detailHist: Historique;
  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<any> = new Subject();
  pari:Paris;
  showData=false;
  coteglobal:number;
  mise:number;
  gainpossible:number;
  data = []; //detail Historique apotra any aminy modal 
  
  constructor(private apiService: ApiService) { }

  ngOnInit(): void {
    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 5,
      processing: true
    };
    this.getHistoriqueParis();
  }
  getHistoriqueParis() {
    this.apiService.historiqueMatch(this.idUser).subscribe((hist) => {
      this.historique = hist;
      this.showData =true;
      this.dtTrigger.next();
      console.log(this.historique);
    }, error => {
      //window.location.reload();
      console.log("Verrifier le serveur");
    });
  }

  getHistoriqueParisById(idMatch:number) {
    this.data =[];
    var e = idMatch;
    this.historique.forEach((element, index) => {
      if (element.id == e){
        this.detailHist = element;
      }
    });
    for(var i=0;i<+this.detailHist.nbmatch;i++){
      var nom=this.detailHist.detailsparis[i].match.equipe1.nom+"/"+this.detailHist.detailsparis[i].match.equipe2.nom;
      var cotev1 = this.detailHist.detailsparis[i].match.cotev1;  
      var cotev2= this.detailHist.detailsparis[i].match.cotev2;  
      var cotex= this.detailHist.detailsparis[i].match.cotex; 
      var prono = this.detailHist.detailsparis[i].prono;
      var stat = this.detailHist.detailsparis[i].gain;
      this.coteglobal = this.detailHist.coteglobal;
      this.mise = this.detailHist.mise;
      this.gainpossible = this.detailHist.gainpossible;
      var statut;
      switch (stat) {
        case null: statut="en cours"; break;
        case "KO": statut="Defaite"; break;
        case "OK": statut="Victoire"; break;
      }
      var donnee = [{ "nom": nom, "coteV1": cotev1, "coteX": cotex, "coteV2": cotev2, "prono": prono, "statut": statut}];
      this.data.push(donnee[0]);
    }
    console.log(this.detailHist.coteglobal);
  }
  ngOnDestroy(): void {
    this.dtTrigger.unsubscribe();
  }
}
