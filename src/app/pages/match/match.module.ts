import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';
import { ListeEquipesComponent } from './liste-equipes/liste-equipes.component';
import { ListeMatchsComponent } from './liste-matchs/liste-matchs.component';
import {NgxDatatableModule} from "@swimlane/ngx-datatable";
import { Ng2SmartTableModule } from 'ng2-smart-table';
import {RouterModule} from "@angular/router";
import {FormsModule, ReactiveFormsModule} from "@angular/forms";
import {NgbModule} from "@ng-bootstrap/ng-bootstrap";
import { ProchainsComponent } from './prochains/prochains.component';
import {InMemoryWebApiModule} from "angular-in-memory-web-api";
import {MultiselectDropdownModule} from "angular-2-dropdown-multiselect";
import {NgxPaginationModule} from "ngx-pagination";
import {PipesModule} from "../../theme/pipes/pipes.module";
import {ModalModule} from "ngx-bootstrap";

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
        MultiselectDropdownModule,
        NgxPaginationModule,
        NgbModule,
        ModalModule.forRoot(),
        FormsModule,
        RouterModule.forChild(routes),
        PipesModule
    ],
  declarations: [ListeEquipesComponent, ListeMatchsComponent, ProchainsComponent]
})
export class MatchModule { }
