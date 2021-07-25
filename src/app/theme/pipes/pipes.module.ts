import { NgModule } from '@angular/core';
import { CommonModule } from '@angular/common';

import { MatchSearchPipe } from './match-search.pipe';

@NgModule({
    imports: [ 
        CommonModule 
    ],
    declarations: [
        MatchSearchPipe
    ],
    exports: [
        MatchSearchPipe
    ]
})
export class PipesModule { }
