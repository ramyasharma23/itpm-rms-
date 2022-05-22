package com.restaurant.management.repositories;

import java.sql.Date;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.restaurant.management.entities.Banquet;

@Repository
public interface BanquetRepository extends JpaRepository<Banquet, Long>, QuerydslPredicateExecutor<Banquet> {
	public boolean existsByDateOfEvent(Date dateOfEvent);
}
