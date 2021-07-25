import { PartageModule } from './partage.module';

describe('PartageModule', () => {
  let partageModule: PartageModule;

  beforeEach(() => {
    partageModule = new PartageModule();
  });

  it('should create an instance', () => {
    expect(partageModule).toBeTruthy();
  });
});
