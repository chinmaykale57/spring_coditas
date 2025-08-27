package com.example.demo.repository;
import com.example.demo.model.Employee;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.jpa.repository.Query;
import org.springframework.data.repository.query.Param;
import org.springframework.stereotype.Repository;

import java.util.List;
import java.util.Optional;

public interface EmployeeRepository extends JpaRepository<Employee, Long> {
//    custom methods
    Optional<Employee> findById(Long id);
    //List<Employee> findAll();
    List<Employee> findByLastName(String lastName);
    List<Employee> findByDepartment(String department);
    List<Employee> findByEmailContaining(String keyword);

    // Custom query using @Query annotation
    @Query("SELECT e FROM Employee e WHERE e.department = :dept AND e.lastName LIKE %:name%")
    List<Employee> findByDepartmentAndLastNameLike(@Param("dept") String department,
                                                   @Param("name") String lastName);

    // This method is already provided by JpaRepository
    void deleteById(Long id);
}
