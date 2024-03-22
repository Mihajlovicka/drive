import { Component } from '@angular/core';
import {NavigationExtras, Router} from "@angular/router";
import {MatDialog} from "@angular/material/dialog";
import {RideLocationComponent} from "../ride-location/ride-location.component";
import {VehicleService} from "../service/vehicle.service";
import {Position} from "../model/vehicle";

@Component({
  selector: 'app-ride-request',
  templateUrl: './ride-request.component.html',
  styleUrls: ['./ride-request.component.css']
})
export class RideRequestComponent {

  currentLocation = {
    latitude: 0,
    longitude: 0
  }

  constructor(private router: Router,
              public dialog: MatDialog) {
  }


  async getTaxi() {
    try {
      const location: any = await this.getCurrentLocation();
      const dialogRef = this.dialog.open(RideLocationComponent, {
        data: {
          current: {
            latitude: location.latitude,
            longitude: location.longitude
          }
        }

      });

      const endLocation : Position= await new Promise((resolve) => {
        dialogRef.componentInstance.closeDialog.subscribe(
          {next: (data:any) => {resolve(data);}}
        );
      });
      const queryParams: NavigationExtras = {
        queryParams: {
          lat1: location.latitude,
          lng1: location.longitude,
          lat2: endLocation.latitude,
          lng2:endLocation.longitude
        }
      };
      this.router.navigate(['/nearest'], queryParams);

    } catch (error) {
      console.error('Error getting location:', error);
    }
  }


  getCurrentLocation() {
    return new Promise((resolve, reject) => {
      if ('geolocation' in navigator) {
        navigator.geolocation.getCurrentPosition((position) => {
          const latitude = position.coords.latitude;
          const longitude = position.coords.longitude;
          resolve({latitude, longitude});
        }, (error) => {
          reject(error);
        });
      } else {
        reject('Geolocation is not available in this browser.');
      }
    });
  }
}
