import { ComponentFixture, TestBed } from '@angular/core/testing';

import { PaymentAdmin } from './payment-admin';

describe('PaymentAdmin', () => {
  let component: PaymentAdmin;
  let fixture: ComponentFixture<PaymentAdmin>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      imports: [PaymentAdmin]
    })
    .compileComponents();

    fixture = TestBed.createComponent(PaymentAdmin);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
