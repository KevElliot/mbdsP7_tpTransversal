import {Injectable} from '@angular/core';
import {HttpClient, HttpHeaders} from "@angular/common/http";
import {Observable} from "rxjs";
import {Equipe} from "../modele/equipe";
import {Match} from "../modele/match";
import {InfoResultat} from "../modele/info-resultat";

@Injectable({
    providedIn: 'root'
})
export class MatchService {

    constructor(private http: HttpClient) {
    }

    getPager(totalItems: number, currentPage: number = 1, pageSize: number = 50) {
        // calculate total pages
        const totalPages = Math.ceil(totalItems / pageSize);
        // ensure current page isn't out of range
        if (currentPage <= 1) {
            currentPage = 1;
        } else if (currentPage > totalPages) {
            currentPage = totalPages;
        }

        let startPage: number, endPage: number;
        if (totalPages <= 10) {
            // less than 10 total pages so show all
            startPage = 1;
            endPage = totalPages;
        } else {
            // more than 10 total pages so calculate start and end pages
            if (currentPage <= 6) {
                startPage = 1;
                endPage = 10;
            } else if (currentPage + 4 >= totalPages) {
                startPage = totalPages - 9;
                endPage = totalPages;
            } else {
                startPage = currentPage - 5;
                endPage = currentPage + 4;
            }
        }

        // calculate start and end item indexes
        const startIndex = (currentPage - 1) * pageSize;
        const endIndex = 49;

        // create an array of pages to ng-repeat in the pager control
        const pages = Array.from(Array((endPage + 1) - startPage).keys()).map(i => startPage + i);
        // return object with all pager properties required by the view
        return {
            totalItems: totalItems,
            currentPage: currentPage,
            pageSize: pageSize,
            totalPages: totalPages,
            startPage: startPage,
            endPage: endPage,
            startIndex: startIndex,
            endIndex: endIndex,
            pages: pages
        };
    }

    getMatchs(): Observable<Match[]> {
        return this.http.get<Match[]>('matchApi/listeTotale');
    }

    getMatchsASuivre(): Observable<Match[]> {
        return this.http.get<Match[]>('matchApi/matchs');
    }

    ajoutMatch(match: Match) {
        return this.http.post('matchApi/match', match);
    }

    modifMatch(match: Match) {
        return this.http.put('matchApi/match', match);
    }

    deleteMatch(id: number) {
        const headers = new HttpHeaders();
        headers.append('Content-Type', 'application/json; charset=utf-8');
        return this.http.delete('matchApi/match/' + id, {headers: headers});
    }

    modifResultat(match: Match): Observable<InfoResultat> {
        return this.http.put<InfoResultat>('matchApi/resultat', match);
    }

    countMatch(idequipe: number, fini: number): Observable<any> {
        return this.http.get('matchApi/count?idequipe=' + idequipe + '&fini=' + fini);
    }

    filtre(idequipe: number, fini: number, first: number, max: number): Observable<Match[]> {
        return this.http.get<Match[]>('matchApi/filtre?idequipe=' + idequipe + '&fini=' + fini + '&first=' + first + '&max=' + max);
    }
}
