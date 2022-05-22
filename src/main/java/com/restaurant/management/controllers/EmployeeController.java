package com.restaurant.management.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.CrossOrigin;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.management.dto.EmployeeDto;
import com.restaurant.management.dto.RoomBookingDto;
import com.restaurant.management.entities.Employee;
import com.restaurant.management.mapper.Mapper;
import com.restaurant.management.services.EmployeeService;
import com.restaurant.management.util.Constants;
import com.restaurant.management.util.EndPointURI;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class EmployeeController {

	@Autowired
	private EmployeeService employeeService;

	@Autowired
	private Mapper mapper;

	@PostMapping(value = EndPointURI.EMPLOYEE)
	public ResponseEntity<Object> addEmployee(@Valid @RequestBody EmployeeDto employeeDto) {
		if (employeeService.isEmployeeId(employeeDto.getEmployeeId())) {
			return new ResponseEntity<>(Constants.EMPLOYEE, HttpStatus.BAD_REQUEST);
		}
		employeeService.saveEmployee(mapper.map(employeeDto, Employee.class));
		return new ResponseEntity<>(Constants.ADD_EMPLOYEE_SUCCESS, HttpStatus.OK);
	}

	@GetMapping(value = EndPointURI.EMPLOYEE)
	public ResponseEntity<Object> getAllEmployee() {
		return new ResponseEntity<Object>(mapper.map(employeeService.getAllEmployees(), EmployeeDto.class),
				HttpStatus.OK);
	}

	@GetMapping(value = EndPointURI.EMPLOYEE_BY_EMPLOYEE_ID)
	public ResponseEntity<Object> getEmployeeByEmployeeId(@PathVariable String employeeId) {
		if (employeeService.isEmployeeIdExists(employeeId)) {
			return new ResponseEntity<>(
					mapper.map(employeeService.getEmployeeByEmployeeId(employeeId), EmployeeDto.class), HttpStatus.OK);
		}
		return new ResponseEntity<>(Constants.EMPLOYEE, HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping(value = EndPointURI.EMPLOYEE_BY_ID)
	public ResponseEntity<Object> deleteEmployeeById(@PathVariable Long id) {
		if (!employeeService.isEmployeeIdExists(id)) {
			return new ResponseEntity<>(Constants.EMPLOYEE, HttpStatus.BAD_REQUEST);
		}
		employeeService.deleteEmployeeById(id);
		return new ResponseEntity<Object>(Constants.DELETE_EMPLOYEE_SUCCESS, HttpStatus.OK);
	}

}
