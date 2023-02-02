import {TestBed} from '@angular/core/testing';

import {ListeReservationService} from './liste-reservation.service';

describe('ListeReservationService', () => {
  let service: ListeReservationService;

  beforeEach(() => {
    TestBed.configureTestingModule({});
    service = TestBed.inject(ListeReservationService);
  });

  it('should be created', () => {
    expect(service).toBeTruthy();
  });
});
