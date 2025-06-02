package com.example.EmployeeMangamentSystem.Service;

import com.example.EmployeeMangamentSystem.Entity.Employee;

import java.util.List;

public interface imple {

    Employee addemployee(Employee employee);
    List<Employee> viewAll();
    Employee update(Employee employee);
    public void Delete(Long id);

}
