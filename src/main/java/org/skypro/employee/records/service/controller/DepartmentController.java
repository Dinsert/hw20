package org.skypro.employee.records.service.controller;

import java.util.Collection;
import java.util.Optional;
import org.skypro.employee.records.service.model.Employee;
import org.skypro.employee.records.service.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RequestParam;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/departments")
@RestController
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("max-salary")
    public Optional<Employee> findEmployeeByDepartmentWithMaxSalary(@RequestParam int departmentId) {
        return departmentService.findMaxSalary(departmentId);
    }

    @GetMapping("min-salary")
    public Optional<Employee> findEmployeeByDepartmentWithMinSalary(@RequestParam int departmentId) {
        return departmentService.findMinSalary(departmentId);
    }

    @GetMapping("all")
    public Collection<Employee> findAllEmployeesByDepartmentOrAllWithDivided(@RequestParam(required = false) Integer departmentId) {
        return departmentService.findAllEmployeesWithDivided(departmentId);
    }

}
