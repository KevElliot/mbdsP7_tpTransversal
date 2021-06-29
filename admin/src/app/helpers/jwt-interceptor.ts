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
        const url = 'https://tpt-server-grails.herokuapp.com/';
        console.log(url);
        if (!request.url.startsWith('http')) {
            request = request.clone({
                url: url + request.url
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