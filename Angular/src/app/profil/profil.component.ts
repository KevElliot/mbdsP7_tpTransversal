import { Component, OnInit } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';
import { NgxQrcodeElementTypes, NgxQrcodeErrorCorrectionLevels } from '@techiediaries/ngx-qrcode';
import { AuthService } from '../shared/auth.service';
import { Login } from '../model/login.model';

@Component({
  selector: 'app-profil',
  templateUrl: './profil.component.html',
  styleUrls: ['./profil.component.css']
})
export class ProfilComponent implements OnInit {
  userInfo: Login = new Login();
  elementType = NgxQrcodeElementTypes.URL;
  correctionLevel = NgxQrcodeErrorCorrectionLevels.HIGH;
  value:string;
  formUpdateUser = new FormGroup({
    name: new FormControl('', Validators.required),
    email: new FormControl('', Validators.required),
    password: new FormControl('', Validators.required),
  });
  showData=false;

  constructor(
    private authService: AuthService,
    private route: ActivatedRoute,
    private router: Router
  ) { }

  ngOnInit(): void {
    this.getUserById();
  }

  onSubmit() {
    const id: string = this.route.snapshot.params.id;
    if (this.formUpdateUser.valid) {
      this.userInfo = new Login();
      this.userInfo._id = id;
      this.userInfo.email = (this.formUpdateUser.value.email);
      this.userInfo.name = (this.formUpdateUser.value.name);
      this.userInfo.password = (this.formUpdateUser.value.password);
      this.authService.updateUser(this.userInfo)
        .subscribe(
          reponse => {
            console.log(reponse);
            location.reload();
          }
        );
    }
  }

  getUserById() {
    const id: number = this.route.snapshot.params.id;
    this.authService.getUser(id).subscribe((user) => {
      this.userInfo = user;
      this.value = user.email + ";" + user.password;
      this.showData =true;
      // console.log("value: " + this.value)
      // console.log(this.userInfo)
    });
  }
}
