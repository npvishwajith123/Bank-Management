package com.np.bank.management.repositories;

import com.np.bank.management.entities.CustomerRecords;
import org.springframework.data.jpa.repository.JpaRepository;

public interface CustomerRecordsRepository extends JpaRepository<CustomerRecords, Integer> {
}
