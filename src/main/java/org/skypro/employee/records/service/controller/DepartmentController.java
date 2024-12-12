package org.skypro.employee.records.service.controller;

import java.util.Collection;
import java.util.List;
import java.util.Map;
import org.skypro.employee.records.service.model.Employee;
import org.skypro.employee.records.service.service.DepartmentService;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RequestMapping("/department")
@RestController
public class DepartmentController {

    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @GetMapping("{id}/employees")
    public Collection<Employee> findAllEmployeesByDepartment(@PathVariable int id) {
        return departmentService.findAllEmployees(id);
    }

    @GetMapping("{id}/salary/sum")
    public int getSalaryByDepartment(@PathVariable int id) {
        return departmentService.getSalary(id);
    }

    @GetMapping("{id}/salary/max")
    public int getMaxSalaryByDepartment(@PathVariable int id) {
        return departmentService.getMaxSalary(id);
    }

    @GetMapping("{id}/salary/min")
    public int getMinSalaryByDepartment(@PathVariable int id) {
        return departmentService.getMinSalary(id);
    }

    @GetMapping("/employees")
    public Map<Integer, List<Employee>> findAllEmployeesByDepartmentWithDivided() {
        return departmentService.findAllEmployeesWithDivided();
    }
}
