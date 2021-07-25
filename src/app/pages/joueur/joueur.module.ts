import {NgModule} from '@angular/core';
import {CommonModule} from '@angular/common';
import {DemandeJetonComponent} from './demande-jeton/demande-jeton.component';
import {ListeEquipesComponent} from "../match/liste-equipes/liste-equipes.component";
import {ListeMatchsComponent} from "../match/liste-matchs/liste-matchs.component";
import {ProchainsComponent} from "../match/prochains/prochains.component";
import {ListeJoueurComponent} from './liste-joueur/liste-joueur.component';
import {AttentePaiementComponent} from './attente-paiement/attente-paiement.component';
import {ConfirmationService} from "../../service/confirmation.service";
import {ConfirmationComponent} from "../partage/confirmation/confirmation.component";
import {RouterModule} from "@angular/router";
import {PartageModule} from "../partage/partage.module";

export const routes = [
    {path: '', redirectTo: 'liste-joueur', pathMatch: 'full'},
    {path: 'liste-joueur', component: ListeJoueurComponent, data: {breadcrumb: 'Liste des joueurs'}},
    {path: 'demande-jeton', component: DemandeJetonComponent, data: {breadcrumb: 'Liste des demandes de jeton'}},
    {path: 'attente-paiement', component: AttentePaiementComponent, data: {breadcrumb: 'Liste d\'attente paiement'}}
];

@NgModule({
    imports: [
        CommonModule,
        PartageModule,
        RouterModule.forChild(routes),
    ],
    declarations: [DemandeJetonComponent, ListeJoueurComponent, AttentePaiementComponent],
    entryComponents: [ConfirmationComponent],
    providers: [ConfirmationService]
})
export class JoueurModule {
}
