package org.skypro.employee.records.service.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.skypro.employee.records.service.model.Employee;
import org.springframework.stereotype.Service;

@Service
public class DepartmentServiceImpl implements DepartmentService {

    private final EmployeeService employeeService;

    public DepartmentServiceImpl(EmployeeService employeeService) {
        this.employeeService = employeeService;
    }

    @Override
    public Collection<Employee> findAllEmployees(int departmentId) {
        return employeeService.findAll().stream()
                              .filter(employee -> employee.getDepartmentId() == departmentId)
                              .toList();
    }

    @Override
    public int getSalary(int departmentId) {
        return employeeService.findAll().stream()
                              .filter(employee -> employee.getDepartmentId() == departmentId)
                              .mapToInt(Employee::getSalary)
                              .sum();
    }

    @Override
    public int getMaxSalary(int departmentId) {
        return employeeService.findAll().stream()
                              .filter(employee -> employee.getDepartmentId() == departmentId)
                              .map(Employee::getSalary)
                              .max(Integer::compareTo)
                              .orElse(0);
    }

    @Override
    public int getMinSalary(int departmentId) {
        return employeeService.findAll().stream()
                              .filter(employee -> employee.getDepartmentId() == departmentId)
                              .map(Employee::getSalary)
                              .min(Integer::compareTo)
                              .orElse(0);
    }

    @Override
    public Map<Integer, List<Employee>> findAllEmployeesWithDivided() {
        return employeeService.findAll().stream()
                              .collect(Collectors.groupingBy(Employee::getDepartmentId));
    }
}
