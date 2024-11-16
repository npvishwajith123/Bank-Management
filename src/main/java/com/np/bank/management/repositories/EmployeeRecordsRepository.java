package com.np.bank.management.repositories;

import com.np.bank.management.entities.EmployeeRecords;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface EmployeeRecordsRepository extends JpaRepository<EmployeeRecords, Integer> {
    @Query("Select e from EmployeeRecords e where e.manager.managerId = :managerId")
    Optional<EmployeeRecords> findByManagerManagerId(int managerId);
}
