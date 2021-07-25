import {Component, OnInit, ViewChild, ViewEncapsulation} from '@angular/core';
import {Match} from "../../../modele/match";
import {ModalDirective} from "ngx-bootstrap/modal";
import {FormBuilder, FormControl, FormGroup} from "@angular/forms";
import {MatchService} from "../../../service/match.service";
import {EquipeService} from "../../../service/equipe.service";
import {Equipe} from "../../../modele/equipe";

@Component({
    selector: 'app-liste-matchs',
    templateUrl: './liste-matchs.component.html',
    styleUrls: ['./liste-matchs.component.scss'],
    encapsulation: ViewEncapsulation.None
})
export class ListeMatchsComponent implements OnInit {
    matchs: Match[] = [];
    public equipes: Equipe[] = [];
    pager: any = {};
    showSearch: boolean = false;
    @ViewChild('largeModal') public largeModal: ModalDirective;
    @ViewChild('smallModal') public smallModal: ModalDirective;
    nouveau: boolean;
    recherche: FormGroup;
    load: boolean = false;
    private count = 0;

    constructor(private builder: FormBuilder, private matchService: MatchService, private equipeService: EquipeService) {
        this.recherche = this.builder.group({
            equipe: new FormControl(0, []),
            fini: new FormControl(1, [])
        });
        this.getEquipes();
    }

    ngOnInit() {
        this.countMatch();
    }

    ajouter() {

    }


    countMatch() {
        this.load = true;
        this.matchService.countMatch(+this.recherche.get('equipe').value, +this.recherche.get('fini').value).subscribe((data) => {
            this.count = data;
            this.setPage(0);
        })
    }

    public getEquipes(): void {
        this.equipeService.getEquipes().subscribe(equipes =>
            this.equipes = equipes
        );
    }

    setPage(page: number) {
        console.log('COUNT::' + this.count);
        this.load = true;
        this.pager = this.matchService.getPager(this.count, page);
        this.matchService.filtre(this.recherche.get('equipe').value, this.recherche.get('fini').value, this.pager.startIndex, this.pager.endIndex + 1).subscribe((data) => {
            this.matchs = data;
            this.load = false;
            console.log(JSON.stringify(this.matchs));
        })
    }

    afficheSearch() {
        if (this.showSearch) {
            this.showSearch = false;
        } else {
            this.showSearch = true;
        }
    }

    reinitialiser() {

    }
}
