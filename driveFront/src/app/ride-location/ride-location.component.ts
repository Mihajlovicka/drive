import {Component, EventEmitter, Inject, OnInit, Output} from '@angular/core';
import {MAT_DIALOG_DATA, MatDialogRef} from "@angular/material/dialog";
import {Position} from "../model/vehicle";

@Component({
  selector: 'app-ride-location',
  templateUrl: './ride-location.component.html',
  styleUrls: ['./ride-location.component.css']
})
export class RideLocationComponent {
  current = new Position();
  endLocation: Position = new Position();
  constructor(public dialogRef: MatDialogRef<RideLocationComponent>,
              @Inject(MAT_DIALOG_DATA) public data: any) {
    this.current = data.current
  }

  closeDialog: EventEmitter<any> = new EventEmitter();

  onSubmit() {
    this.closeDialog.emit(this.endLocation);
    this.dialogRef.close();
  }
}


