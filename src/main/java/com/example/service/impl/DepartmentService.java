package com.example.service.impl;

import com.example.dto.DepartmentDTO;
import com.example.model.Department;
import com.example.model.Employee;
import com.example.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.web.client.RestTemplate;

import java.util.Arrays;
import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private DepartmentRepository departmentRepository;

    private RestTemplate restTemplate;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository, RestTemplate restTemplate) {
        this.departmentRepository = departmentRepository;
        this.restTemplate = restTemplate;
    }

    public Integer addDepartment(DepartmentDTO departmentDTO) {
        var optionalDepartment = departmentRepository.findByNameAndCode(departmentDTO.getName(), departmentDTO.getCode());
        if (optionalDepartment.isPresent()) {
            return -1; // Department already exists
        } else {
            var department = mapToDepartmentFromDto(departmentDTO);
            department = departmentRepository.save(department);
            return department.getId();
        }
    }

    public Department getEmployeesByDepartmentId(Integer deptId) {
        var optionalDepartment = departmentRepository.findById(deptId);
        if (optionalDepartment.isEmpty()) {
            return null;
        }
        Employee[] empArray = restTemplate.getForObject("http://localhost:9090/api/v1/employees/getEmployeesByDepartmentId/?id=" + deptId, Employee[].class);
        List<Employee> employees = Arrays.asList(empArray);
        var department = optionalDepartment.get();
        department.setEmployeeList(employees);
        return department;
    }

    private Department mapToDepartmentFromDto(DepartmentDTO departmentDTO) {
        return Department.builder()
                .code(departmentDTO.getCode())
                .name(departmentDTO.getName())
                .build();
    }

    public List<Department> fetchAllDepartments() {
        return departmentRepository.findAll();
    }

//    public List<Employee> getByEmployeesByDepartmentId(Integer id) {
//        var department = departmentRepository.findById(id);
//        if (department.isEmpty()) {
//            return null;
//        }
//        var employeesInDepartment = department.get().getEmployees();
//        return employeesInDepartment;
//    }

    public Department getDepartmentById(Integer deptId) {
        var optionalDepartment = departmentRepository.findById(deptId);
        if (optionalDepartment.isEmpty()) {
            return null;
        }
        return optionalDepartment.get();
    }
}
