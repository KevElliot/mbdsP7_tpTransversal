import { Menu } from './menu.model';

export const verticalMenuItems = [
    new Menu (1, 'Match', null, null, 'laptop', null, true, 0),
    new Menu (2, 'Liste des équipes', '/pages/match/liste-equipes', null, null, null, false, 1),
    new Menu (3, 'Liste des matchs', '/pages/match/liste-matchs', null, null, null, false, 1),
    new Menu (4, 'Liste des prochains matchs', '/pages/match/prochains', null, null, null, false, 1),
    new Menu (5, 'Demande de Jeton', '/pages/joueur/demande-jeton', null, 'th-large', null, false, 0),
    new Menu (6, 'Map', '/pages/maps', null, 'globe', null, false, 0),
]