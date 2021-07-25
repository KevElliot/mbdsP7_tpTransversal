import {Component, OnInit, ViewChild, ViewEncapsulation} from '@angular/core';
import {NgbModal, NgbModalRef} from "@ng-bootstrap/ng-bootstrap";
import {FormBuilder, FormGroup, Validators} from "@angular/forms";
import {ToastrService} from "ngx-toastr";
import {MenuService} from "../../../theme/components/menu/menu.service";
import {MatchService} from "../../../service/match.service";
import {Match} from "../../../modele/match";
import {Equipe} from "../../../modele/equipe";
import {EquipeService} from "../../../service/equipe.service";
import {DatePipe} from "@angular/common";
import {InfoResultat} from "../../../modele/info-resultat";
import {ModalDirective} from "ngx-bootstrap";
import {JoueurService} from "../../../service/joueur.service";

@Component({
    selector: 'app-prochains',
    templateUrl: './prochains.component.html',
    styleUrls: ['./prochains.component.scss'],
    encapsulation: ViewEncapsulation.None
})
export class ProchainsComponent implements OnInit {
    tableauLieu=[{"ville":"Paris","lat":48.8534,"lng":2.3488,"zoom":7},{"ville":"Madrid","lat":40.4167,"lng":-3.70325,"zoom":7},{"ville":"Munich","lat":48.1351253,"lng":11.5819806,"zoom":7},{"ville":"Liverpool","lat":53.4083714,"lng":-2.9915726,"zoom":7}];
    public matchs: Match[] = [];
    public equipes: Equipe[] = [];
    public searchText: string;
    public p: any;
    public type: string = 'grid';
    public modalRef: NgbModalRef;
    public form: FormGroup;
    public formResultat: FormGroup;
    load: boolean = false;
    editResult: boolean = true;
    infoResultat : InfoResultat = new InfoResultat();
    @ViewChild('smallModal') public smallModal: ModalDirective;
    match: Match;
    loadPaiement: boolean =false;
    loadList: boolean = false;
    equipe1: Equipe;
    equipe2: Equipe;
    constructor(public fb: FormBuilder,
                public toastrService: ToastrService,
                public modalService: NgbModal, private matchService: MatchService, private equipeService: EquipeService,private joueurService : JoueurService, public datepipe: DatePipe) {


    }

    ngOnInit() {
        this.getMatchs();

        this.form = this.fb.group({
            id: null,
            equipe1: [null, Validators.required],
            equipe2: [null, Validators.required],
            cotev1: [null, Validators.required],
            cotex: [null, Validators.required],
            cotev2: [null, Validators.required],
            datematch: [null, Validators.required],
            lieumatch: [null, Validators.required]
        },{validator: validatorVS('equipe1', 'equipe2')});
        this.formResultat = this.fb.group({
            id: null,
            but1: [0, Validators.required],
            but2: [0, Validators.required],
        });
    }


    public getMatchs(): void {
        this.loadList=true;
        this.matchService.getMatchsASuivre().subscribe(matchs => {
                this.matchs = matchs;
                this.getEquipes();
                this.editResult = true;
            }
        );
    }

    public getEquipes(): void {
        this.equipeService.getEquipes().subscribe(equipes =>{
                this.equipes = equipes;
                this.loadList=false;
        });
    }

    public addMatch(match: Match) {
        match.fini = 0;
        this.matchService.ajoutMatch(match).subscribe(user => {
            this.getMatchs();
        }, (error) => {
            this.toastrService.error('une erreur s\'est produite')
        }, () => {
            this.closeModal();
            this.toastrService.success('Match enregistré avec succès');
        });
    }

    public updateMatch(match: Match) {
        match.fini = 0;
        this.matchService.modifMatch(match).subscribe(user => {
            this.getMatchs();
        }, (error) => {
            this.toastrService.error('une erreur s\'est produite')
        }, () => {
            this.closeModal();
            this.toastrService.success('Match enregistré avec succès');
        });
    }

