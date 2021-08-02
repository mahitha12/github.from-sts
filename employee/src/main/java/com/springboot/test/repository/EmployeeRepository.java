package com.springboot.test.repository;

import org.springframework.data.jpa.repository.JpaRepository;

import com.springboot.test.entity.Employee;

public interface EmployeeRepository extends JpaRepository<Employee, Integer>{

}
