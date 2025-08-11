import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateCost } from './update-cost';

describe('UpdateCost', () => {
  let component: UpdateCost;
  let fixture: ComponentFixture<UpdateCost>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UpdateCost]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdateCost);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
