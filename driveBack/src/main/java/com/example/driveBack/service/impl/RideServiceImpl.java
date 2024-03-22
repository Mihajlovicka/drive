package com.example.driveBack.service.impl;

import com.example.driveBack.dto.RideDTO;
import com.example.driveBack.dto.RidePreview;
import com.example.driveBack.exception.NotFoundException;
import com.example.driveBack.model.*;
import com.example.driveBack.repo.PositionRepository;
import com.example.driveBack.repo.RatingRepository;
import com.example.driveBack.repo.RideRepository;
import com.example.driveBack.service.*;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.stereotype.Service;

import java.util.List;
import java.util.stream.Collectors;

@Service
public class RideServiceImpl implements RideService {
    @Autowired
    VehicleService vehicleService;
    @Autowired
    UserService userService;
    @Autowired
    RideRepository rideRepository;
    @Autowired
    RatingRepository ratingRepository;
    @Autowired
    RideSimulationService rideSimulationService;
    @Autowired
    PositionRepository positionRepository;

    @Override
    public void newRide(RideDTO rideDTO) {
        Ride ride = makeRide(rideDTO);
        rideSimulationService.newSimulation(ride, rideDTO.getStartPosition(), ride.getEndPosition());
    }

    private Ride makeRide(RideDTO rideDTO) {
        Ride ride = new Ride(rideDTO);
        ride.setVehicle(vehicleService.getVehicle(rideDTO.getRideId()));
        ride.setUser(userService.getLoggedIn());
        positionRepository.saveAll(List.of(ride.getStartPosition(), ride.getEndPosition()));
        rideRepository.save(ride);
        return ride;
    }

    @Override
    public List<RidePreview> getUserRides() {
        User u = userService.getLoggedIn();
        return rideRepository.findRideByUser(u).stream()
                .map(RidePreview::new)
                .collect(Collectors.toList());
    }

    @Override
    public void rateRide(Long id, Rating rating) {
        Ride r = rideRepository.findById(id).orElseThrow(()-> new NotFoundException("Ride not found."));
        r.setRating(rating);
        ratingRepository.save(rating);
        rideRepository.save(r);
    }

    @Override
    public void endRide(Ride ride) {
        ride.setState(RideState.FINISHED);
        rideRepository.save(ride);
        vehicleService.freeUpDriver(ride.getVehicle());
    }
}
