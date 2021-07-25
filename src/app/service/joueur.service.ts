import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {Observable} from "rxjs";
import {Equipe} from "../modele/equipe";
import {DemandeJeton} from "../modele/demande-jeton";
import {Capital} from "../modele/capital";

@Injectable({
  providedIn: 'root'
})
export class JoueurService {

  constructor(private http: HttpClient) { }
  getDemandeJeton(): Observable<DemandeJeton[]> {
    return this.http.get<DemandeJeton[]>('pariBack/jeton/demande');
  }
  validerjeton(demande: DemandeJeton) {
    return this.http.put('pariBack/jeton/statut', demande);
  }
  validerParis(capitals: Capital[]) {
    return this.http.put('pariBack/user/gain', capitals);
  }
}