    public toggle(type) {
        this.type = type;
    }

    public openMenuAssign(event, match: Match) {
        let parent = event.target.parentNode;
        this.match = match;
        this.formResultat.get('id').setValue(this.match.id);
        while (parent) {
            parent = parent.parentNode;
            if (parent.classList.contains('content')) {
                parent.classList.add('flipped');
                parent.parentNode.parentNode.classList.add('z-index-1');
                break;
            }
        }
        this.editResult = false;
    }

    public closeMenuAssign(event) {
        this.editResult = true;
        let parent = event.target.parentNode;
        while (parent) {
            parent = parent.parentNode;
            if (parent.classList.contains('content')) {
                parent.classList.remove('flipped');
                parent.parentNode.parentNode.classList.remove('z-index-1');
                break;
            }
        }
    }


    public openModal(modalContent, match) {

        if (match) {
            this.match = match;
            this.form.get('id').setValue(this.match.id);
            this.form.get('equipe1').setValue(this.match.equipe1.id);
            this.form.get('equipe2').setValue(this.match.equipe2.id);
            this.form.get('cotev1').setValue(this.match.cotev1);
            this.form.get('cotex').setValue(this.match.cotex);
            this.form.get('cotev2').setValue(this.match.cotev2);
            this.form.get('lieumatch').setValue(this.match.lieumatch);
            let datematch = new Date(this.match.datematch);
            let date = this.datepipe.transform(datematch, 'yyyy-MM-ddTHH:mm');
            this.form.get('datematch').setValue(date);
        } else {
            this.match = new Match();
        }
        this.modalRef = this.modalService.open(modalContent, {container: '.app'});

        this.modalRef.result.then((result) => {
            this.form.reset();
        }, (reason) => {
            this.form.reset();
        });
    }

    public closeModal() {
        this.modalRef.close();
    }

    public onSubmit(match: Match): void {
        let equipe1 = new Equipe();
        equipe1.id = this.form.get('equipe1').value;
        let equipe2 = new Equipe();
        equipe2.id = this.form.get('equipe2').value;
        match.equipe1 = equipe1;
        match.equipe2 = equipe2;
        let datematch = new Date(this.form.get('datematch').value);
        match.datematch = datematch;
        console.log(JSON.stringify(match));
        if (this.form.valid) {
            if (match.id) {
                this.updateMatch(match);
            } else {
                this.addMatch(match);
            }
        }
    }

    onSubmitResultat(match: Match) {
        this.load = true;
        match.id=this.match.id
        console.log(JSON.stringify(match));
        console.log(JSON.stringify(this.formResultat.value));
        this.matchService.modifResultat(match).subscribe(info => {
            this.getMatchs();
            this.infoResultat = info;
        }, (error) => {
            this.load = false;
            this.toastrService.error('une erreur s\'est produite')
        }, () => {
            this.load = false;
            this.toastrService.success('Fin du match');
            this.smallModal.show();
            this.formResultat.reset();
        });
    }

    Ok() {
        this.loadPaiement = true;
        this.joueurService.validerParis(this.infoResultat.capitals).subscribe(info => {
            this.getMatchs();
        }, (error) => {
            this.loadPaiement = false;
            this.toastrService.error('une erreur s\'est produite')
        }, () => {
            this.loadPaiement = false;
            this.smallModal.hide();
            this.toastrService.success('Match validé');
        });
    }

    setEquipe(number: number) {
        if(number==1){
            this.equipe1=this.equipes.filter(value => value.id==+this.form.get('equipe1').value)[0]
        }else{
            this.equipe2=this.equipes.filter(value => value.id==+this.form.get('equipe2').value)[0]
        }
    }
}
export function validatorVS(equipe1: string, equipe2: string) {
    return (group: FormGroup) => {
        let eq1= group.controls[equipe1];
        let eq2= group.controls[equipe2];
        if (eq1.value == eq2.value) {
            return eq2.setErrors({vs: true})
        }
    }
}
