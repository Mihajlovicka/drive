import { ComponentFixture, TestBed } from '@angular/core/testing';

import { RideLocationComponent } from './ride-location.component';

describe('RideLocationComponent', () => {
  let component: RideLocationComponent;
  let fixture: ComponentFixture<RideLocationComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ RideLocationComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(RideLocationComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
