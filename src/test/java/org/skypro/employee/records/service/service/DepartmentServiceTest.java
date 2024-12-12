package org.skypro.employee.records.service.service;

import static org.junit.jupiter.api.Assertions.*;
import static org.mockito.Mockito.*;

import java.util.ArrayList;
import java.util.Collection;
import java.util.List;
import java.util.Map;
import java.util.stream.Collectors;
import org.junit.jupiter.api.BeforeEach;
import org.junit.jupiter.api.Test;
import org.junit.jupiter.api.extension.ExtendWith;
import org.mockito.Mock;
import org.mockito.junit.jupiter.MockitoExtension;
import org.skypro.employee.records.service.model.Employee;

@ExtendWith(MockitoExtension.class)
class DepartmentServiceTest {

    @Mock
    private EmployeeService employeeMock;
    private DepartmentService out;
    private List<Employee> employeeList;

    @BeforeEach
    void setUp() {
        out = new DepartmentServiceImpl(employeeMock);
        employeeList = List.of(new Employee("Ivan1", "Ivanov1", 10, 1),
                               new Employee("Ivan2", "Ivanov2", 20, 1),
                               new Employee("Ivan3", "Ivanov3", 30, 2));
    }

    @Test
    void getSalary() {
        when(employeeMock.findAll()).thenReturn(employeeList);
        int actual = out.getSalary(1);
        int excepted = 30;
        assertEquals(excepted, actual);
    }

    @Test
    void getSalaryTransferredDepartmentIdIsMissing() {
        when(employeeMock.findAll()).thenReturn(employeeList);
        int actual = out.getSalary(3);
        int excepted = 0;
        assertEquals(excepted, actual);
    }

    @Test
    void findMaxSalary() {
        when(employeeMock.findAll()).thenReturn(employeeList);
        int actual = out.getMaxSalary(1);
        int excepted = employeeList.get(1).getSalary();
        assertEquals(excepted, actual);
    }

    @Test
    void findMaxSalaryTransferredDepartmentIdIsMissing() {
        when(employeeMock.findAll()).thenReturn(employeeList);
        int actual = out.getMaxSalary(3);
        int excepted = 0;
        assertEquals(excepted, actual);
    }

    @Test
    void findMinSalary() {
        when(employeeMock.findAll()).thenReturn(employeeList);
        int actual = out.getMinSalary(1);
        int excepted = employeeList.get(0).getSalary();
        assertEquals(excepted, actual);
    }

    @Test
    void findMinSalaryTransferredDepartmentIdIsMissing() {
        when(employeeMock.findAll()).thenReturn(employeeList);
        int actual = out.getMinSalary(3);
        int excepted = 0;
        assertEquals(excepted, actual);
    }

    @Test
    void findAllEmployees() {
        when(employeeMock.findAll()).thenReturn(employeeList);
        Collection<Employee> actual = out.findAllEmployees(1);
        Collection<Employee> expected = employeeList.stream()
                                                    .filter(employee -> employee.getDepartmentId() == employeeList.get(0).getDepartmentId())
                                                    .toList();
        assertEquals(expected, actual);
    }

    @Test
    void findAllEmployeesTransferredDepartmentIdIsMissing() {
        when(employeeMock.findAll()).thenReturn(employeeList);
        Collection<Employee> actual = out.findAllEmployees(3);
        Collection<Employee> expected = new ArrayList<>();
        assertEquals(expected, actual);
    }

    @Test
    void findAllEmployeesWithDivided() {
        when(employeeMock.findAll()).thenReturn(employeeList);
        Map<Integer, List<Employee>> actual = out.findAllEmployeesWithDivided();
        Map<Integer, List<Employee>> excepted = employeeList.stream()
                                                            .collect(Collectors.groupingBy(Employee::getDepartmentId));
        assertEquals(excepted, actual);
    }
}