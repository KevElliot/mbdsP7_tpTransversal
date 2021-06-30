import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { forkJoin, Observable, of } from 'rxjs';
import { Match } from '../model/match.model';

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
}