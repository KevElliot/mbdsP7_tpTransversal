import { HttpClient, HttpHeaders } from '@angular/common/http';
import { Injectable } from '@angular/core';
import { forkJoin, Observable, of } from 'rxjs';
import { Login } from '../login/login.model';

@Injectable({
    providedIn: 'root'
})
export class AuthService {

    constructor(private http: HttpClient) { }
    
    uri = "http://localhost:8010/pariBack/auth";

    authentification(login: any): Observable<any> {
        console.log("Authentification...")
        return this.http.post(this.uri + '/login', login);
    }
    register(login: any): Observable<any> {
        console.log("Enregistrement en cours...")
        return this.http.post(this.uri + '/register', login);
    }
}