import {Equipe} from "./equipe";

export class Match {
    id:number
    equipe1: Equipe
    equipe2: Equipe
    cotev1: number
    cotev2: number
    cotex: number
    resultat: number
    datematch : Date
    lieumatch: string
    fini : number
}
