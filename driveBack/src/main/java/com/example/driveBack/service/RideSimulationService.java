package com.example.driveBack.service;

import com.example.driveBack.model.Position;
import com.example.driveBack.model.Ride;

import java.util.List;

public interface RideSimulationService {
    void newSimulation(Ride ride, Position start, Position end);
}
