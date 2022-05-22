package com.restaurant.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.restaurant.management.entities.Employee;

@Repository
public interface EmployeeRepository extends JpaRepository<Employee, Long>, QuerydslPredicateExecutor<Employee>  {

	public boolean existsByEmployeeId(String employeeId);

	public Employee findAllByEmployeeId(String employeeId);

}
