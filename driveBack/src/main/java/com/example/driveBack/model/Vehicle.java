package com.example.driveBack.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.hibernate.annotations.Type;
import org.locationtech.jts.geom.Point;

import javax.persistence.*;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class Vehicle {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    private String brand;
    private Driver driver;
    private double startPrice;
    private double pricePerKm;
    private VehicleState state = VehicleState.FREE;
    @Column(columnDefinition = "Point")
    private Point currentPosition;

}
