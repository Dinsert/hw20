package org.skypro.employee.records.service.service;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.skypro.employee.records.service.model.Employee;

public interface DepartmentService {

    int getSalary(int departmentId);

    int getMaxSalary(int departmentId);

    int getMinSalary(int departmentId);

    Map<Integer, List<Employee>> findAllEmployeesWithDivided();

    Collection<Employee> findAllEmployees(int departmentId);
}
