package com.example.driveBack.controller;

import com.example.driveBack.dto.RideDTO;
import com.example.driveBack.dto.VehiclePreview;
import com.example.driveBack.model.Position;
import com.example.driveBack.service.VehicleService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/vehicle/")
public class VehicleController {

    @Autowired
    VehicleService rideService;

    @GetMapping("get-nearest/{latitude}/{longitude}/{newLatitude}/{newLongitude}")
    public ResponseEntity getNearest(@PathVariable double latitude, @PathVariable double longitude,
                                     @PathVariable double newLatitude, @PathVariable double newLongitude) {
        return new ResponseEntity<>(rideService.getNearest(
                new Position(latitude, longitude),
                new Position(newLatitude, newLongitude)
        ), HttpStatus.OK);
    }

    @PostMapping("book-vehicle")
    public ResponseEntity bookVehicle(@RequestBody RideDTO ridePreview) {
        return new ResponseEntity<>(rideService.bookVehicle(
                ridePreview
        ), HttpStatus.OK);
    }
}
