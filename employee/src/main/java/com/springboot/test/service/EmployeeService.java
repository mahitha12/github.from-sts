package com.springboot.test.service;

import com.springboot.test.controller.EmployeeController;
import com.springboot.test.entity.Employee;
import com.springboot.test.repository.EmployeeRepository;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

@Service
	public class EmployeeService {
	
	private static final Logger logger = LoggerFactory.getLogger(EmployeeService.class);

	    @Autowired
	    private EmployeeRepository repository;

	

	public Optional<Employee> getByID(int eid) {
			Optional<Employee> opt = null;
			try {
				opt = repository.findById(eid);
			} catch (Exception e) {
				logger.error("Error in EmployeeServiceImp at getByID()", e);
			}
			return opt;
		}

	public void addEmployee(Employee emp) {
		try {
			repository.save(emp);
		} catch (Exception e) {
			logger.error("Error in EmpServiceImp at addStudent()", e);
		}
	}
	public List<Employee> getEmployees() {
		
			List<Employee> empList = new ArrayList<>();
			try {
				empList = (List<Employee>) repository.findAll();
			} catch (Exception e) {
				logger.error("Error in EmployeeServiceImp at getEmployees()", e);
			}
			return empList;
		}
}
	  
