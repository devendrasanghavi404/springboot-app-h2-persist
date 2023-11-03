package com.example.service.impl;

import com.example.dto.DepartmentDTO;
import com.example.dto.EmployeeDTO;
import com.example.dto.EmployeeResponseDTO;
import com.example.model.Department;
import com.example.model.Employee;
import com.example.repository.DepartmentRepository;
import com.example.repository.EmployeeRepository;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.HashMap;
import java.util.List;
import java.util.stream.Collectors;

@Service
public class EmployeeService {

    private final EmployeeRepository employeeRepository;
    private final DepartmentRepository departmentRepository;


    @Autowired
    public EmployeeService(EmployeeRepository employeeRepository, DepartmentRepository departmentRepository) {
        this.employeeRepository = employeeRepository;
        this.departmentRepository = departmentRepository;
    }

    public Integer saveEmployee(EmployeeDTO employeeDTO) {
        if (employeeRepository.findByEmail(employeeDTO.getEmail()).isPresent()) {
            return -1;
        }

        var optionalDepartment = departmentRepository.findByNameAndCode(employeeDTO.getDepartmentDTO().getName(), employeeDTO.getDepartmentDTO().getCode());
        Department department;
        if (optionalDepartment.isEmpty()) {
            // Create a new Department from the DTO
            var departmentDTO = mapDtoToDepartment(employeeDTO);
            department = new Department();
            department.setCode(departmentDTO.getCode());
            department.setName(departmentDTO.getName());
            department = departmentRepository.save(department);
        } else {
            department = optionalDepartment.get();
        }
        Employee employee = mapDtoToEmployee(employeeDTO);
        employee.setDepartment(department);

        Employee savedEmployee = employeeRepository.save(employee);
        return savedEmployee.getId();
    }

    public List<Employee> saveMultipleEmployees(List<EmployeeDTO> employeeDTOList) {
        List<Employee> employees = employeeDTOList.stream()
                .map(this::mapDtoToEmployee)
                .collect(Collectors.toList());
        return employeeRepository.saveAll(employees);
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


    private Employee mapDtoToEmployee(EmployeeDTO employeeDTO) {
        return Employee.builder()
                .firstName(employeeDTO.getFirstName())
                .lastName(employeeDTO.getLastName())
                .email(employeeDTO.getEmail())
                .grade(employeeDTO.getGrade())
                .salary(employeeDTO.getSalary())
                .build();
    }

    private DepartmentDTO mapDtoToDepartment(EmployeeDTO employeeDTO) {
        return DepartmentDTO.builder()
                .name(employeeDTO.getDepartmentDTO().getName())
                .code(employeeDTO.getDepartmentDTO().getCode())
                .build();
    }

    public EmployeeResponseDTO fetchOneEmployee(Integer id) {
        EmployeeResponseDTO responseDTO = new EmployeeResponseDTO();
        var optionalEmployee = employeeRepository.findById(id);
        if (optionalEmployee.isEmpty()) {
            return responseDTO;
        }
        return mapEmployeeToResponseDTO(optionalEmployee.get());
    }

    private EmployeeResponseDTO mapEmployeeToResponseDTO(Employee employee) {
        return EmployeeResponseDTO.builder()
                .employee(employee)
                .department(employee.getDepartment())
                .build();
    }

    public List<Employee> fetchAllEmployeesByDepartmentId(Integer id) {
        var listOfEmployees = employeeRepository.findAll();
        listOfEmployees = listOfEmployees.stream().filter(i -> i.getDepartment().getId()==(id)).collect(Collectors.toList());
        return listOfEmployees;
    }
}
