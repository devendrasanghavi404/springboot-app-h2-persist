package com.example.controller;

import com.example.dto.DepartmentDTO;
import com.example.model.Department;
import com.example.service.impl.DepartmentService;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.*;

import java.util.List;

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
        return ResponseEntity.ok("saved : true: " + resId);
    }

    @GetMapping("/departments")
    public ResponseEntity<List<Department>> getAllDepartments() {
        return ResponseEntity.ok(departmentService.fetchAllDepartments());
    }

    @GetMapping("/department/getEmployeesByDepartmentId/")
    public ResponseEntity<Department> getDepartmentById(@RequestParam Integer deptId){
        return ResponseEntity.ok(departmentService.getEmployeesByDepartmentId(deptId));
    }
}
