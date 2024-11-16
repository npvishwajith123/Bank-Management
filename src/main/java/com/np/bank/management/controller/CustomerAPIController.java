package com.np.bank.management.controller;

import com.fasterxml.jackson.annotation.JsonView;
import com.np.bank.management.entities.*;
import com.np.bank.management.repositories.*;
import lombok.RequiredArgsConstructor;
import org.springframework.http.CacheControl;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RestController;

import java.net.URI;
import java.util.List;
import java.util.concurrent.TimeUnit;

@RestController
@RequiredArgsConstructor
public class CustomerAPIController {

    private final AccountRepository accountRepository;
    private final CustomerRepository customerRepository;
    private final BankAdminRepository adminRepository;
    private final EmployeeRecordsRepository employeeRepository;

    @GetMapping("/accounts/{customerId}/balance")
    public ResponseEntity<?> getCustomerAccBalance(@PathVariable("customerId")int customerId) {
        try {
            Account customerAccountDetails = accountRepository.findByCustomerCustomerId(customerId)
                    .orElseThrow(() -> new Exception("No accounts found for customer"));
            return ResponseEntity.ok(customerAccountDetails);
        } catch (Exception e) {
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body("Exception occurred: "+e.getMessage());
        }
    }

    @GetMapping("/accounts/{accountId}/transactions")
    public ResponseEntity<?> getTransactionHistory(@PathVariable("accountId")int accountId) {
        try {
            Account accountDetails = accountRepository.findByAccountId(accountId)
                    .orElseThrow(() -> new Exception("No transactions found for customer"));
            return ResponseEntity.ok(accountDetails);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body("Exception occurred: "+e.getMessage());
        }
    }

    @GetMapping("/managers/{managerId}/customers")
    public ResponseEntity<?> getCustomersForManager(@PathVariable("managerId") int managerId) {
        try {
            List<Customer> customer = customerRepository.findByManagerManagerId(managerId)
                    .orElseThrow(() -> new Exception("No customers found"));
            return ResponseEntity.ok(customer);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body("Exception occurred: "+e.getMessage());
        }
    }

    @GetMapping("/admin/contact")
    @JsonView(BankAdmin.View.BasicView.class)
    public ResponseEntity<?> getAdminContact() {
        List<BankAdmin> admins = adminRepository.findAll();
        return ResponseEntity.status(HttpStatus.OK.value()).location(URI.create("/superAdmins/contact")).body(admins);
    }

    @GetMapping("/superAdmins/contact")
    @JsonView(BankAdmin.View.DetailedView.class)
    public ResponseEntity<?> getCompleteAdminContact() {
        List<BankAdmin> admins = adminRepository.findAll();
        return ResponseEntity.ok(admins);
    }

    @GetMapping("/accounts/{accountId}")
    public ResponseEntity<?> getAccountDetails(@PathVariable("accountId")int accountId){
        try {
            Account account = accountRepository.findByAccountId(accountId)
                    .orElseThrow(() -> new Exception("No transactions found for customer"));
            return ResponseEntity.status(HttpStatus.OK.value())
                    .cacheControl(CacheControl.maxAge(300, TimeUnit.SECONDS))
                    .body(account);

        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body("Exception occurred: "+e.getMessage());
        }
    }

    @GetMapping("/managers/{managerId}/employees")
    public ResponseEntity<?> getEmployeesForManager(@PathVariable("managerId") int managerId) {
        try {
            EmployeeRecords employees = employeeRepository.findByManagerManagerId(managerId)
                    .orElseThrow(() -> new Exception("No Employee Records found for given managerId"));
            return ResponseEntity.ok(employees);
        } catch (Exception e){
            return ResponseEntity.status(HttpStatus.BAD_REQUEST.value()).body("Exception occurred: "+e.getMessage());

        }
    }
}
