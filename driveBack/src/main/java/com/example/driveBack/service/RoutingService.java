package com.example.driveBack.service;

import com.example.driveBack.model.Position;

import java.util.List;

public interface RoutingService {
    List<Position> getPositionsFromRoute(Position departure, Position stop, Position destination);
}
