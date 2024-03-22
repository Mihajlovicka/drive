import {Component, OnInit} from '@angular/core';
import {VehicleService} from "../service/vehicle.service";
import {VehiclePreview} from "../model/vehicle-preview";
import {ActivatedRoute, Router} from "@angular/router";
import {Position} from "../model/vehicle";
import {RideService} from "../service/ride.service";

@Component({
  selector: 'app-nearest-vehicles',
  templateUrl: './nearest-vehicles.component.html',
  styleUrls: ['./nearest-vehicles.component.css']
})
export class NearestVehiclesComponent{
  nearest:VehiclePreview[] = []
  currentP: Position |undefined;
  newP: Position |undefined;

  constructor(private service: VehicleService,
              private rideService: RideService,
              private route: ActivatedRoute,
              private router: Router) {
    this.route.queryParams.subscribe(params => {
      this.currentP = {
        latitude:params['lat1'],
        longitude:params['lng1']
      }
      this.newP = {
        latitude:params['lat2'],
        longitude:params['lng2']
      }
      this.retrieveNearestVehicles(this.currentP, this.newP);
    });
  }

  private retrieveNearestVehicles(currentP:Position, newP:Position) {
    this.service.getNearest(currentP, newP).subscribe({
      next: (r) => {
        this.nearest = r;
        this.sortNearestByTotalPrice();
      }
    });
  }

  private sortNearestByTotalPrice() {
    this.nearest.sort((a, b) => a.totalPrice - b.totalPrice);
  }


  bookTaxi(vehicle: VehiclePreview) {
    const ride = {
      rideId: vehicle.id,
      totalPrice: vehicle.totalPrice,
      totalDistance: vehicle.totalDistance,
      startPosition: this.currentP,
      endPosition: this.newP
    }
    this.service.bookTaxi(ride).subscribe({
      next: (resp) => {
        if(resp) {
          this.rideService.newRide(ride).subscribe({
            next: () => {alert("Taxi is on it's way!");
              this.router.navigate(['/rides']);}
          })
        }
        else
          alert("Driver has rejected this request!")
      },
      error: (err) => alert(err.error.message)
    });
  }
}
