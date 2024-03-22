package com.example.driveBack.dto;

import com.example.driveBack.model.Driver;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehiclePreview {
    private Long id;
    private String brand;
    private Driver driver;
    private double distance;
    private double totalPrice;
    private double totalDistance;
    private double startPrice;
    private double pricePerKm;

    public VehiclePreview(VehiclePreviewI i){
        id = i.getId();
        brand = i.getBrand();
        driver = new Driver(i.getFirstName(), i.getLastName());
        distance = i.getDistance();
        startPrice = i.getStartPrice();
        pricePerKm = i.getPricePerKm();
    }

    public void setTotalPrice(){
        totalPrice = startPrice + totalDistance*pricePerKm;
    }


}
