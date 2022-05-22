package com.restaurant.management.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.restaurant.management.entities.RoomBooking;
import com.restaurant.management.repositories.RoomBookingRepository;

@Service
public class RoomBookingServiceImpl implements RoomBookingService {

	@Autowired
	private RoomBookingRepository roomBookingRepository;

	@Transactional
	public void saveRoomBooking(RoomBooking roomBooking) {
		roomBookingRepository.save(roomBooking);
	}

	@Transactional(readOnly = true)
	public List<RoomBooking> getRoomBookingDetails() {
		return roomBookingRepository.findAll();
	}

	@Transactional
	public boolean isRoomBookingIdExists(Long id) {
		return roomBookingRepository.existsById(id);
	}

	@Transactional(readOnly = true)
	public RoomBooking getRoomBookingDetailById(Long id) {
		return roomBookingRepository.findById(id).get();
	}

	@Transactional
	public String deleteRoomBookingDetail(Long id) {
		roomBookingRepository.deleteById(id);
		return "Room Booking Detail Removed !! " + id;
	}

}
