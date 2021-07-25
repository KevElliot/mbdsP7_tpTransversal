import { Pipe, PipeTransform } from '@angular/core';

@Pipe({ name: 'MatchSearchPipe', pure: false })
export class MatchSearchPipe implements PipeTransform {

  transform(value, args?): Array<any> {
    let searchText = new RegExp(args, 'ig');
    if (value) {
      return value.filter(match => {
          return match.lieumatch.search(searchText) !== -1;
      });
    }
  }

}
