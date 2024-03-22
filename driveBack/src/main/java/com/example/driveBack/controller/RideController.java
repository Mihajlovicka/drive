package com.example.driveBack.controller;

import com.example.driveBack.dto.RideDTO;
import com.example.driveBack.model.Position;
import com.example.driveBack.model.Rating;
import com.example.driveBack.model.Ride;
import com.example.driveBack.model.User;
import com.example.driveBack.service.RideService;
import com.example.driveBack.service.VehicleService;
import java.util.List;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

@RestController
@RequestMapping("/ride/")
public class RideController {

    @Autowired
    RideService rideService;

    @GetMapping("all")
    public ResponseEntity getAll() {
        return new ResponseEntity<>(rideService.getUserRides(), HttpStatus.OK);
    }

    @PatchMapping("rate/{id}")
    public ResponseEntity rate(@PathVariable Long id,@RequestBody Rating rating) {
        rideService.rateRide(id, rating);
        return new ResponseEntity<>(HttpStatus.OK);
    }

    @PostMapping("new")
    public ResponseEntity newRide(@RequestBody RideDTO rideDTO) {
        rideService.newRide(rideDTO);
        return new ResponseEntity<>(HttpStatus.OK);
    }
}
