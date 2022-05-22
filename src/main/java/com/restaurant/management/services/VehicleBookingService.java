package com.restaurant.management.services;

import java.util.List;

import com.restaurant.management.entities.VehicleBooking;

public interface VehicleBookingService {

	public void saveVehicleBooking(VehicleBooking vehicleBooking);

	public List<VehicleBooking> getVehicleBookingDetails();

	public boolean isVehicleBookingIdExists(Long id);

	public VehicleBooking getVehicleBookingDetailById(Long id);

	public String deleteVehicleBookingDetail(Long id);

}
