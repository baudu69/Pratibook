import {ComponentFixture, TestBed} from '@angular/core/testing';

import {FormAddInstanceComponent} from './form-add-instance.component';

describe('FormAddInstanceComponent', () => {
  let component: FormAddInstanceComponent;
  let fixture: ComponentFixture<FormAddInstanceComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [FormAddInstanceComponent]
    })
      .compileComponents();

    fixture = TestBed.createComponent(FormAddInstanceComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
