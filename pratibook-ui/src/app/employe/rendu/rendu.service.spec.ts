import {TestBed} from '@angular/core/testing';

import {RenduService} from './rendu.service';

describe('RenduService', () => {
  let service: RenduService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(RenduService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
