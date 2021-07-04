import { Component, OnInit, ViewChild, ElementRef, Renderer2 } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MatCard } from '@angular/material/card';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { ApiService } from '../../shared/apiGrails.service';
import { AuthService } from '../../shared/auth.service';
import { Match } from '../../model/match.model';
import { Paris } from '../../model/paris.model';
import { DetailsParis } from '../../model/detailsParis.model';
import { MatSlideToggleModule } from '@angular/material/slide-toggle';
import { Login } from '../../model/login.model';

@Component({
  selector: 'app-detail-parie',
  templateUrl: './detail-parie.component.html',
  styleUrls: ['./detail-parie.component.css']
})
export class DetailParieComponent implements OnInit {
  //myFormGroup:FormGroup;
  myFormGroup: FormGroup = this.fb.group({});
  idParie = 0;
  idUser = sessionStorage.getItem("userActive");
  jetonUser = sessionStorage.getItem("jetons");
  data = [] //fitambarany données rehetra
  lastData = [] //données farany natsofoka
  toogled = false;
  match: Match[];
  paris: Paris;
  detailsParis: DetailsParis[] = [];
  matchParier: Match[];
  totalMise: number = 0;
  pariGrouper = false;
  placerCliquer = false;

  constructor(private route: ActivatedRoute,
    private renderer: Renderer2,
    private apiService: ApiService,
    private authService: AuthService,
    private fb: FormBuilder,
    private router: Router,
  ) { }

  ngOnInit(): void {
    const id: number = +this.route.snapshot.params.id;
    this.idParie = id;

    this.getAllMatch();
  }
  getAllMatch() {
    this.apiService.listeMatch().subscribe((match) => {
      this.match = match;
    });
  }
  visibilityPari(id, idMatch, nom, cote, prono) {
    this.lastData = [{ "id": id, "nom": nom, "cote": cote, "prono": prono, "jeton": 1, "gain": cote }];
    var e = this.lastData[0].id;
    if (this.data.find(item => item.id === e)) {
      this.data.forEach((element, index) => {
        if (element.id == e) {
          this.totalMise -= this.data[index].jeton;
          this.myFormGroup.removeControl('jeton' + element.id);
          this.myFormGroup.removeControl('gain' + element.id);
          delete this.data[index];//mamafa anle card
        }
      });
      this.detailsParis.forEach((element, index) => {
        if (element.id == e) delete this.detailsParis[index]; //mamafa anle donnee rehevo miala le card
      });
      this.data = this.data.filter(function (newData) {
        return newData != null;
      });
      this.detailsParis = this.detailsParis.filter(function (newData) {
        return newData != null;
      });
    } else {
      this.toogled = true;
      this.data.unshift(this.lastData[0]);//manampy card + creation formGroup
      this.totalMise += this.data[0].jeton;
      var detail: DetailsParis = new DetailsParis();
      detail.id = id;
      var match_ex = new Match();
      match_ex.id = this.match[idMatch].id;
      detail.match = match_ex;
      detail.prono = prono;
      this.detailsParis.push(detail); //manampy detailsParis
      // console.log("detailsParis-- "+JSON.stringify(this.detailsParis));
      this.data.forEach(params => {
        var gain = params.jeton * params.cote;
        this.myFormGroup.addControl('gain' + params.id, this.fb.control(gain, [Validators.required]));
        this.myFormGroup.addControl('jeton' + params.id, this.fb.control(1, [Validators.required]));
      })
    }
  }
  toogle() {
    if (this.toogled) { this.toogled = false }
    else { this.toogled = true }
  }
  placerParie() {
    console.log(this.totalMise);
    console.log(this.jetonUser);
    
    if (this.totalMise<=+this.jetonUser) {
      this.placerCliquer = true;
      if (this.pariGrouper) {
        this.paris = new Paris();
        if (this.data.length == 1) {
          alert("Vous aviez qu'un seul pari, veuillez enlever le pari grouper ou ajouter d'autre pari.");
        }
        this.paris.idclient = this.idUser;
        this.paris.detailsparis = this.detailsParis;
        this.paris.mise = this.totalMise;
        console.log("donne pour pari----- " + JSON.stringify(this.paris));
        this.apiService.placerPari(this.paris).subscribe(
          reponse => {
            //console.log("nahazo valiny "+reponse);
            this.router.navigate(["/historique"]);
          }, error => {
            console.log("error " + error);
          });
      } else {
        var detail1: DetailsParis[] = [];
        for (var i = 0; i < this.data.length; i++) {
          this.paris = new Paris();
          this.paris.idclient = this.idUser;
          detail1.push(this.detailsParis[i]);
          this.paris.detailsparis = detail1;
          this.paris.mise = this.data[i].jeton;
          this.apiService.placerPari(this.paris).subscribe(
            reponse => {
              //console.log("nahazo valiny "+reponse);
              this.router.navigate(["/historique"]);
            }, error => {
              console.log("error " + error);
            });
        }
      }
    }else{
      alert("Votre jetons est inferieur à votre mise! faite une demande de jeton");
    }
  }
  onSubmit(id) {
    this.totalMise = 0;
    //manova anle gain par rapport anle jetons button Changer
    //id eto le id anle button fa tsy index anle tableau data tandremo
    var indexData = this.data.map(e => e.id).indexOf(id);
    var jeton = this.myFormGroup.get('jeton' + id) as FormControl;
    this.data[indexData].jeton = +jeton.value;
    this.data.forEach(element => this.totalMise += element.jeton);
    var gain = (this.data[indexData].cote * jeton.value)
    this.myFormGroup.controls['gain' + id].setValue(gain);
    this.data[indexData].gain = gain;
    var total = 0;
  }
  onChange() {
    if (this.pariGrouper == false) {
      this.pariGrouper = true;
    } else {
      this.pariGrouper = false;
    }
  }
}

