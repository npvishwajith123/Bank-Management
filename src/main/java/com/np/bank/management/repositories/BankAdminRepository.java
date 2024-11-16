package com.np.bank.management.repositories;

import com.np.bank.management.entities.BankAdmin;
import org.springframework.data.jpa.repository.JpaRepository;

public interface BankAdminRepository extends JpaRepository<BankAdmin, Integer> {
}
