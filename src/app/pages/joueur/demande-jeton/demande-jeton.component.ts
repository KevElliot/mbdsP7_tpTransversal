import {Component, OnInit, ViewEncapsulation} from '@angular/core';
import {MatchService} from "../../../service/match.service";
import {JoueurService} from "../../../service/joueur.service";
import {DemandeJeton} from "../../../modele/demande-jeton";
import {ToastrService} from "ngx-toastr";
import {ConfirmationService} from "../../../service/confirmation.service";


@Component({
    selector: 'app-demande-jeton',
    templateUrl: './demande-jeton.component.html',
    styleUrls: ['./demande-jeton.component.scss'],
    encapsulation: ViewEncapsulation.None
})
export class DemandeJetonComponent implements OnInit {

    demandes: DemandeJeton[] = [];

    constructor(private joueurService: JoueurService,public toastrService: ToastrService,public confirmationService: ConfirmationService) {
    }

    ngOnInit() {
      this.getDemandes();
    }

    public getDemandes() {
        this.joueurService.getDemandeJeton().subscribe((data) => {
            this.demandes = data;
        },(error)=>{

        },()=>{

        })
    }

  valider(demande: DemandeJeton) {
        var dem= new DemandeJeton();
        dem._id=demande._id;
        dem.statut=true;
      this.joueurService.validerjeton(dem).subscribe((data) => {
          this.getDemandes()
      },(error)=>{
          this.toastrService.error('Une errer s\'est produite');
      },()=>{
          this.toastrService.success('Demande de jeton validée');
      })
  }
  validation(demande: DemandeJeton){
     this.confirmationService.confirm('Validation demande de jeton', 'Voulez vous vraiment valider cette demande?', demande)
          .then((confirmed) =>
              this.valider(demande)
          )
  }
}
