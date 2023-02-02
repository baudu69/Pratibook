import {ComponentFixture, TestBed} from '@angular/core/testing';

import {ValidReservationComponent} from './valid-reservation.component';

describe('ValidReservationComponent', () => {
  let component: ValidReservationComponent;
  let fixture: ComponentFixture<ValidReservationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ValidReservationComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(ValidReservationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
