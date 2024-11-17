package org.skypro.employee.records.service.controller;

import java.util.Collection;
import java.util.Optional;
import org.skypro.employee.records.service.model.Employee;
import org.skypro.employee.records.service.service.EmployeeServiceDepartment;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/departments")
@RestController
public class EmployeeControllerDepartment {

    private final EmployeeServiceDepartment employeeServiceDepartment;

    public EmployeeControllerDepartment(EmployeeServiceDepartment employeeServiceDepartment) {
        this.employeeServiceDepartment = employeeServiceDepartment;
    }

    @GetMapping("max-salary")
    public Optional<Employee> findEmployeeByDepartmentWithMaxSalary(@RequestParam int departmentId) {
        return employeeServiceDepartment.findMaxSalary(departmentId);
    }

    @GetMapping("min-salary")
    public Optional<Employee> findEmployeeByDepartmentWithMinSalary(@RequestParam int departmentId) {
        return employeeServiceDepartment.findMinSalary(departmentId);
    }

    @GetMapping("all-department")
    public Collection<Employee> findAllEmployeesByDepartment(@RequestParam int departmentId) {
        return employeeServiceDepartment.findAllEmployees(departmentId);
    }

    @GetMapping("all")
    public Collection<Employee> findAllEmployeesWithDividedByDepartment() {
        return employeeServiceDepartment.findAllEmployeesWithDivided();
    }
}
