import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';

@Component({
  selector: 'app-parie',
  templateUrl: './parie.component.html',
  styleUrls: ['./parie.component.css']
})
export class ParieComponent implements OnInit {
  cote= 0;
  choix = '';
  id=1;
  
  constructor(private router:Router) { }

  ngOnInit(): void {
  }
  setter(cote,choix) {
    this.cote = cote;
    this.choix= choix;
  }
  detailPari(){
    //var monObjet = sessionStorage.getItem('monObjet');
    //alert(monObjet);
    this.router.navigate(['/parie', this.id]);
  }
}
