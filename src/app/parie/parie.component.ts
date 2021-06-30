import { Component, OnInit } from '@angular/core';
import { Router } from '@angular/router';
import { ApiService } from '../shared/apiGrails.service';
import { Match } from '../model/match.model';
@Component({
  selector: 'app-parie',
  templateUrl: './parie.component.html',
  styleUrls: ['./parie.component.css']
})
export class ParieComponent implements OnInit {
  cote= 0;
  choix = '';
  id=1;
  match: Match[];

  constructor(private router:Router,
              private apiService: ApiService,
            ) { }

  ngOnInit(): void {
    this.getAllMatch();
  }
  getAllMatch(){
    this.apiService.listeMatch().subscribe((match) => {
      this.match = match;
      console.log(this.match)
    });
  }
  setter(cote,choix) {
    this.cote = cote;
    this.choix= choix;
  }
  detailPari(){
    this.router.navigate(['/parie', this.id]);
  }
}
