package org.skypro.employee.records.service.service;

import static org.junit.jupiter.api.Assertions.*;

import java.util.ArrayList;
import java.util.Collection;
import org.junit.jupiter.api.Test;
import org.skypro.employee.records.service.exceptions.EmployeeAlreadyAddedException;
import org.skypro.employee.records.service.exceptions.EmployeeNotFoundException;
import org.skypro.employee.records.service.exceptions.EmployeeStorageIsFullException;
import org.skypro.employee.records.service.exceptions.InvalidLineException;
import org.skypro.employee.records.service.model.Employee;

class EmployeeServiceTest {

    private final EmployeeService out = new EmployeeServiceImpl();
    private final String firstName = "Ivan";
    private final String lastName = "Ivanov";
    private final int salary = 10;
    private final int departmentId = 1;

    @Test
    void shouldThrowEmployeeAlreadyAddedExceptionAtReAdding() {
        out.add(firstName, lastName, salary, departmentId);
        assertThrows(EmployeeAlreadyAddedException.class, () -> out.add(firstName, lastName, salary, departmentId));
    }

    @Test
    void shouldThrowInvalidLineExceptionAtIncorrectData() {
        assertThrows(InvalidLineException.class, () -> out.add("1", lastName, salary, departmentId));
        assertThrows(InvalidLineException.class, () -> out.add(firstName, "2", salary, departmentId));
        assertThrows(InvalidLineException.class, () -> out.add("1", "2", salary, departmentId));
    }

    @Test
    void shouldThrowEmployeeStorageIsFullExceptionAtOverflowStorageEmployees() {
        out.add(firstName, lastName, salary, departmentId);
        assertThrows(EmployeeStorageIsFullException.class, () -> out.add("firstName", lastName, salary, departmentId));
    }

    @Test
    void remove() {
        out.add(firstName, lastName, salary, departmentId);
        out.remove(firstName, lastName);
        Collection<Employee> actual = out.findAll();
        Collection<Employee> expected = new ArrayList<>();
        assertEquals(expected, actual);
    }

    @Test
    void shouldThrowEmployeeNotFoundExceptionIfEmployeeNotFoundAtRemove() {
        assertThrows(EmployeeNotFoundException.class, () -> out.remove(firstName, lastName));
    }

    @Test
    void find() {
        out.add(firstName, lastName, salary, departmentId);
        Employee actual = out.find(firstName, lastName);
        Employee expected = new Employee(firstName, lastName, salary, departmentId);
        assertEquals(expected, actual);
    }

    @Test
    void shouldThrowEmployeeNotFoundExceptionIfEmployeeNotFoundAtFind() {
        assertThrows(EmployeeNotFoundException.class, () -> out.find(firstName, lastName));
    }

    @Test
    void findAll() {
        out.add(firstName, lastName, salary, departmentId);
        Collection<Employee> actual = out.findAll();
        Collection<Employee> expected = new ArrayList<>();
        expected.add(new Employee(firstName, lastName, salary, departmentId));
        assertEquals(expected, actual);
    }
}