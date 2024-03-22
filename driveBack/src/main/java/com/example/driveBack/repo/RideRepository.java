package com.example.driveBack.repo;

import com.example.driveBack.model.Ride;
import java.util.List;

import com.example.driveBack.model.User;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

@Repository
public interface RideRepository extends JpaRepository<Ride, Long> {
    List<Ride> findRideByUser(User user);
}
