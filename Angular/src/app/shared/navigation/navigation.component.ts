import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { Login } from '../../model/login.model';
import { AuthService } from '../auth.service';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {

  constructor(private router: Router, private authService: AuthService) { }
  login = new Login();
  idUser 
  name 
  jetons 

  ngOnInit(): void {
    this.idUser = sessionStorage.getItem("userActive");
    this.name = sessionStorage.getItem("nom");
    this.jetons = sessionStorage.getItem("jetons");
  }
  deconnecter() {
    sessionStorage.clear();
    this.router.navigate(['/']);
  }
}
