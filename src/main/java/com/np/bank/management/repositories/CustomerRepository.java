package com.np.bank.management.repositories;

import com.np.bank.management.entities.Customer;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.List;
import java.util.Optional;

public interface CustomerRepository extends JpaRepository<Customer, Integer> {
    @Query("Select c from Customer c where c.manager.managerId = :managerId")
    Optional<List<Customer>> findByManagerManagerId(int managerId);
}
