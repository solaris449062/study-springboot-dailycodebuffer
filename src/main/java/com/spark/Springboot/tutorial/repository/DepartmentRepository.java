package com.spark.Springboot.tutorial.repository;

import com.spark.Springboot.tutorial.entity.Department;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface DepartmentRepository extends JpaRepository<Department, Long> {
    public Department findByDepartmentName(String name);

    public Department findByDepartmentNameIgnoreCase(String name);
}
