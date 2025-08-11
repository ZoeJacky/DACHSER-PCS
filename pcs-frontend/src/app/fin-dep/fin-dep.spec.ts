import { ComponentFixture, TestBed } from '@angular/core/testing';

import { FinanceDep } from './fin-dep';

describe('FinDep', () => {
  let component: FinanceDep;
  let fixture: ComponentFixture<FinanceDep>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [FinanceDep]
    })
    .compileComponents();

    fixture = TestBed.createComponent(FinanceDep);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
