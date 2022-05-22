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
import org.springframework.web.bind.annotation.PutMapping;
import org.springframework.web.bind.annotation.RequestBody;
import org.springframework.web.bind.annotation.RestController;

import com.restaurant.management.dto.BanquetDto;
import com.restaurant.management.dto.BanquetSearchDto;
import com.restaurant.management.entities.Banquet;
import com.restaurant.management.mapper.Mapper;
import com.restaurant.management.services.BanquetService;
import com.restaurant.management.util.Constants;
import com.restaurant.management.util.EndPointURI;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class BanquetController {

	@Autowired
	private BanquetService banquetService;

	@Autowired
	private Mapper mapper;

	@PostMapping(value = EndPointURI.BANQUET)
	public ResponseEntity<Object> addBanquet(@Valid @RequestBody BanquetDto banquetDto) {
		if (banquetService.isDateOfEventExists(banquetDto.getDateOfEvent())) {
			return new ResponseEntity<>(Constants.BANQUET, HttpStatus.BAD_REQUEST);
		}
		banquetService.saveBanquet(mapper.map(banquetDto, Banquet.class));
		return new ResponseEntity<>(Constants.ADD_BANQUET_SUCCESS, HttpStatus.OK);
	}

	@GetMapping(value = EndPointURI.BANQUET)
	public ResponseEntity<Object> getAllBanquets() {
		return new ResponseEntity<Object>(mapper.map(banquetService.getBanquets(), BanquetDto.class), HttpStatus.OK);
	}

	@GetMapping(value = EndPointURI.BANQUET_BY_ID)
	public ResponseEntity<Object> getBanquetById(@PathVariable Long id) {
		if (banquetService.isBanquetIdExists(id)) {
			return new ResponseEntity<>(mapper.map(banquetService.getBanquetById(id), BanquetDto.class), HttpStatus.OK);
		}
		return new ResponseEntity<>(Constants.BANQUET, HttpStatus.BAD_REQUEST);
	}

	@GetMapping(value = EndPointURI.BANQUET_SEARCH)
	public ResponseEntity<Object> searchBanquet(BanquetSearchDto banquetSearchDto) {
		return new ResponseEntity<>(
				mapper.map(banquetService.multipulSearchBanquet(banquetSearchDto), BanquetDto.class), HttpStatus.OK);
	}

	@DeleteMapping(value = EndPointURI.BANQUET_BY_ID)
	public ResponseEntity<Object> deleteBanquetDetail(@PathVariable Long id) {
		if (!banquetService.isBanquetIdExists(id)) {
			return new ResponseEntity<>(Constants.BANQUET, HttpStatus.BAD_REQUEST);
		}
		banquetService.deleteBanquetDetail(id);
		return new ResponseEntity<Object>(Constants.DELETE_BANQUET_SUCCESS, HttpStatus.OK);
	}

	@PutMapping(value = EndPointURI.BANQUET)
	public ResponseEntity<Object> editBanquet(@Valid @RequestBody BanquetDto banquetDto) {
		if (!banquetService.isBanquetIdExists(banquetDto.getId())) {
			return new ResponseEntity<>(Constants.BANQUET, HttpStatus.BAD_REQUEST);
		}

		if (banquetService.isDateOfEventExists(banquetDto.getDateOfEvent())) {
			return new ResponseEntity<>(Constants.BANQUET, HttpStatus.BAD_REQUEST);
		}
		Banquet banq = new Banquet();
		banq = banquetService.getBanquetById(banquetDto.getId());
		banquetDto.setDateOfEvent(banq.getDateOfEvent());
		banquetService.updateBanquet(mapper.map(banquetDto, Banquet.class));
		return new ResponseEntity<>(Constants.UPDATE_BANQUET_SUCCESS, HttpStatus.OK);
	}
}
