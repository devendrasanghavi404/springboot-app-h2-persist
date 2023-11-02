package com.example.dto;

import com.example.model.Department;
import com.example.model.Employee;
import lombok.*;

import java.util.List;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class DepartmentResponseDTO {
    private Department department;
    private List<Employee> employeeList;
}
