package com.test.droneapp.repositories;

import com.test.droneapp.entity.Drone;
import com.test.droneapp.enums.DroneState;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.stereotype.Repository;

import java.util.List;

@Repository
public interface DroneRepository extends JpaRepository<Drone, Long> {
    @Query("SELECT d FROM Drone d WHERE d.state in (:ds1, :ds2)")
    List<Drone> findDronesWhereStateIs(DroneState ds1, DroneState ds2);
}
