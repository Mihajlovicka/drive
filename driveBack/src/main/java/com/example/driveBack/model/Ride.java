package com.example.driveBack.model;

import com.example.driveBack.dto.RideDTO;
import com.fasterxml.jackson.annotation.JsonIgnoreProperties;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;
import org.springframework.format.annotation.DateTimeFormat;

import java.util.Date;
import javax.persistence.*;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@JsonIgnoreProperties({ "currentPosition" })
public class Ride {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Long id;
    @OneToOne
    private Position startPosition;
    @OneToOne
    private Position endPosition;
    @OneToOne
    private Rating rating;
    private double totalPrice;
    private double totalDistance;
    @ManyToOne@JoinColumn(name = "vehicle_id")
    private Vehicle vehicle;
    @ManyToOne
    private User user;
    @DateTimeFormat(iso = DateTimeFormat.ISO.DATE_TIME)
    private Date createdAt;
    private RideState state;


    public Ride(RideDTO rideDTO){
        totalDistance = rideDTO.getTotalDistance();
        totalPrice = rideDTO.getTotalPrice();
        startPosition = rideDTO.getStartPosition();
        endPosition = rideDTO.getEndPosition();
        createdAt = new Date();
        state = RideState.CURRENT;
    }
}
