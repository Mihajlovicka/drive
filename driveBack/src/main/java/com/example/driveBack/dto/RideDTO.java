package com.example.driveBack.dto;

import com.example.driveBack.model.Position;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import java.util.*;

@Data
@AllArgsConstructor
@NoArgsConstructor
public class RideDTO {
    private Position startPosition;
    private Position endPosition;
    private String email;
    private Long rideId;
    private double totalPrice;
    private double totalDistance;
}
