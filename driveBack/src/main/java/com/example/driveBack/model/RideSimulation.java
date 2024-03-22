package com.example.driveBack.model;

import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

import javax.persistence.*;
import java.util.List;

@Entity
@Data
@NoArgsConstructor
@AllArgsConstructor
public class RideSimulation {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Ride ride;
    @OneToMany(
            cascade= CascadeType.ALL,
            orphanRemoval = true)
    @OrderColumn
    private List<Position> positions;

    public RideSimulation(Ride ride, List<Position> positions) {
        this.ride = ride;
        this.positions = positions;
    }
}
