package org.skypro.employee.records.service.service;

import java.util.ArrayList;
import java.util.Collections;
import java.util.List;
import org.skypro.employee.records.service.exceptions.EmployeeAlreadyAddedException;
import org.skypro.employee.records.service.exceptions.EmployeeNotFoundException;
import org.skypro.employee.records.service.exceptions.EmployeeStorageIsFullException;
import org.skypro.employee.records.service.model.Employee;
import org.springframework.stereotype.Service;

@Service
public class EmployeeServiceImpl implements EmployeeService {

    private static final int MAX_POSSIBLE_NUMBER_BY_EMPLOYEES = 10;
    private final List<Employee> employeeList;

    public EmployeeServiceImpl() {
        this.employeeList = new ArrayList<>();
    }

    @Override
    public Employee add(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employeeList.contains(employee)) {
            throw new EmployeeAlreadyAddedException();
        }
        if (employeeList.size() == MAX_POSSIBLE_NUMBER_BY_EMPLOYEES) {
            throw new EmployeeStorageIsFullException();
        }
        employeeList.add(employee);
        return employee;
    }

    @Override
    public Employee remove(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employeeList.contains(employee)) {
            employeeList.remove(employee);
            return employee;
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public Employee find(String firstName, String lastName) {
        Employee employee = new Employee(firstName, lastName);
        if (employeeList.contains(employee)) {
            return employee;
        }
        throw new EmployeeNotFoundException();
    }

    @Override
    public List<Employee> findAll() {
        return Collections.unmodifiableList(employeeList);
    }


}
