package com.example.model;

import com.fasterxml.jackson.annotation.JsonBackReference;
import jakarta.persistence.*;
import lombok.*;

import java.util.List;

@Entity
@Table
@AllArgsConstructor
@NoArgsConstructor
@Getter
@Setter
@Builder
public class Department {
    @Id
    @GeneratedValue(strategy = GenerationType.IDENTITY)
    private Integer id;
    private String name;
    private String code;

    @OneToMany(mappedBy = "department")
    @JsonBackReference
    private List<Employee> employees;

    public static Department newInstance() {
        var department = new Department();
        department.id = 0;
        department.name = "";
        department.code = "";
        return department;
    }

    @Transient
    private List<Employee> employeeList;
}
