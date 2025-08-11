import { ComponentFixture, TestBed } from '@angular/core/testing';

import { UpdateIncome } from './update-income';

describe('UpdateIncome', () => {
  let component: UpdateIncome;
  let fixture: ComponentFixture<UpdateIncome>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [UpdateIncome]
    })
    .compileComponents();

    fixture = TestBed.createComponent(UpdateIncome);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
