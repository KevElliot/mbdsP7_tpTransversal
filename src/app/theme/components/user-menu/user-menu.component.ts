import { Component, OnInit, ViewEncapsulation } from '@angular/core';
import {User} from "../../../modele/user";

@Component({
  selector: 'app-user-menu',
  templateUrl: './user-menu.component.html',
  styleUrls: ['./user-menu.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class UserMenuComponent implements OnInit {
  user : User = JSON.parse(localStorage.getItem('currentAdmin'));
  utilisateur: string = this.user.name;
  constructor() { }

  ngOnInit() {
  }

}
