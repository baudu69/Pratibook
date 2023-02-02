import {TestBed} from '@angular/core/testing';

import {DetailsInstanceService} from './details-instance.service';

describe('DetailsInstanceService', () => {
  let service: DetailsInstanceService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(DetailsInstanceService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
