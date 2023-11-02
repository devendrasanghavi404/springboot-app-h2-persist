package com.example.dto;

import com.example.model.Department;
import com.example.model.Employee;
import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
@ToString
public class EmployeeResponseDTO {
    private Employee employee;
    private Department department;
}
