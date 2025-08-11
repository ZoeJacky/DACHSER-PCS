import { ComponentFixture, TestBed } from '@angular/core/testing';

import { CostAdmin } from './cost-admin';

describe('CostAdmin', () => {
  let component: CostAdmin;
  let fixture: ComponentFixture<CostAdmin>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [CostAdmin]
    })
    .compileComponents();

    fixture = TestBed.createComponent(CostAdmin);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
