import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { Login } from '../../model/login.model';
import { AuthService } from '../auth.service';
import { DemandeJetons } from '../../model/demandeJetons.model';

@Component({
  selector: 'app-navigation',
  templateUrl: './navigation.component.html',
  styleUrls: ['./navigation.component.css']
})
export class NavigationComponent implements OnInit {
  login = new Login();
  idUser = sessionStorage.getItem("userActive");
  name = sessionStorage.getItem("nom");
  jetons = sessionStorage.getItem("jetons");
  erreur:string;
  demandeCliquer=false;
  formDemandeJeton = new FormGroup({
    jetonDemande: new FormControl('', [Validators.required, Validators.min(10)]),
  });
  isConnect:boolean;

  constructor(private router: Router, private authService: AuthService) { }
  

  ngOnInit(): void {
    if(this.idUser){
      this.authService.setConnected(true);
    }
    this.isConnect = this.authService.getIsConnected();
  }
  deconnecter() {
    sessionStorage.clear();
    this.authService.setConnected(false);
    this.router.navigate(['/']);
    window.location.reload();
  }
  onSubmit() {
    //demande de jeton ny ato
    if(this.formDemandeJeton.valid){
      this.demandeCliquer=true;
      var demandeJeton = new DemandeJetons();
      demandeJeton.iduser=this.idUser;
      demandeJeton.jetonsdemande=this.formDemandeJeton.value.jetonDemande;
      demandeJeton.statut="false";
      this.authService.demandeJeton(demandeJeton).subscribe(
        reponse =>{
          window.location.reload();
        }
      );
      console.log(demandeJeton);
    }else{
      this.erreur="Veuillez faire une demande de plus de 10 jetons";
    }
  }
}
