package com.test.droneapp.repositories;

import com.test.droneapp.entity.Medication;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface
MedicationRepository extends JpaRepository<Medication, Long> {
}
