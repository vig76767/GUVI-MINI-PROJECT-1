package com.example.EmployeeMangamentSystem.Entity;

import jakarta.persistence.*;
import lombok.AllArgsConstructor;
import lombok.Data;
import lombok.NoArgsConstructor;

@Entity
@Data
@AllArgsConstructor
@NoArgsConstructor
@Table(name="EmployeeDatabases")
public class Employee {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    @Column( name = "employee_id")
    private Long EmployeeId;
    @Column(nullable = false ,name="employee_fname" )
    private String EmployeeFirstName;
    @Column(nullable = false,name="employee_lname")
    private String EmployeeLastName;
    @Column(nullable = false, name = "employee_email")
    private String EmployeeEmail;
}
