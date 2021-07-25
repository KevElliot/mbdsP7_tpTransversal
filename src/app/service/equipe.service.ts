import { Injectable } from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Equipe} from "../modele/equipe";
import {Observable} from "rxjs";

@Injectable({
  providedIn: 'root'
})
export class EquipeService {

  constructor(private http: HttpClient) { }

  getEquipes(): Observable<Equipe[]> {
    return this.http.get<Equipe[]>('equipeApi/equipes');
  }
  ajoutEquipe(equipe: Equipe) {
    return this.http.post('equipeApi/equipe', equipe);
  }

  modifEquipe(equipe: Equipe) {
    return this.http.put('equipeApi/equipe', equipe);
  }
  deleteEquipe(id: number) {
    const headers = new HttpHeaders();
    headers.append('Content-Type', 'application/json; charset=utf-8');
    return this.http.delete('equipeApi/equipe/' + id, {headers: headers});
  }
}
