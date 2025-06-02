package com.example.EmployeeMangamentSystem.Service;

import com.example.EmployeeMangamentSystem.Entity.Employee;
import com.example.EmployeeMangamentSystem.Repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;
@Service
public class Employeeimple  implements imple{
    @Autowired
    public EmployeeRepository employeeRepository;

    @Override
    public Employee addemployee(Employee employee) {
        return employeeRepository.save(employee);
    }

    @Override
    public List<Employee> viewAll() {
        List<Employee>employees = employeeRepository.findAll();
        return employees;
    }

    @Override
    public Employee update(Employee employee) {
       Employee employee1 = new Employee();
            employee1.setEmployeeId(employee.getEmployeeId());
            employee1.setEmployeeFirstName(employee.getEmployeeFirstName());
            employee1.setEmployeeLastName(employee.getEmployeeLastName());
            employee1.setEmployeeEmail(employee.getEmployeeEmail());
            return employeeRepository.save(employee1);
    }

    @Override
    public void Delete(Long id) {
        List<Employee> employeeList = employeeRepository.findAll();
        for(Employee emp: employeeList){
            if(id ==emp.getEmployeeId()){
                employeeRepository.delete(emp);
                break;
            }
        }

    }
}
