package com.restaurant.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.data.querydsl.QuerydslPredicateExecutor;
import org.springframework.stereotype.Repository;

import com.restaurant.management.entities.RoomDetail;

@Repository
public interface RoomDetailRepository extends JpaRepository<RoomDetail, Long>, QuerydslPredicateExecutor<RoomDetail> {
	public boolean existsByRoomNo(Long roomNo);
}
