package com.example.driveBack.repo;

import com.example.driveBack.model.RideSimulation;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface RideSimulationRepository extends JpaRepository<RideSimulation, Long> {

}
