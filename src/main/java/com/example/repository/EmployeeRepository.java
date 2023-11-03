package com.example.repository;

import com.example.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Integer> {
    Optional<Employee> findByEmail(String email);

    @Query("SELECT e FROM Employee e WHERE e.firstName LIKE CONCAT(:letter, '%')")
    List<Employee> getEmployeeByNameStartsWith(String letter);

    @Query(value = "SELECT e FROM Employee e WHERE e.department.id = :id")
    List<Employee> findEmployeesByDepartmentId(@Param("id") Integer id);


}
