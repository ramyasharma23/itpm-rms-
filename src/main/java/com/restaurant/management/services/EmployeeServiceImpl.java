package com.restaurant.management.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.BooleanBuilder;
import com.restaurant.management.dto.EmployeeSearchDto;
import com.restaurant.management.entities.Employee;
import com.restaurant.management.entities.QEmployee;
import com.restaurant.management.repositories.EmployeeRepository;
import com.restaurant.management.util.Utils;

@Service
public class EmployeeServiceImpl implements EmployeeService {

	@Autowired
	private EmployeeRepository employeeRepository;

	@Transactional
	public boolean isEmployeeId(String employeeId) {
		return employeeRepository.existsByEmployeeId(employeeId);
	}

	@Transactional
	public void saveEmployee(Employee employee) {
		employeeRepository.save(employee);
	}

	@Transactional(readOnly = true)
	public List<Employee> getAllEmployees() {
		return employeeRepository.findAll();
	}

	@Transactional
	public boolean isEmployeeIdExists(String id) {
		return employeeRepository.existsByEmployeeId(id);
	}

	@Transactional(readOnly = true)
	public Employee getEmployeeByEmployeeId(String employeeId) {
		return employeeRepository.findAllByEmployeeId(employeeId);
	}

	@Transactional
	public boolean isEmployeeIdExists(Long id) {
		return employeeRepository.existsById(id);
	}

	@Override
	public String deleteEmployeeById(Long id) {
		employeeRepository.deleteById(id);
		return "Employee Removed !! " + id;
	}

	@Transactional(readOnly = true)
	public List<Employee> multipulSearchEmployee(EmployeeSearchDto employeeSearchDto) {
		BooleanBuilder booleanBuilder = new BooleanBuilder();

		if (Utils.isNotNullAndEmpty(employeeSearchDto.getAddress())) {
			booleanBuilder.and(QEmployee.employee.address.containsIgnoreCase(employeeSearchDto.getAddress()));
		}
		if (Utils.isNotNullAndEmpty(employeeSearchDto.getEmail())) {
			booleanBuilder.and(QEmployee.employee.email.containsIgnoreCase(employeeSearchDto.getEmail()));
		}
		if (Utils.isNotNullAndEmpty(employeeSearchDto.getEmployeeId())) {
			booleanBuilder.and(QEmployee.employee.employeeId.containsIgnoreCase(employeeSearchDto.getEmployeeId()));
		}
		if (Utils.isNotNullAndEmpty(employeeSearchDto.getJobPost())) {
			booleanBuilder.and(QEmployee.employee.jobPost.containsIgnoreCase(employeeSearchDto.getJobPost()));
		}
		if (Utils.isNotNullAndEmptyId(employeeSearchDto.getMobileNumber())) {
			booleanBuilder.and(QEmployee.employee.mobileNumber.eq(employeeSearchDto.getMobileNumber()));
		}
		return (List<Employee>) employeeRepository.findAll(booleanBuilder);
	}

}