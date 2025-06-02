package com.example.EmployeeMangamentSystem.Repository;

import com.example.EmployeeMangamentSystem.Entity.Employee;
import org.springframework.data.jpa.repository.JpaRepository;

public interface EmployeeRepository extends JpaRepository<Employee,Long>{
}
