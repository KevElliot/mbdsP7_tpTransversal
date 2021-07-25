import { Injectable } from '@angular/core';
import {HttpClient} from "@angular/common/http";
import {BehaviorSubject, Observable} from "rxjs";
import {User} from "../modele/user";
import {AppService} from "./app.service";
import {map} from "rxjs/operators";

@Injectable({
  providedIn: 'root'
})
export class AuthentificationService {
    private currentUserSubject: BehaviorSubject<User>;
    public currentUser: Observable<User>;
    constructor(private http: HttpClient, private appService: AppService) {
        this.currentUserSubject = new BehaviorSubject<User>(JSON.parse(localStorage.getItem('currentUtilisateur')));
        this.currentUser = this.currentUserSubject.asObservable();
    }
    public get currentUserValue(): User {
        return this.currentUserSubject.value;
    }
    login(email: string, password: string) {
        return this.http.post<any>('pariBack/auth/login', { email, password }).pipe(map(user => {
            console.log(JSON.stringify(user));
            if (user && user.token) {
                if(user.role=='admin'){
                    localStorage.setItem('currentAdmin', JSON.stringify(user));
                    localStorage.setItem('tokenAdmin', user.token);
                    console.log(JSON.stringify(user));
                    this.currentUserSubject.next(user);
                    this.appService.setUserLoggedIn(true);
                    return user;
                }
            }

        }));
    }

    logout() {
        localStorage.removeItem('currentAdmin');
        localStorage.removeItem('tokenAdmin');
        this.appService.setUserLoggedIn(false);
        this.currentUserSubject.next(null);
    }
}
