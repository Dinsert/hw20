package org.skypro.employee.records.service.service;

import java.util.List;
import org.skypro.employee.records.service.model.Employee;

public interface EmployeeService {

    Employee add(String firstName, String lastName);

    Employee remove(String firstName, String lastName);

    Employee find(String firstName, String lastName);

    List<Employee> findAll();
}
