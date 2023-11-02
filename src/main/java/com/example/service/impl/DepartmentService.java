package com.example.service.impl;

import com.example.dto.DepartmentDTO;
import com.example.model.Department;
import com.example.repository.DepartmentRepository;
import lombok.RequiredArgsConstructor;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;

import java.util.List;

@Service
@RequiredArgsConstructor
public class DepartmentService {

    private DepartmentRepository departmentRepository;

    @Autowired
    public DepartmentService(DepartmentRepository departmentRepository) {
        this.departmentRepository = departmentRepository;
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

    private Department mapToDepartmentFromDto(DepartmentDTO departmentDTO) {
        return Department.builder()
                .code(departmentDTO.getCode())
                .name(departmentDTO.getName())
                .build();
    }

    public Department fetchDepartByNameAndCode(DepartmentDTO departmentDTO) {
        var optionalDepartment = departmentRepository.findByNameAndCode(departmentDTO.getName(), departmentDTO.getCode());
        if (optionalDepartment.isEmpty()) {
            return Department.newInstance();
        }
        return optionalDepartment.get();
    }

    public List<Department> fetchAllDepartments() {
        var departmentList = departmentRepository.findAll();
        return departmentList;
    }
}
