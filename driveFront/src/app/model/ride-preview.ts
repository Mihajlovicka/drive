import {Position} from "./vehicle";
import {VehiclePreview} from "./vehicle-preview";

export interface RidePreview{
  id:number,
  startPosition: Position,
  endPosition: Position,
  rating?: Rating,
  totalPrice: number,
  totalDistance: number,
  vehicle: VehiclePreview,
  createdAt:Date,
  state:RideState,
}

export interface Rating{
  rating:number,
  comment?: string
}

export enum RideState{
  CURRENT,
  FINISHED
}
