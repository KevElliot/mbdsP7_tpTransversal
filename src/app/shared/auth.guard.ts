import { Injectable } from '@angular/core';
import { ActivatedRouteSnapshot, CanActivate, RouterStateSnapshot, UrlTree,Router } from '@angular/router';
import { Observable } from 'rxjs';
import { AuthService } from './auth.service';

@Injectable({
  providedIn: 'root'
})
export class AuthGuard implements CanActivate {
  idUser = sessionStorage.getItem("userActive");
  constructor(private authService: AuthService, private router: Router) {}
  canActivate(
    route: ActivatedRouteSnapshot,
    state: RouterStateSnapshot): Observable<boolean | UrlTree> | Promise<boolean | UrlTree> | boolean | UrlTree {
      if(this.idUser){
        this.authService.setConnected(true);
      }
      return this.authService.isConnected().then((connecte) => {
        if (connecte) {
          return true;
        } else {
          console.log("Guard: connectez-vous!");
          
          this.router.navigate(['/login']);
          return false;
        }
      });
  }
  
}
