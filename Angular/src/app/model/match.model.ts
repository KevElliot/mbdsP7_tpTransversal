import { Equipe } from './equipe.model';

export class Match {
    id:number;
    cotev1:string;
    cotex:string;
    resultat:string;
    lieumatch:string;
    datematch:string;
    cotev2:string;
    equipe2:Equipe;
    equipe1:Equipe;
    fini:string;
  }