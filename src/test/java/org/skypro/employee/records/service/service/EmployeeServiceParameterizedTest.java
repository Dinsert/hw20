package org.skypro.employee.records.service.service;

import static org.junit.jupiter.api.Assertions.assertEquals;
import static org.junit.jupiter.api.Assertions.assertThrows;

import java.util.ArrayList;
import java.util.Collection;
import org.junit.jupiter.params.ParameterizedTest;
import org.junit.jupiter.params.provider.CsvSource;
import org.skypro.employee.records.service.exceptions.EmployeeAlreadyAddedException;
import org.skypro.employee.records.service.exceptions.EmployeeNotFoundException;
import org.skypro.employee.records.service.exceptions.EmployeeStorageIsFullException;
import org.skypro.employee.records.service.exceptions.InvalidLineException;
import org.skypro.employee.records.service.model.Employee;

public class EmployeeServiceParameterizedTest {

    private final EmployeeService out = new EmployeeServiceImpl();

    @ParameterizedTest
    @CsvSource({"Ivan, Ivanov, 10, 1"})
    void shouldThrowEmployeeAlreadyAddedExceptionAtReAdding(String firstName, String lastName, int salary, int departmentId) {
        out.add(firstName, lastName, salary, departmentId);
        assertThrows(EmployeeAlreadyAddedException.class, () -> out.add(firstName, lastName, salary, departmentId));
    }

    @ParameterizedTest
    @CsvSource({"1, Ivanov, 10, 1", "Ivan, 2, 10, 1", "1, 2, 10, 1"})
    void shouldThrowInvalidLineExceptionAtIncorrectData(String firstName, String lastName, int salary, int departmentId) {
        assertThrows(InvalidLineException.class, () -> out.add(firstName, lastName, salary, departmentId));
    }

    @ParameterizedTest
    @CsvSource({"Ivan, Ivanov, 10, 1"})
    void shouldThrowEmployeeStorageIsFullExceptionAtOverflowStorageEmployees(String firstName, String lastName, int salary, int departmentId) {
        out.add(firstName, lastName, salary, departmentId);
        assertThrows(EmployeeStorageIsFullException.class, () -> out.add("firstName", lastName, salary, departmentId));
    }

    @ParameterizedTest
    @CsvSource({"Ivan, Ivanov, 10, 1"})
    void remove(String firstName, String lastName, int salary, int departmentId) {
        out.add(firstName, lastName, salary, departmentId);
        out.remove(firstName, lastName);
        Collection<Employee> actual = out.findAll();
        Collection<Employee> expected = new ArrayList<>();
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({"Ivan, Ivanov"})
    void shouldThrowEmployeeNotFoundExceptionIfEmployeeNotFoundAtRemove(String firstName, String lastName) {
        assertThrows(EmployeeNotFoundException.class, () -> out.remove(firstName, lastName));
    }

    @ParameterizedTest
    @CsvSource({"Ivan, Ivanov, 10, 1"})
    void find(String firstName, String lastName, int salary, int departmentId) {
        out.add(firstName, lastName, salary, departmentId);
        Employee actual = out.find(firstName, lastName);
        Employee expected = new Employee(firstName, lastName, salary, departmentId);
        assertEquals(expected, actual);
    }

    @ParameterizedTest
    @CsvSource({"Ivan, Ivanov"})
    void shouldThrowEmployeeNotFoundExceptionIfEmployeeNotFoundAtFind(String firstName, String lastName) {
        assertThrows(EmployeeNotFoundException.class, () -> out.find(firstName, lastName));
    }

    @ParameterizedTest
    @CsvSource({"Ivan, Ivanov, 10, 1"})
    void findAll(String firstName, String lastName, int salary, int departmentId) {
        out.add(firstName, lastName, salary, departmentId);
        Collection<Employee> actual = out.findAll();
        Collection<Employee> expected = new ArrayList<>();
        expected.add(new Employee(firstName, lastName, salary, departmentId));
        assertEquals(expected, actual);
    }
}
