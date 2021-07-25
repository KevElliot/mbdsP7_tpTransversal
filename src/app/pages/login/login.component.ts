import { Component, ViewEncapsulation } from '@angular/core';
import { Router } from '@angular/router';
import { FormGroup, FormControl, AbstractControl, FormBuilder, Validators} from '@angular/forms';
import { CustomValidators } from 'ng2-validation';
import {first} from 'rxjs/operators';
import { ToastrService } from 'ngx-toastr';
import {AuthentificationService} from "../../service/authentification.service";

@Component({
    selector: 'app-login',
    templateUrl: './login.component.html',
    styleUrls: ['./login.component.scss'],
    encapsulation: ViewEncapsulation.None
})
export class LoginComponent {
    public router: Router;
    public form: FormGroup;
    public email: AbstractControl;
    public password: AbstractControl;

    constructor(private toastr: ToastrService, router: Router, fb: FormBuilder, private service: AuthentificationService) {
        this.service.logout();
        this.router = router;
        this.form = fb.group({
            'email': ['', Validators.compose([Validators.required, CustomValidators.email])],
            'password': ['', Validators.compose([Validators.required])]
        });

        this.email = this.form.controls['email'];
        this.password = this.form.controls['password'];
        // this.email.setValue('toavinarazafy3@gmail.com');
        // this.password.setValue('toavina');
    }

    public onSubmit() {
        if (this.form.valid) {
            console.log('control');
            this.service.login(this.email.value, this.password.value)
                .pipe(first())
                .subscribe(
                    data => {
                        console.log('control' + data);
                        if(localStorage.getItem('tokenAdmin')){
                            this.router.navigate(['/']);
                        }else{
                            this.toastr.error('Email ou mot de passe incorrecte', 'Erreur');
                        }
                    },
                    error => {
                        this.toastr.error('Email ou mot de passe incorrecte', 'Erreur');
                    });
        }
    }
    private isLoggedIn(): boolean {
        const result = !!(sessionStorage.get('token'));
        return result;
    }

    // tslint:disable-next-line:use-life-cycle-interface
    ngAfterViewInit() {
        document.getElementById('preloader').classList.add('hide');
    }
}
