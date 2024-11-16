package com.np.bank.management.repositories;

import com.np.bank.management.entities.Users;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;

import java.util.Optional;

public interface UserRepository extends JpaRepository<Users, Integer> {
    @Query("Select u from Users u where u.userName = :username")
    Optional<Users> findByUserName(String username);
}
