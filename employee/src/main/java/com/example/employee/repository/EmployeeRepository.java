package com.example.employee.repository;

import com.example.employee.entity.EmployeeEnitity;
import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

@Repository
public interface EmployeeRepository extends JpaRepository<EmployeeEnitity,Long> {
}
