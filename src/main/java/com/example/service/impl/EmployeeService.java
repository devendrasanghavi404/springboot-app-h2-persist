package com.example.service.impl;

import com.example.dto.EmployeeDTO;
import com.example.model.Employee;
import com.example.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public boolean saveEmployee(EmployeeDTO employeeDTO) {
        if (employeeRepository.findByEmail(employeeDTO.getEmail()).isPresent()) {
            return false;
        }
        Employee employee = mapDtoToEmployee(employeeDTO);
        employeeRepository.save(employee);
        return true;
    }

    public List<Employee> saveMultipleEmployees(List<EmployeeDTO> employeeDTOList) {
        List<Employee> employees = employeeDTOList.stream()
                .map(this::mapDtoToEmployee)
                .collect(Collectors.toList());
        return employeeRepository.saveAll(employees);
    }

    private Employee mapDtoToEmployee(EmployeeDTO employeeDTO) {
        return Employee.builder()
                .firstName(employeeDTO.getFirstName())
                .lastName(employeeDTO.getLastName())
                .email(employeeDTO.getEmail())
                .grade(employeeDTO.getGrade())
                .salary(employeeDTO.getSalary())
                .build();
    }

    public List<Employee> fetchAllEmployees() {
        return employeeRepository.findAll();
    }

    public List<String> fetchEmployeeByNameStartsWith(String alphabet) {
        return employeeRepository.getEmployeeByNameStartsWith(alphabet)
                .stream()
                .map(employee -> employee.getFirstName() + " " + employee.getLastName())
                .collect(Collectors.toList());
    }

    public HashMap<String, Double> fetchEmployeeWithSalaryGreaterThan(Integer salary) {
        return fetchAllEmployees().stream()
                .filter(employee -> employee.getSalary() >= salary)
                .collect(Collectors.toMap(
                        employee -> employee.getFirstName() + " " + employee.getLastName(),
                        Employee::getSalary,
                        (oldValue, newValue) -> oldValue,
                        HashMap::new
                ));
    }

    public boolean deleteEmployeeById(Integer id) {
        return employeeRepository.findById(id)
                .map(employee -> {
                    employeeRepository.delete(employee);
                    return true;
                })
                .orElse(false);
    }
}
