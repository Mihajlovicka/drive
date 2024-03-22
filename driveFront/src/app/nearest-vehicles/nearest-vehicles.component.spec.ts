import { ComponentFixture, TestBed } from '@angular/core/testing';

import { NearestVehiclesComponent } from './nearest-vehicles.component';

describe('NearestVehiclesComponent', () => {
  let component: NearestVehiclesComponent;
  let fixture: ComponentFixture<NearestVehiclesComponent>;

  beforeEach(async () => {
    await TestBed.configureTestingModule({
      declarations: [ NearestVehiclesComponent ]
    })
    .compileComponents();

    fixture = TestBed.createComponent(NearestVehiclesComponent);
    component = fixture.componentInstance;
    fixture.detectChanges();
  });

  it('should create', () => {
    expect(component).toBeTruthy();
  });
});
