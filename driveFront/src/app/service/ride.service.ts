import {HttpClient, HttpHeaders, HttpResponse} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {map} from "rxjs";
import {environment} from '../environments/environment';
import {Position} from "../model/vehicle";
import {VehiclePreview, VehiclePreviewI} from "../model/vehicle-preview";
import {Rating, RidePreview} from "../model/ride-preview";

@Injectable()
export class RideService {

  host: string = environment.apiUrl + 'ride/'

  constructor(private http: HttpClient) {
  }

  getRides() {
    return this.http.get<RidePreview[]>(`${this.host}all`)
  }

  rate(id: number, data: Rating) {
    return this.http.patch(`${this.host}rate/${id}`, data)
  }

  newRide(ride:  any) {
    return this.http.post(`${this.host}new`, ride)
  }
}
