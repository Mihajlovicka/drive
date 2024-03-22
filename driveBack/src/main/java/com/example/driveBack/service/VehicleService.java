package com.example.driveBack.service;

import com.example.driveBack.dto.RideDTO;
import com.example.driveBack.dto.VehiclePreview;
import com.example.driveBack.dto.VehiclePreviewI;
import com.example.driveBack.model.Position;
import com.example.driveBack.model.Vehicle;

import java.util.List;

public interface VehicleService {
    List<VehiclePreview> getNearest(Position currentPosition, Position newPosition);

    boolean bookVehicle(RideDTO ridePreview);

    Vehicle getVehicle(Long rideId);

    void updatePosition(Vehicle vehicle, Position position);

    void freeUpDriver(Vehicle vehicle);
}
