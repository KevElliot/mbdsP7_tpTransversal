import { Routes, RouterModule } from '@angular/router';
import { ModuleWithProviders } from '@angular/core';
import { PagesComponent } from './pages.component';

export const routes: Routes = [
    {
        path: '', 
        component: PagesComponent,
        children:[
            { path:'', redirectTo:'match', pathMatch:'full' },
            { path: 'maps', loadChildren: 'app/pages/maps/maps.module#MapsModule', data: { breadcrumb: 'Maps' } },
            { path: 'match', loadChildren: 'app/pages/match/match.module#MatchModule', data: { breadcrumb: 'Match' }  },
            { path: 'joueur', loadChildren: 'app/pages/joueur/joueur.module#JoueurModule', data: { breadcrumb: 'Joueur' }  }
        ]
    }
];

export const routing: ModuleWithProviders = RouterModule.forChild(routes);