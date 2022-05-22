package com.restaurant.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurant.management.entities.RoomBooking;

@Repository
public interface RoomBookingRepository extends JpaRepository<RoomBooking, Long> {

}
