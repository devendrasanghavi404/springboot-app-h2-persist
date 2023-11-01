package com.example.dto;

import lombok.*;

@AllArgsConstructor
@NoArgsConstructor
@ToString
@Getter
@Setter
@Builder
public class EmployeeDTO {

    private String firstName;
    private String lastName;
    private String email;
    private Double salary;
    private String grade;
}
