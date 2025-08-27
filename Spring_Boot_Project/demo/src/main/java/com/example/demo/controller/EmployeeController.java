package com.example.demo.controller;

import com.example.demo.model.Employee;
import com.example.demo.service.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;
import java.util.Optional;

@RestController
@RequestMapping("/api/employees")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping
    public Employee createEmployee(@RequestBody Employee employee) {
        return employeeService.saveEmployee(employee);
    }
    @GetMapping("/{id}")
    public ResponseEntity<Employee> getEmployeeById(@PathVariable Long id) {
        Optional<Employee> employee = employeeService.getEmployeeById(id);
        return employee.map(ResponseEntity::ok)
                .orElse(ResponseEntity.notFound().build());
    }

    @GetMapping
    public List<Employee> getAllEmployees() {
        return employeeService.getAllEmployees();
    }
    @GetMapping("/lastname/{lastName}")
    public List<Employee> getEmployeesByLastName(@PathVariable String lastName) {
        return employeeService.getEmployeesByLastName(lastName);
    }

    @GetMapping("/department/{dept}")
    public List<Employee> getEmployeesByDepartment(@PathVariable String dept) {
        return employeeService.getEmployeesByDepartment(dept);
    }

    @GetMapping("/email/{keyword}")
    public List<Employee> getEmployeesByEmailContaining(@PathVariable String keyword) {
        return employeeService.getEmployeesByEmailContaining(keyword);
    }

    @GetMapping("/dept/{dept}/name/{name}")
    public List<Employee> getEmployeesByDeptAndName(@PathVariable String dept,
                                                    @PathVariable String name) {
        return employeeService.getEmployeesByDepartmentAndLastName(dept, name);
    }
    @PutMapping("/{id}")
    public ResponseEntity<Employee> updateEmployee(@PathVariable Long id,
                                                   @RequestBody Employee employeeDetails) {
        Employee updatedEmployee = employeeService.updateEmployee(id, employeeDetails);

        if (updatedEmployee != null) {
            return ResponseEntity.ok(updatedEmployee);
        }

        return ResponseEntity.notFound().build();
    }
    @DeleteMapping("/{id}")
    public ResponseEntity<?> deleteEmployee(@PathVariable Long id) {
        boolean isDeleted = employeeService.deleteEmployee(id);

        if (isDeleted) {
            return ResponseEntity.ok().build();
        }

        return ResponseEntity.notFound().build();
    }
}