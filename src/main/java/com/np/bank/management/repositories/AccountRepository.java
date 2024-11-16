package com.np.bank.management.repositories;

import com.np.bank.management.entities.Account;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface AccountRepository extends JpaRepository<Account, Integer> {
    @Query("Select a from Account a where a.customer.customerId = :customerId")
    Optional<Account> findByCustomerCustomerId(int customerId);

    @Query("Select a from Account a where a.accountId = :accountId")
    Optional<Account> findByAccountId(int accountId);
}
