package com.example.service.impl;

import com.example.dto.EmployeeDTO;
import com.example.model.Employee;
import com.example.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

import static java.util.stream.Collectors.toList;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;

    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository) {
        this.employeeRepository = employeeRepository;
    }

    public boolean saveEmployee(EmployeeDTO employeeDTO) {
        var existingEmployee = employeeRepository.findByEmail(employeeDTO.getEmail());
        var employee = mapDtoToEmployee(employeeDTO);

        if (existingEmployee.isPresent()) {
            return false;
        }
        employeeRepository.save(employee);
        return true;
    }

    public List<Employee> saveMultipleEmployees(List<EmployeeDTO> employeeDTOList) {
        List<Employee> employees = employeeDTOList.stream()
                .map(this::mapDtoToEmployee)
                .collect(toList());
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
        var employees = employeeRepository.findAll();
        return employees;
    }

    public boolean deleteEmployeeById(Integer id) {
        var optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isEmpty()) {
            return false;
        }
        employeeRepository.delete(optionalEmployee.get());
        return true;
    }
}
