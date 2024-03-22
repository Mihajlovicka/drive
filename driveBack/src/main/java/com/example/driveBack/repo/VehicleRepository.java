package com.example.driveBack.repo;

import com.example.driveBack.dto.VehiclePreviewI;
import com.example.driveBack.model.Vehicle;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;
import java.util.List;

@Repository
public interface VehicleRepository extends JpaRepository<Vehicle, Long> {
    @Query(value = "SELECT v.id as id, v.brand as brand, v.first_name as firstName, v.last_name as lastName," +
            " v.start_price as startPrice, v.price_per_km as pricePerKm," +
            " ST_Distance_Sphere(v.current_position, POINT(:latitude, :longitude)) / 1000 AS distance " +
            "FROM Vehicle v " +
            "WHERE v.state = 0 " +
            "ORDER BY distance " +
            "LIMIT 10", nativeQuery = true)
    List<VehiclePreviewI> findNearestVehicles(@Param("latitude") Double latitude, @Param("longitude") Double longitude);
}


//uzeti i driver
// izracunati cenu
//vratiti i prikazati
