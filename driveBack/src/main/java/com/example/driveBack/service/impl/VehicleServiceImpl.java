package com.example.driveBack.service.impl;

import com.example.driveBack.dto.RideDTO;
import com.example.driveBack.dto.VehiclePreview;
import com.example.driveBack.dto.VehiclePreviewI;
import com.example.driveBack.exception.NotFoundException;
import com.example.driveBack.model.Position;
import com.example.driveBack.model.Vehicle;
import com.example.driveBack.model.VehicleState;
import com.example.driveBack.repo.VehicleRepository;
import com.example.driveBack.service.VehicleService;
import org.locationtech.jts.geom.Coordinate;
import org.locationtech.jts.geom.GeometryFactory;
import org.locationtech.jts.geom.Point;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class VehicleServiceImpl implements VehicleService {

    @Autowired
    VehicleRepository vehicleRepository;

    @Override
    public List<VehiclePreview> getNearest(Position currentPosition, Position newPosition) {
        List<VehiclePreviewI> nearestVehicles = vehicleRepository.findNearestVehicles(currentPosition.getLatitude(), currentPosition.getLongitude());

        List<VehiclePreview> vp = nearestVehicles.stream()
                .map(VehiclePreview::new)
                .collect(Collectors.toList());

        vp.forEach(v -> {
            v.setTotalDistance(DistanceCalculator.calculateDistance(currentPosition, newPosition));
            v.setTotalPrice();
        });

        return vp;

    }

    @Override
    public boolean bookVehicle(RideDTO rideDTO) {
        boolean notRejected = simulateTaxiRejectChance();
        if(notRejected)
            bookRealVehicle(rideDTO.getRideId());
        return notRejected;
    }

    @Override
    public Vehicle getVehicle(Long id) {
        return vehicleRepository.findById(id).orElseThrow(() -> new NotFoundException("Vehicle does not exist."));
    }

    @Override
    public void updatePosition(Vehicle vehicle, Position position) {
        vehicle.setCurrentPosition(makePoint(position));
        vehicleRepository.save(vehicle);
    }

    @Override
    public void freeUpDriver(Vehicle vehicle) {
        vehicle.setState(VehicleState.FREE);
        vehicleRepository.save(vehicle);
    }

    private Point makePoint(Position position) {
        GeometryFactory geometryFactory = new GeometryFactory();
        Coordinate coordinate = new Coordinate(position.getLongitude(), position.getLatitude());
        return geometryFactory.createPoint(coordinate);
    }

    private void bookRealVehicle(Long id) {
        Vehicle vehicle = getVehicle(id);
        vehicle.setState(VehicleState.BOOKED);
        vehicleRepository.save(vehicle);
    }

    private boolean simulateTaxiRejectChance() {
        return Math.random() >= 0.25;
    }
}
