import {Component, OnInit} from '@angular/core';
import {ActivatedRoute, Router} from "@angular/router";
import {Rating, RidePreview} from "../model/ride-preview";
import {RideService} from "../service/ride.service";
import {RideLocationComponent} from "../ride-location/ride-location.component";
import {MatDialog} from "@angular/material/dialog";
import {RatingComponent} from "../rating/rating.component";
import {coerceNumberProperty} from "@angular/cdk/coercion";

@Component({
  selector: 'app-rides',
  templateUrl: './rides.component.html',
  styleUrls: ['./rides.component.css']
})
export class RidesComponent implements  OnInit{
  rides:RidePreview[] = []

  constructor(private service: RideService,
              private route: ActivatedRoute,
              private router: Router,
              public dialog: MatDialog) {

  }

  rate(id: number) {
    const dialogRef = this.dialog.open(RatingComponent);

    dialogRef.afterClosed().subscribe(
      {next: (data:Rating) => {
        this.service.rate(id, data).subscribe(
          {
            next: () => {
              this.rides.forEach(el => {
                if(el.id == id){
                  el.rating = data
                }
              })
            }
          }
        )
        }}
    );
  }

  ngOnInit(): void {
    this.service.getRides().subscribe({
      next: (r) => {
        this.rides = r
        console.log(this.rides)
        this.sort()
      }
    });
  }

  private sort() {
    this.rides.sort((a, b) => b.id - a.id);
  }
}
