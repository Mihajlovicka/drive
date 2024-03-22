package com.example.driveBack.service.impl;

import com.example.driveBack.model.Position;
import com.example.driveBack.model.Ride;
import com.example.driveBack.model.RideSimulation;
import com.example.driveBack.repo.PositionRepository;
import com.example.driveBack.repo.RideSimulationRepository;
import com.example.driveBack.service.RideService;
import com.example.driveBack.service.RideSimulationService;
import com.example.driveBack.service.RoutingService;
import com.example.driveBack.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.context.annotation.Lazy;
import org.springframework.scheduling.annotation.Scheduled;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import java.util.List;

@Service
public class RideSimulationServiceImpl implements RideSimulationService {
    @Autowired
    RideSimulationRepository rideSimulationRepository;
    @Autowired
    @Lazy
    RoutingService routingService;
    @Autowired
    @Lazy
    VehicleService vehicleService;
    @Autowired
    @Lazy
    RideService rideService;
    @Autowired
    PositionRepository positionRepository;

    @Override
    public void newSimulation(Ride ride, Position start, Position end) {
        List<Position> positions = routingService.getPositionsFromRoute(
                new Position(ride.getVehicle().getCurrentPosition().getY(),
                        ride.getVehicle().getCurrentPosition().getX()),
                start, end);
        positionRepository.saveAll(positions);
        rideSimulationRepository.save(new RideSimulation(ride, positions));
    }

    @Transactional
    @Scheduled(fixedRate = 5000)
    public void simulation() {
        double kmPer5Sec = 0.0833;
        for (RideSimulation r : rideSimulationRepository.findAll()) {
            List<Position> positions = r.getPositions();
            double distance = 0;
            int i = 0;
            while (i < positions.size() - 1) {
                distance += DistanceCalculator.calculateDistance(positions.get(i), positions.get(i + 1));
                if (distance > kmPer5Sec)
                    break;
                i++;
            }

            if (positions.size() <= 1) {
                vehicleService.updatePosition(r.getRide().getVehicle(), positions.get(i));
                r.getPositions().remove(i);
            } else {
                vehicleService.updatePosition(r.getRide().getVehicle(), positions.get(i + 1));
                r.getPositions().removeAll(r.getPositions().subList(0, i + 2));
            }

            rideSimulationRepository.save(r);
            if (r.getPositions().isEmpty()) {
                rideSimulationRepository.delete(r);
                rideService.endRide(r.getRide());
            }
        }

    }


}
