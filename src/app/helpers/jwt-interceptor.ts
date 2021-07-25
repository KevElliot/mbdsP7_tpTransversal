import {HttpClient, HttpEvent, HttpHandler, HttpInterceptor, HttpRequest} from "@angular/common/http";
import {AuthentificationService} from "../service/authentification.service";
import {Injectable} from "@angular/core";
import {Observable} from "rxjs";

@Injectable()
export class JwtInterceptor implements HttpInterceptor{
    constructor(private http: HttpClient, private authenticationService: AuthentificationService) {
    }
    intercept(request: HttpRequest<any>, next: HttpHandler): Observable<HttpEvent<any>> {
        const req = request.url;
        const currentUser = this.authenticationService.currentUserValue;
        //const url = 'http://localhost:8080/';
        //const urlNode = 'http://localhost:8010/';
        const url = 'https://tpt-server-grails.herokuapp.com/';
        const urlNode = 'https://parilocalnode.herokuapp.com/';
        console.log(url);
        if (!request.url.startsWith('http')) {
            if(request.url.startsWith('pariBack')){
                request = request.clone({
                    url: urlNode + request.url
                });
            } else{
                request = request.clone({
                    url: url + request.url
                });
            }


        }
        if (request.method=='GET') {
            console.log('Method GET')
            request = request.clone({
                setHeaders: {
                    'Accept': 'application/json'
                }
            });
        }

        /**if (currentUser && currentUser.token) {
            console.log(`Bearer ${currentUser.token}`);
            request = request.clone({
                setHeaders: {
                    'Authorization': `Bearer ${currentUser.token}`
                }
            });
        }**/
        return next.handle(request);
    }
}