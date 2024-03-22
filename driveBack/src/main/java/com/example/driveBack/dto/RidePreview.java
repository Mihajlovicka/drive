package com.example.driveBack.dto;

import com.example.driveBack.model.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RidePreview {
    private Long id;
    private VehicleDTO vehicle;
    private double totalPrice;
    private Rating rating;
    private double totalDistance;
    private RideState state;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date createdAt;
    private Position startPosition;
    private Position endPosition;


    public RidePreview(Ride ride){
        id = ride.getId();
        totalPrice = ride.getTotalPrice();
        totalDistance = ride.getTotalDistance();
        state = ride.getState();
        createdAt = ride.getCreatedAt();
        vehicle = new VehicleDTO(ride.getVehicle());
        rating = ride.getRating();
        startPosition = ride.getStartPosition();
        endPosition = ride.getEndPosition();
    }
}
