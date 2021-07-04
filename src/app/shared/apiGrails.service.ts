import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { forkJoin, Observable, of } from 'rxjs';
import { Match } from '../model/match.model';
import { DetailsParis } from '../model/detailsParis.model';
import { Paris } from '../model/paris.model';
import { Historique } from '../model/historique.model';

@Injectable({
    providedIn: 'root'
})
export class ApiService {

    constructor(private http: HttpClient) { }
    
    uri = "https://tpt-server-grails.herokuapp.com/";

    listeMatch():Observable<Match[]> {
        const headers = new HttpHeaders();
        headers.append('accept', 'application/json');
        console.log("Attente des matchs...")
        return this.http.get<Match[]>(this.uri + '/matchApi/matchs',{headers:headers});
    }
    matchById(id:number):Observable<Match> {
        const headers = new HttpHeaders();
        headers.append('accept', 'application/json');
        console.log("Details Matchs");
        return this.http.get<Match>(this.uri + "/matchApi/match/"+id,{headers:headers});
    }
    placerPari(paris:Paris):Observable<any>{
        const headers = new HttpHeaders();
        headers.append('Content-Type', 'application/json');
        console.log("Pari placer");
        return this.http.post(this.uri + "/parisApi/paris",paris,{headers:headers});
    }
    historiqueMatch(idclient:any):Observable<Historique[]> {
        const headers = new HttpHeaders();
        headers.append('accept', 'application/json');
        console.log("HistorriqueMatch en cours...")
        return this.http.get<Historique[]>(this.uri + 'parisApi/listeParis?idclient='+idclient,{headers:headers});
    }
    historiqueMatchById(idMatch:any):Observable<Paris> {
        const headers = new HttpHeaders();
        headers.append('accept', 'application/json');
        console.log("detail Paris en cours...")
        return this.http.get<Paris>(this.uri + '/parisApi/paris?id='+idMatch,{headers:headers});
    }
}