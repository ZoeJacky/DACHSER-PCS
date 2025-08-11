import { ComponentFixture, TestBed } from '@angular/core/testing';

import { AddCost } from './add-cost';

describe('AddCost', () => {
  let component: AddCost;
  let fixture: ComponentFixture<AddCost>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [AddCost]
    })
    .compileComponents();

    fixture = TestBed.createComponent(AddCost);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
