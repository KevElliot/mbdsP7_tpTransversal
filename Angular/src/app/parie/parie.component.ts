import { Component, OnInit, OnDestroy } from '@angular/core';
import { Subject } from 'rxjs';
import { DataTablesModule } from 'angular-datatables';
import { Router } from '@angular/router';
import { ApiService } from '../shared/apiGrails.service';
import { Match } from '../model/match.model';

@Component({
  selector: 'app-parie',
  templateUrl: './parie.component.html',
  styleUrls: ['./parie.component.css']
})
export class ParieComponent implements OnInit, OnDestroy {
  cote = 0;
  choix = '';
  id = 1;
  match: Match[];
  dtOptions: DataTables.Settings = {};
  dtTrigger: Subject<any> = new Subject();
  showData=false;

  constructor(private router: Router,
    private apiService: ApiService,
  ) { }

  ngOnInit(): void {
    this.dtOptions = {
      pagingType: 'full_numbers',
      pageLength: 5,
      processing: true
    };
    this.getAllMatch();
  }
  getAllMatch() {
    this.apiService.listeMatch().subscribe((match) => {
      this.match = match;
      this.showData =true;
      this.dtTrigger.next();
      console.log(this.match)
    }, error => {
      window.location.reload();
    });
  }
  setter(cote, choix) {
    this.cote = cote;
    this.choix = choix;
  }
  detailPari() {
    this.router.navigate(['/parie', this.id]);
  }

  ngOnDestroy(): void {
    this.dtTrigger.unsubscribe();
  }
}
