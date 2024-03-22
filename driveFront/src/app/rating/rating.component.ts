import {Component, EventEmitter, Inject} from '@angular/core';
import {Position} from "../model/vehicle";
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {Rating} from "../model/ride-preview";

@Component({
  selector: 'app-review',
  templateUrl: './rating.component.html',
  styleUrls: ['./rating.component.css']
})
export class RatingComponent {
  rating : Rating = {
    rating: 0,
    comment: ''
  }
  constructor(public dialogRef: MatDialogRef<RatingComponent>) {}

}
