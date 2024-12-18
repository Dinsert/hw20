package org.skypro.employee.records.service.service;

import java.util.ArrayList;
import java.util.Collection;
import java.util.HashMap;
import java.util.Map;
import org.apache.commons.lang3.StringUtils;
import org.skypro.employee.records.service.exceptions.EmployeeAlreadyAddedException;
import org.skypro.employee.records.service.exceptions.EmployeeNotFoundException;
import org.skypro.employee.records.service.exceptions.EmployeeStorageIsFullException;
import org.skypro.employee.records.service.exceptions.InvalidLineException;
import org.skypro.employee.records.service.model.Employee;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final int MAX_POSSIBLE_NUMBER_BY_EMPLOYEES = 1;
    private final Map<String, Employee> employeeMap;

    public EmployeeServiceImpl() {
        this.employeeMap = new HashMap<>();
    }

    @Override
    public Employee add(String firstName, String lastName, int salary, int departmentId) {
        if (!(StringUtils.isAlpha(firstName) && StringUtils.isAlpha(lastName))) {
            throw new InvalidLineException();
        }
        Employee employee = new Employee(firstName, lastName, salary, departmentId);
        if (employeeMap.containsKey(employee.getFullName())) {
            throw new EmployeeAlreadyAddedException();
        }
        if (employeeMap.size() == MAX_POSSIBLE_NUMBER_BY_EMPLOYEES) {
            throw new EmployeeStorageIsFullException();
        }
        employeeMap.put(employee.getFullName(), employee);
        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName, 0, 0);
        if (employeeMap.containsKey(employee.getFullName())) {
            return employeeMap.remove(employee.getFullName());
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Employee find(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName, 0, 0);
        if (employeeMap.containsKey(employee.getFullName())) {
            return employeeMap.get(employee.getFullName());
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Collection<Employee> findAll() {
        return new ArrayList<>(employeeMap.values());
    }
}
