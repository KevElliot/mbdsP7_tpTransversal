import { Component, OnInit } from '@angular/core';
import { ApiService } from '../../shared/apiGrails.service';
import { Historique } from '../../model/historique.model';

@Component({
  selector: 'app-historique-parie',
  templateUrl: './historique-parie.component.html',
  styleUrls: ['./historique-parie.component.css']
})
export class HistoriqueParieComponent implements OnInit {
  idUser = sessionStorage.getItem("userActive");
  historique:Historique[];
  constructor(private apiService: ApiService) { }

  ngOnInit(): void {
    this.getHistoriqueParis();
  }
  getHistoriqueParis(){
    this.apiService.historiqueMatch(this.idUser).subscribe((hist) => {
      this.historique = hist;
      console.log(this.historique);
    });
  }
}
