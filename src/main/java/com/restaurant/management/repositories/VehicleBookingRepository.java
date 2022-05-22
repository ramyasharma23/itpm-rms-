package com.restaurant.management.repositories;

import org.springframework.data.jpa.repository.JpaRepository;
import org.springframework.stereotype.Repository;

import com.restaurant.management.entities.VehicleBooking;

@Repository
public interface VehicleBookingRepository extends JpaRepository<VehicleBooking, Long> {

}
