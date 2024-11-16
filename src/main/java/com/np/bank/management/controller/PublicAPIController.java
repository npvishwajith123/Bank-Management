package com.np.bank.management.controller;

import com.np.bank.management.entities.Users;
import com.np.bank.management.repositories.UserRepository;
import com.np.bank.management.utilities.JwtUtility;
import lombok.RequiredArgsConstructor;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequiredArgsConstructor
public class PublicAPIController {

    private final UserRepository userRepository;
    private final JwtUtility jwtUtility;

    @PostMapping("/public/auth/login")
    public ResponseEntity<?> login(@RequestBody Users user) {
        try {
            Users userDetails = userRepository.findByUserName(user.getUserName())
                    .orElseThrow(() -> new Exception("No user found with userName: "
                            + user.getUserName()));

            if (userDetails.getPassword().equals(user.getPassword())) {
                return ResponseEntity.ok(jwtUtility.generateJwtToken(userDetails));
            } else {
                throw new Exception("Passwords doesn't match");
            }
        } catch (Exception ex) {
            return ResponseEntity.status(HttpStatus.UNAUTHORIZED.value())
                    .body("Cannot authorize user, exception occurred: " + ex.getMessage());
        }
    }
}
