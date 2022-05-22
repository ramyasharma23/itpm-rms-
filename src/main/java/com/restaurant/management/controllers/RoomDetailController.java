package com.restaurant.management.controllers;

import javax.validation.Valid;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.http.HttpStatus;
import org.springframework.http.ResponseEntity;
import org.springframework.web.bind.annotation.DeleteMapping;
import org.springframework.web.bind.annotation.GetMapping;
import org.springframework.web.bind.annotation.PathVariable;
import org.springframework.web.bind.annotation.PostMapping;
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;
import com.restaurant.management.dto.RoomDetailDto;
import com.restaurant.management.dto.RoomDetailSearchDto;
import com.restaurant.management.entities.RoomDetail;
import com.restaurant.management.mapper.Mapper;
import com.restaurant.management.services.RoomDetailService;
import com.restaurant.management.util.Constants;
import com.restaurant.management.util.EndPointURI;

@RestController
public class RoomDetailController {

	@Autowired
	private RoomDetailService roomdetailService;

	@Autowired
	private Mapper mapper;

	@PostMapping(value = EndPointURI.ROOMDETAIL)
	public ResponseEntity<Object> addBanquet(@Valid @RequestBody RoomDetailDto roomDetailDto) {
		if (roomdetailService.isRoomNoExists(roomDetailDto.getRoomNo())) {
			return new ResponseEntity<>(Constants.ROOMDETAIL, HttpStatus.BAD_REQUEST);
		}
		roomdetailService.saveRoomDetail(mapper.map(roomDetailDto, RoomDetail.class));
		return new ResponseEntity<>(Constants.ADD_ROOMDETAIL_SUCCESS, HttpStatus.OK);
	}

	@PutMapping(value = EndPointURI.ROOMDETAIL)
	public ResponseEntity<Object> EditBanquet(@Valid @RequestBody RoomDetailDto roomDetailDto) {
		if (!roomdetailService.isRoomDetailIdExists(roomDetailDto.getId())) {
			return new ResponseEntity<>(Constants.ROOMDETAIL, HttpStatus.BAD_REQUEST);
		}
		if (roomdetailService.isRoomNoExists(roomDetailDto.getRoomNo())) {
			return new ResponseEntity<>(Constants.ROOMDETAIL, HttpStatus.BAD_REQUEST);
		}
		roomdetailService.editRoomDetail(mapper.map(roomDetailDto, RoomDetail.class));
		return new ResponseEntity<>(Constants.EDIT_ROOMDETAIL_SUCCESS, HttpStatus.OK);
	}

	@GetMapping(value = EndPointURI.ROOMDETAIL)
	public ResponseEntity<Object> getAllBanquets() {
		return new ResponseEntity<Object>(mapper.map(roomdetailService.getRoomDetails(), RoomDetailDto.class),
				HttpStatus.OK);
	}

	@GetMapping(value = EndPointURI.ROOMDETAIL_BY_ID)
	public ResponseEntity<Object> getBanquetById(@PathVariable Long id) {
		if (roomdetailService.isRoomDetailIdExists(id)) {
			return new ResponseEntity<>(mapper.map(roomdetailService.getRoomDetailById(id), RoomDetailDto.class),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(Constants.ROOMDETAIL, HttpStatus.BAD_REQUEST);
	}

	@DeleteMapping(value = EndPointURI.ROOMDETAIL_BY_ID)
	public ResponseEntity<Object> deleteBanquetDetail(@PathVariable Long id) {
		if (!roomdetailService.isRoomDetailIdExists(id)) {
			return new ResponseEntity<>(Constants.ROOMDETAIL, HttpStatus.BAD_REQUEST);
		}
		roomdetailService.deleteRoomDetailById(id);
		return new ResponseEntity<Object>(Constants.DELETE_ROOMDETAIL_SUCCESS, HttpStatus.OK);
	}

	@GetMapping(value = EndPointURI.ROOMDETAIL_SEARCH)
	public ResponseEntity<Object> searchRoomDetail(RoomDetailSearchDto roomDetailSearchDto) {
		return new ResponseEntity<>(
				mapper.map(roomdetailService.multipleSearchRoomDetail(roomDetailSearchDto), RoomDetailSearchDto.class),
				HttpStatus.OK);
	}

}
