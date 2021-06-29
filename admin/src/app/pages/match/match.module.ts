import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListeEquipesComponent } from './liste-equipes/liste-equipes.component';
import { ListeMatchsComponent } from './liste-matchs/liste-matchs.component';
import {NgxDatatableModule} from "@swimlane/ngx-datatable";
import { Ng2SmartTableModule } from 'ng2-smart-table';
import {RouterModule} from "@angular/router";
import {ReactiveFormsModule} from "@angular/forms";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import { ProchainsComponent } from './prochains/prochains.component';

export const routes = [
    { path: '', redirectTo: 'prochains', pathMatch: 'full'},
    { path: 'liste-equipes', component: ListeEquipesComponent, data: { breadcrumb: 'Liste des équipes' } },
    { path: 'liste-matchs', component: ListeMatchsComponent, data: { breadcrumb: 'Liste des matchs' } },
    { path: 'prochains', component: ProchainsComponent, data: { breadcrumb: 'Les prochains matchs' } }
];
@NgModule({
    imports: [
        CommonModule,
        NgxDatatableModule,
        Ng2SmartTableModule,
        NgbModule.forRoot(),
        ReactiveFormsModule,
        RouterModule.forChild(routes)
    ],
  declarations: [ListeEquipesComponent, ListeMatchsComponent, ProchainsComponent]
})
export class MatchModule { }
