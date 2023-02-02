import {ComponentFixture, TestBed} from '@angular/core/testing';

import {DetailsInstanceComponent} from './details-instance.component';

describe('DetailsInstanceComponent', () => {
  let component: DetailsInstanceComponent;
  let fixture: ComponentFixture<DetailsInstanceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [DetailsInstanceComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(DetailsInstanceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
