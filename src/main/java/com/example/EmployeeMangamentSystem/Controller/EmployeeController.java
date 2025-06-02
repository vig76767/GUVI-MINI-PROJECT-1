package com.example.EmployeeMangamentSystem.Controller;


import com.example.EmployeeMangamentSystem.Entity.Employee;
import com.example.EmployeeMangamentSystem.Service.Employeeimple;
import jakarta.validation.Valid;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Controller;
import org.springframework.ui.Model;
import org.springframework.validation.BindingResult;
import org.springframework.web.bind.annotation.*;
import org.springframework.web.servlet.mvc.support.RedirectAttributes;

import java.util.List;

@Controller
public class EmployeeController {
    @Autowired
    public Employeeimple employeeimple;
    @GetMapping("/index")
    public String FirstPage(Model model){
        List<Employee> employeeList=employeeimple.viewAll();
        model.addAttribute("employeelist",employeeList);
        return "index";
    }

    @GetMapping("/Employee")
    public String addEmployee(Model model){
        model.addAttribute("employee",new Employee());
            return "AddEmployee";
    }
    @PostMapping("/Employee/save")
    public String addemployee(@Valid @ModelAttribute Employee employee, Model model , BindingResult result){
        List<Employee>  employeeList = employeeimple.viewAll();
        if(employeeList!=null) {
            for (Employee employee1 : employeeList) {
                if (employee1.getEmployeeEmail().equals((employee.getEmployeeEmail()))){
                        result.rejectValue("EmployeeEmail",null,"This email id is already in use");
                        break;
                }
            }
        }
        String name= employee.getEmployeeFirstName();
        for(int i=0;i<name.length();i++){
            if(!(name.charAt(i)>='a' && name.charAt(i)<='z' || name.charAt(i)>='A' && name.charAt(i)<='Z')){
                result.rejectValue("EmployeeFirstName",null,"Only letters are allowed");
            }
        }
        String lname= employee.getEmployeeLastName();
        for(int i=0;i<lname.length();i++){
            if(!(lname.charAt(i)>='a' && lname.charAt(i)<='z' || lname.charAt(i)>='A' && lname.charAt(i)<='Z')){
                result.rejectValue("EmployeeLastName",null,"Only letters are allowed");
            }
        }
        if(result.hasErrors()){
            model.addAttribute("employee",employee);
            return "AddEmployee";
        }
        employeeimple.addemployee(employee);
        return "redirect:/Employee?success";
    }

    @GetMapping("/Employees")
    public String viewAll(Model modal){
    List<Employee> employeeList = employeeimple.viewAll();
        if(employeeList.isEmpty()){
            return "errorpage";
        }
    modal.addAttribute("employeelist",employeeList);
    return "ViewAll";
    }

    @GetMapping("/Employee/Delete")
    public String EmployeeDelete(){
        List<Employee> employeeList =employeeimple.viewAll();
        if(employeeList.isEmpty()){
            return "errorpage";
        }
        return "Delete";
    }

    @PostMapping("/Employee/delete")
    public String delete(@RequestParam("id") Long id){
        List<Employee> employeeList =employeeimple.viewAll();
        for(Employee emp : employeeList) {
            if (id == emp.getEmployeeId()) {
                employeeimple.Delete(id);
                return "redirect:/Employee/Delete?success";
            }
        }
        return "redirect:/Employee/Delete?failed";
    }
    @GetMapping("/Employee/update")
    public String update(){
        List<Employee> employeeList =employeeimple.viewAll();
        if(employeeList.isEmpty()){
            return "errorpage";
        }
        return "Updatevalue";
    }

    @PostMapping("/Employee/Update")
    public String update(@RequestParam("id") Long id,Model model){
        List<Employee> employeeList =employeeimple.viewAll();
        for(Employee emp : employeeList) {
            if (id == emp.getEmployeeId()) {
                model.addAttribute("employee",emp);
                return "CheckId";
            }
        }
        return "redirect:/Employee/update?failed";
    }

    @PostMapping("/Employee/Confirm")
    public String confirm(@Valid @ModelAttribute Employee employee ,BindingResult result,Model model){
        List<Employee>  employeeList = employeeimple.viewAll();
        if(employeeList!=null) {
            for (Employee employee1 : employeeList) {
                if (employee1.getEmployeeEmail().equals((employee.getEmployeeEmail()))){
                    result.rejectValue("EmployeeEmail",null,"This email id is already in use");
                    break;
                }
            }
        }
        String name= employee.getEmployeeFirstName();
        for(int i=0;i<name.length();i++){
            if(!(name.charAt(i)>='a' && name.charAt(i)<='z' || name.charAt(i)>='A' && name.charAt(i)<='Z')){
                result.rejectValue("EmployeeFirstName",null,"Only letters are allowed");
            }
        }
        String lname= employee.getEmployeeLastName();
        for(int i=0;i<lname.length();i++){
            if(!(lname.charAt(i)>='a' && lname.charAt(i)<='z' || lname.charAt(i)>='A' && lname.charAt(i)<='Z')){
                result.rejectValue("EmployeeLastName",null,"Only letters are allowed");
            }
        }
        if(result.hasErrors()){
            model.addAttribute("employee",employee);
            return "CheckId";
        }
            employeeimple.update(employee);
            return "redirect:/Employee/update?success";
    }

}
