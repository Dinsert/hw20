package org.skypro.employee.records.service.service;

import java.util.Collection;
import org.skypro.employee.records.service.model.Employee;

public interface EmployeeService {

    Employee add(String firstName, String lastName, int salary, int departmentId);

    Employee remove(String firstName, String lastName);

    Employee find(String firstName, String lastName);

    Collection<Employee> findAll();
}
