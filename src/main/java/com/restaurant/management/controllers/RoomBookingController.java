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

import com.restaurant.management.dto.RoomBookingDto;
import com.restaurant.management.entities.RoomBooking;
import com.restaurant.management.mapper.Mapper;
import com.restaurant.management.services.RoomBookingService;
import com.restaurant.management.util.Constants;
import com.restaurant.management.util.EndPointURI;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class RoomBookingController {

	@Autowired
	private RoomBookingService roomBookingService;

	@Autowired
	private Mapper mapper;

	@PostMapping(value = EndPointURI.ROOMBOOKING)
	public ResponseEntity<Object> addRoomBooking(@Valid @RequestBody RoomBookingDto roomBookingDto) {
		roomBookingService.saveRoomBooking(mapper.map(roomBookingDto, RoomBooking.class));
		return new ResponseEntity<>(Constants.ADD_ROOMBOOKING_SUCCESS, HttpStatus.OK);
	}

	@GetMapping(value = EndPointURI.ROOMBOOKING)
	public ResponseEntity<Object> getAllRoomBookingDetails() {
		return new ResponseEntity<Object>(mapper.map(roomBookingService.getRoomBookingDetails(), RoomBookingDto.class),
				HttpStatus.OK);
	}

	@GetMapping(value = EndPointURI.ROOMBOOKING_BY_ID)
	public ResponseEntity<Object> getRoomBookingDetailById(@PathVariable Long id) {
		if (roomBookingService.isRoomBookingIdExists(id)) {
			return new ResponseEntity<>(
					mapper.map(roomBookingService.getRoomBookingDetailById(id), RoomBookingDto.class), HttpStatus.OK);
		}
		return new ResponseEntity<>(Constants.ROOMBOOKING, HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping(value = EndPointURI.ROOMBOOKING_BY_ID)
	public ResponseEntity<Object> deleteRoomBookingDetail(@PathVariable Long id) {
		if (!roomBookingService.isRoomBookingIdExists(id)) {
			return new ResponseEntity<>(Constants.ROOMBOOKING, HttpStatus.BAD_REQUEST);
		}
		roomBookingService.deleteRoomBookingDetail(id);
		return new ResponseEntity<Object>(Constants.DELETE_ROOMBOOKING_SUCCESS, HttpStatus.OK);
	}
}
