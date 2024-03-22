export interface VehiclePreviewI {
  id: number;
  brand: string;
  driver: {
    firstName: string;
    lastName: string;
  };
  distance: number;
  totalPrice: number;
  totalDistance: number;
  startPrice: number;
  pricePerKm: number;
}

export class VehiclePreview implements VehiclePreviewI {
  id: number = 0;
  brand: string = '';
  driver = { firstName: '', lastName: '' };
  distance: number = 0;
  totalPrice: number = 0;
  totalDistance: number = 0;
  startPrice: number = 0;
  pricePerKm: number = 0;
}
