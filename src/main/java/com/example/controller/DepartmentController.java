package com.example.controller;

import com.example.dto.DepartmentDTO;
import com.example.service.impl.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

@RestController
@RequestMapping(value = "/api/v1")
public class DepartmentController {

    private static Integer resId = 0;
    private final DepartmentService departmentService;

    public DepartmentController(DepartmentService departmentService) {
        this.departmentService = departmentService;
    }

    @PostMapping(value = "/department")
    public ResponseEntity<String> createDepartment(@RequestBody DepartmentDTO departmentDTO) {
        resId = departmentService.addDepartment(departmentDTO);
        if (resId == -1) {
            return ResponseEntity.badRequest().body("unable to save: duplicate entry found");
        }
        return ResponseEntity.ok("saved : true: " + departmentService.addDepartment(departmentDTO));
    }
}
