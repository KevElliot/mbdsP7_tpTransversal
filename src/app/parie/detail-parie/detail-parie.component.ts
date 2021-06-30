import { Component, OnInit, ViewChild, ElementRef, Renderer2 } from '@angular/core';
import { ActivatedRoute, Router } from '@angular/router';
import { MatCard } from '@angular/material/card';
import { FormGroup, FormControl, Validators, FormBuilder } from '@angular/forms';

@Component({
  selector: 'app-detail-parie',
  templateUrl: './detail-parie.component.html',
  styleUrls: ['./detail-parie.component.css']
})
export class DetailParieComponent implements OnInit {
  constructor(private route: ActivatedRoute,
    private renderer: Renderer2,
    private fb: FormBuilder
  ) { }

  //myFormGroup:FormGroup;
  myFormGroup: FormGroup = this.fb.group({});
  idParie = 0;
  data = [] //fitambarany données rehetra
  lastData = [] //données farany natsofoka
  toogled = false;

  ngOnInit(): void {
    const id: number = +this.route.snapshot.params.id;
    this.idParie = id;
  }
  visibilityPari(id, cote, type) {
    this.lastData = [{ "id": id, "cote": cote, "type": type, "jeton": '1', "gain": cote }];
    var e = this.lastData[0].id;
    if (this.data.find(item => item.id === e)) {
      this.data.forEach((element, index) => {
        if (element.id == e) delete this.data[index]; //mamafa anle card
      });
      this.data = this.data.filter(function (newData) {
        return newData != null;
      });
      //mamafa anle FormControl dynamique

    } else {
      this.toogled = true;
      this.data.unshift(this.lastData[0]);//manampy card + creation formGroup
      this.data.forEach(params => {
        var gain = params.jeton * params.cote;
        this.myFormGroup.addControl('gain' + params.id, this.fb.control(gain, [Validators.required]));
        this.myFormGroup.addControl('jeton' + params.id, this.fb.control(1, [Validators.required]));
      })
    }
  }
  toogle() {
    if (this.toogled) { this.toogled = false }
    else { this.toogled = true }
  }
  placerParie() {
    //foreach data dia mi-insert makany anaty historique
    console.log(this.data);
  }
  onSubmit(id) {
    //id eto le id anle button fa tsy index anle tableau data tandremo
    var indexData = this.data.map(e => e.id).indexOf(id);
    var jeton = this.myFormGroup.get('jeton' + id) as FormControl;
    var gain = (this.data[indexData].cote * jeton.value)
    this.myFormGroup.controls['gain' + id].setValue(gain);
    this.data[indexData].gain = gain;
    var total = 0;
  }
}

