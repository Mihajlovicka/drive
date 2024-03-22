import {HttpClient, HttpHeaders, HttpResponse} from "@angular/common/http";
import {Injectable} from "@angular/core";
import {map} from "rxjs";
import {environment} from '../environments/environment';
import {Position} from "../model/vehicle";
import {VehiclePreview, VehiclePreviewI} from "../model/vehicle-preview";

@Injectable()
export class VehicleService {

  host: string = environment.apiUrl + 'vehicle/'

  constructor(private http: HttpClient) {
  }

  getNearest(currentP:Position, newP:Position) {
    const url = `${this.host}get-nearest/${currentP.latitude}/${currentP.longitude}/${newP.latitude}/${newP.longitude}`;
    return this.http.get<VehiclePreviewI[]>(url)
  }

  bookTaxi(ride: any) {
    return this.http.post(`${this.host}book-vehicle`,ride)
  }
}
