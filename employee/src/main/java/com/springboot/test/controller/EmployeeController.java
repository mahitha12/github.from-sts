package com.springboot.test.controller;

import java.util.ArrayList;
import java.util.List;
import java.util.Optional;

import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RequestMapping;
import org.springframework.web.bind.annotation.RestController;

import com.springboot.test.entity.Employee;
import com.springboot.test.service.EmployeeService;

@RestController
public class EmployeeController {

	private static final Logger logger = LoggerFactory.getLogger(EmployeeController.class);

	@Autowired
	private EmployeeService service;



	
	
	
	
	@PostMapping("/addEmployee")
	public ResponseEntity<Object> addEmployee(@RequestBody Employee emp) {
		try {
			int eid = emp.getId();
			if (eid > 0) {
				Optional<Employee> opt = service.getByID(eid);
				if (opt.isPresent()) {
					service.addEmployee(emp);
					return new ResponseEntity<>("Data updated succesfully", HttpStatus.OK);
				} else {
					return new ResponseEntity<>("No data found for updation ", HttpStatus.BAD_REQUEST);
				}
			} else {
				service.addEmployee(emp);
			}
		} catch (Exception e) {
			logger.error("Error in EmployeeController at addEmployee()", e);
			return new ResponseEntity<>(HttpStatus.BAD_REQUEST);
		}
		return new ResponseEntity<>("Data inserted succesfully", HttpStatus.OK);

	}
	
	@GetMapping("/getEmployeess")
	public ResponseEntity<Object> getEmployeess() {
		
		logger.info("Entered into EmployeeController  getEmployeess()>>");
		List<Employee> empList = new ArrayList<>(0);
		try {
			empList = service.getEmployees();
			logger.info("List @@@{}", empList.size());
			if (!empList.isEmpty()) {
				return new ResponseEntity<>(empList, HttpStatus.OK);
			}

		} catch (Exception e) {
			logger.error("Error in EmployeeController at getEmployeess()", e);
		}
		return new ResponseEntity<>("NoData", HttpStatus.BAD_REQUEST);

	}

	@SuppressWarnings("unused")
	@GetMapping("/getEmployee/{eid}")
	public ResponseEntity<Object> getEmployeeId(@PathVariable int eid)
			  {
		try
	{
			Optional<Employee> opt = service.getByID(eid);
			if (opt.isPresent()) {
				return new ResponseEntity<>(opt, HttpStatus.OK);
			}
			
		} 
	catch (Exception e) {
			logger.error("Error in EmployeeController at getEmployeeId()", e);
			 
		}
		return new ResponseEntity<>("no data found for thi id number "+eid, HttpStatus.OK);

	}
}

	
	
	
	
	
	

