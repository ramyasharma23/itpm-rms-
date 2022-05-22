package com.restaurant.management.services;

import java.util.List;

import com.restaurant.management.entities.RoomBooking;

public interface RoomBookingService {

	public void saveRoomBooking(RoomBooking roomBooking);

	public List<RoomBooking> getRoomBookingDetails();

	public boolean isRoomBookingIdExists(Long id);

	public RoomBooking getRoomBookingDetailById(Long id);

	public String deleteRoomBookingDetail(Long id);

}
