import {Component, EventEmitter, OnInit, ViewEncapsulation} from '@angular/core';
import {EquipeService} from "../../../service/equipe.service";

@Component({
  selector: 'app-liste-equipes',
  templateUrl: './liste-equipes.component.html',
  styleUrls: ['./liste-equipes.component.scss'],
  encapsulation: ViewEncapsulation.None
})
export class ListeEquipesComponent implements OnInit {
  settingEquipe: Object;
  equipes: any;

  event: EventEmitter<any> = new EventEmitter();
  constructor(public service: EquipeService) {
    this.getEquipes();
  }
  loadTableSettings() {
    return {
      selectMode: 'single',  //single|multi
      hideHeader: false,
      hideSubHeader: false,
      actions: {
        columnTitle: 'Actions',
        add: true,
        edit: true,
        delete: true,
        custom: [],
        position: 'right' // left|right
      },
      add: {
        addButtonContent: '<h4 class="mb-1"><i class="fa fa-plus ml-3 text-success"></i></h4>',
        createButtonContent: '<i class="fa fa-check mr-3 text-success"></i>',
        cancelButtonContent: '<i class="fa fa-times text-danger"></i>',
        confirmCreate: true
      },
      edit: {
        editButtonContent: '<i class="fa fa-pencil mr-3 text-primary"></i>',
        saveButtonContent: '<i class="fa fa-check mr-3 text-success"></i>',
        cancelButtonContent: '<i class="fa fa-times text-danger"></i>',
        confirmSave: true
      },
      delete: {
        deleteButtonContent: '<i class="fa fa-trash-o text-danger"></i>',
        confirmDelete: true
      },
      noDataMessage: 'No data found',
      columns: {
        id: {
          title: '',
          addable: false,
          filter: false,
          editable: false,
          width: '60px',
          type: 'html',
          valuePrepareFunction: (value) => { return `<a [routerLink]="['pages/utilisateur/detail/${value}']"><i class="fa fa-caret-right"></i></a>`; }
        },
        nom: {
          title: 'Nom',
          type: 'string',
          filter: true
        },
        note: {
          title: 'Points',
          type: 'number',
          addable: false,
          editable: false,
          defaultValue: 0,
          filter: true
        }
      },
      pager: {
        display: true,
        perPage: 10
      }
    };
  }
  onCreateEquipe(event) {
    this.service.ajoutEquipe(event.newData)
        .subscribe((data) => {
          event.confirm.resolve(event.newData);
        });
  }

  onEditEquipe(event) {
    this.service.modifEquipe(event.newData)
        .subscribe(data => {
          event.confirm.resolve(event.newData);
        });
  }

  onDeleteEquipe(event) {
    if (window.confirm('Etes vous sure de vouloir supprimer?')) {
      this.service.deleteEquipe(event.data.id)
          .subscribe((data) => {

          });
      event.confirm.resolve(event.source.data);
    } else {
      event.confirm.reject();
    }
  }

  public getEquipes() {
    this.service.getEquipes().subscribe((data) => {
      this.equipes = data;
      this.settingEquipe = this.loadTableSettings();
      console.log(JSON.stringify(this.equipes));
    })
  }
  ngOnInit(): void {
  }

}
