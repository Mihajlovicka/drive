package com.example.driveBack.dto;

import com.example.driveBack.model.Driver;
import com.example.driveBack.model.Vehicle;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Data
@NoArgsConstructor
@AllArgsConstructor
public class VehicleDTO {
    private String brand;
    private Driver driver;

    public VehicleDTO(Vehicle vehicle) {
        brand = vehicle.getBrand();
        driver = vehicle.getDriver();
    }
}
