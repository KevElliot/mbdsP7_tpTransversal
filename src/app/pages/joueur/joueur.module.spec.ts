import { JoueurModule } from './joueur.module';

describe('JoueurModule', () => {
  let joueurModule: JoueurModule;

  beforeEach(() => {
    joueurModule = new JoueurModule();
  });

  it('should create an instance', () => {
    expect(joueurModule).toBeTruthy();
  });
});
