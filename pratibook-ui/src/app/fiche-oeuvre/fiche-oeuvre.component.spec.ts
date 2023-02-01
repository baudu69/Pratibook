import {ComponentFixture, TestBed} from '@angular/core/testing';

import {FicheOeuvreComponent} from './fiche-oeuvre.component';

describe('FicheOeuvreComponent', () => {
  let component: FicheOeuvreComponent;
  let fixture: ComponentFixture<FicheOeuvreComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [FicheOeuvreComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(FicheOeuvreComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
