package com.example.driveBack.service.impl;

import com.example.driveBack.model.Position;
import org.springframework.stereotype.Service;


public class DistanceCalculator {
    private static final double EARTH_RADIUS = 6371; // Earth's radius in kilometers

    public static double calculateDistance(Position p1, Position p2) {
        double lat1Rad = Math.toRadians(p1.getLatitude());
        double lon1Rad = Math.toRadians(p1.getLongitude());
        double lat2Rad = Math.toRadians(p2.getLatitude());
        double lon2Rad = Math.toRadians(p2.getLongitude());

        // Haversine formula
        double dLat = lat2Rad - lat1Rad;
        double dLon = lon2Rad - lon1Rad;
        double a = Math.pow(Math.sin(dLat / 2), 2) + Math.cos(lat1Rad) * Math.cos(lat2Rad) * Math.pow(Math.sin(dLon / 2), 2);
        double c = 2 * Math.atan2(Math.sqrt(a), Math.sqrt(1 - a));
        double distance = EARTH_RADIUS * c;

        return distance; //km
    }
}
