import { Component, OnInit } from '@angular/core';
import { FormGroup, FormControl,Validators,FormBuilder } from '@angular/forms';
import { Router } from '@angular/router';

@Component({
  selector: 'app-login',
  templateUrl: './login.component.html',
  styleUrls: ['./login.component.css']
})
export class LoginComponent implements OnInit {

  constructor(private fb: FormBuilder, private router:Router) { }

  afficherRegister = false;
  isLinear = true;
  nom;email;jeton;
  firstFormGroup: FormGroup;
  secondFormGroup: FormGroup;

  ngOnInit(): void {
    this.firstFormGroup = this.fb.group({
      nom: ['', Validators.required],
      email: ['', Validators.required],
      password: ['', Validators.required],
    });
    this.secondFormGroup = this.fb.group({
      jeton: ['', [Validators.required,Validators.min(1),Validators.max(20)]]
    });
  }
  setInfo(){
    this.nom = this.firstFormGroup.value.nom;
    this.email = this.firstFormGroup.value.email;
    this.jeton = this.secondFormGroup.value.jeton;
  }
  connexion(){
    sessionStorage.setItem('monObjet', 'maValeur');
    this.router.navigate(['/home']);
  }
}
