package com.example.controller;

import com.example.dto.EmployeeDTO;
import com.example.model.Employee;
import com.example.service.impl.EmployeeService;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.HashMap;
import java.util.List;

@RestController
@RequestMapping(value = "/api/v1")
public class EmployeeController {

    @Autowired
    private EmployeeService employeeService;

    @PostMapping(value = "/employee")
    public ResponseEntity<String> createOneEmployee(@RequestBody EmployeeDTO employeeDTO) {
        return employeeService.saveEmployee(employeeDTO) ? ResponseEntity.ok("saved: true") :
                ResponseEntity.badRequest().body("unable to save");
    }

    @PostMapping(value = "/emplyoees")
    public ResponseEntity<List<Employee>> createAllEmployees(@RequestBody List<EmployeeDTO> employeeDTOList) {
        return ResponseEntity.ok(employeeService.saveMultipleEmployees(employeeDTOList));
    }

    @GetMapping(value = "/employees")
    public ResponseEntity<List<Employee>> fetchAllEmployees() {
        return ResponseEntity.ok(employeeService.fetchAllEmployees());
    }

    @GetMapping(value = "/employee/{starts}")
    public ResponseEntity<List<String>> fetchAllEmployeesByNameStartsWith(@PathVariable String starts) {
        return ResponseEntity.ok(employeeService.fetchEmployeeByNameStartsWith(starts));
    }

    @GetMapping(value = "/employees/{salary}")
    public ResponseEntity<HashMap<String, Double>> fetchEmployeeWithGreaterSalary(@PathVariable Integer salary) {
        return ResponseEntity.ok(employeeService.fetchEmployeeWithSalaryGreaterThan(salary));
    }

    @DeleteMapping(value = "/employee/{id}")
    public ResponseEntity<String> removeEmployeeById(@PathVariable Integer id) {
        return employeeService.deleteEmployeeById(id) ? ResponseEntity.ok("deleted: true") :
                ResponseEntity.badRequest().body("deleted: false");
    }
}
