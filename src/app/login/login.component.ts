import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';
import { AuthService } from '../shared/auth.service';
import { Login } from '../model/login.model';

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
      let user = new Login();
      user.name = this.nom;
      user.email = this.email;
      user.password = this.firstFormGroup.value.password;
      user.role = "joueur";
      user.jetons = this.jeton;
      user.image = "null";
      this.authService.register(user)
      .subscribe(
        reponse => {
          console.log(reponse);
          alert("veuillez vous connecté!");
          location.reload();
        });
  }
  onSubmit() {
    if (this.signin.valid) {
      let nouvelAuthentification = new Login();
      let user = new Login();
      // nouvelAuthentification.email = this.signin.value.email;
      // nouvelAuthentification.password = this.signin.value.password;
      nouvelAuthentification.email = "tom@gmail.com";
      nouvelAuthentification.password = "tom";
      this.authService.authentification(nouvelAuthentification)
        .subscribe(
          reponse => {
            sessionStorage.setItem('userActive',reponse._id);
            sessionStorage.setItem('nom',reponse.name);
            sessionStorage.setItem('jetons',reponse.jetons);
            // this.router.navigate(["/home"],{queryParams:{data:reponse._id}});
            this.router.navigate(["/home"]);
          }, error => {
            this.champs = "Username or password is incorrect";
          });
    }
  }
}
