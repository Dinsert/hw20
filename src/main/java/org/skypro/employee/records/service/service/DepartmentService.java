package org.skypro.employee.records.service.service;

import static java.util.Objects.*;

import java.util.Collection;
import java.util.Comparator;
import java.util.Optional;
import org.skypro.employee.records.service.model.Employee;
import org.springframework.stereotype.Service;

@Service
public class DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentService(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    public Optional<Employee> findMaxSalary(int departmentId) {
        return employeeService.findAll().stream()
                              .filter(e -> e.getDepartmentId() == departmentId)
                              .max(Comparator.comparingInt(Employee::getSalary));
    }

    public Optional<Employee> findMinSalary(int departmentId) {
        return employeeService.findAll().stream()
                              .filter(e -> e.getDepartmentId() == departmentId)
                              .min(Comparator.comparingInt(Employee::getSalary));
    }

    public Collection<Employee> findAllEmployeesWithDivided(Integer departmentId) {
        if (isNull(departmentId)) {
            return employeeService.findAll().stream()
                                  .sorted(Comparator.comparingInt(Employee::getDepartmentId))
                                  .toList();
        }
        return findAllEmployees(departmentId);
    }

    private Collection<Employee> findAllEmployees(int departmentId) {
        return employeeService.findAll().stream()
                              .filter(employee -> employee.getDepartmentId() == departmentId)
                              .toList();
    }

}
