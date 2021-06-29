import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../shared/auth.service';
import { Login } from './login.model';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private fb: FormBuilder, private router: Router, private authService: AuthService) { }
  login: Login[];
  signin = new FormGroup({
    email: new FormControl(''),
    password: new FormControl(''),
  });
  champs = "";
  afficherRegister = false;
  isLinear = true;
  nom; email; jeton;
  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;

  ngOnInit(): void {
    this.firstFormGroup = this.fb.group({
      nom: ['', Validators.required],
      email: ['', Validators.required],
      password: ['', Validators.required],
    });
    this.secondFormGroup = this.fb.group({
      jeton: ['', [Validators.required, Validators.min(1)]]
    });
  }
  setInfo() {
    this.nom = this.firstFormGroup.value.nom;
    this.email = this.firstFormGroup.value.email;
    this.jeton = this.secondFormGroup.value.jeton;
  }
  connexion() {
    sessionStorage.setItem('monObjet', 'maValeur');
    this.router.navigate(['/home']);
  }
  inscription() {
      let nouvelAuthentification = new Login();
      nouvelAuthentification.email = this.signin.value.email;
      nouvelAuthentification.password = this.signin.value.password;
      this.authService.authentification(nouvelAuthentification)
  }
  onSubmit() {
    if (this.signin.valid) {
      let nouvelAuthentification = new Login();
      nouvelAuthentification.email = this.signin.value.email;
      nouvelAuthentification.password = this.signin.value.password;
      this.authService.authentification(nouvelAuthentification)
        .subscribe(
          reponse => {
            console.log(reponse);
            this.router.navigate(["/home"]);
          }, error => {
            this.champs = "Username or password is incorrect";
          });
    }
  }
}
