package com.np.bank.management.entities;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Getter;
import lombok.NoArgsConstructor;
import lombok.Setter;

@Entity
@Getter
@Setter
@NoArgsConstructor
@AllArgsConstructor
public class EmployeeRecords {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private int employeeId;
    private String name;
    private String role;
    @ManyToOne(fetch = FetchType.EAGER)
    @JoinColumn(name = "managerId")
    private Manager manager;
    private String contactNumber;
}
