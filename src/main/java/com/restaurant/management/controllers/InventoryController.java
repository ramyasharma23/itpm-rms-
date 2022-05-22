package com.restaurant.management.controllers;

import java.util.ArrayList;
import java.util.List;

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

import com.restaurant.management.dto.InventoryDto;
import com.restaurant.management.dto.InventorySearchDto;
import com.restaurant.management.entities.Inventory;
import com.restaurant.management.mapper.Mapper;
import com.restaurant.management.services.InventoryService;
import com.restaurant.management.util.Constants;
import com.restaurant.management.util.EndPointURI;

@CrossOrigin(origins = "http://localhost:3000")
@RestController
public class InventoryController {

	@Autowired
	private InventoryService inventoryService;
	@Autowired
	private Mapper mapper;

	@PostMapping(value = EndPointURI.INVENTORY)
	public ResponseEntity<Object> addInventory(@Valid @RequestBody InventoryDto inventoryDto) {
		inventoryService.saveInventory(mapper.map(inventoryDto, Inventory.class));
		return new ResponseEntity<>(Constants.ADD_INVENTORY_SUCCESS, HttpStatus.OK);
	}

	@PutMapping(value = EndPointURI.INVENTORY)
	public ResponseEntity<Object> editInventory(@Valid @RequestBody InventoryDto inventoryDto) {
		if (!inventoryService.isInventoryIdExists(inventoryDto.getId())) {
			return new ResponseEntity<>(Constants.INVENTORY, HttpStatus.BAD_REQUEST);
		}
		Inventory invent = new Inventory();
		invent = inventoryService.getInventoryDetailsById(inventoryDto.getId());
		inventoryDto.setSupplierDisappliedDate(invent.getSupplierDisappliedDate());
		inventoryService.editInventory(mapper.map(inventoryDto, Inventory.class));
		return new ResponseEntity<>(Constants.EDIT_INVENTORY_SUCCESS, HttpStatus.OK);
	}

	@GetMapping(value = EndPointURI.INVENTORY)
	public ResponseEntity<Object> getAllInventoryDetails() {
		return new ResponseEntity<Object>(mapper.map(inventoryService.getInventoryDetails(), InventoryDto.class),
				HttpStatus.OK);
	}

	@GetMapping(value = EndPointURI.INVENTORY_BY_ID)
	public ResponseEntity<Object> getInventoryDetailById(@PathVariable Long id) {
		if (inventoryService.isInventoryIdExists(id)) {
			return new ResponseEntity<>(mapper.map(inventoryService.getInventoryDetailsById(id), InventoryDto.class),
					HttpStatus.OK);
		}
		return new ResponseEntity<>(Constants.INVENTORY, HttpStatus.BAD_REQUEST);
	}

	@GetMapping(value = EndPointURI.INVENTORY_SEARCH)
	public ResponseEntity<Object> searchInventory(InventorySearchDto inventorySearchDto) {
		return new ResponseEntity<>(
				mapper.map(inventoryService.multipulSearchInventory(inventorySearchDto), InventoryDto.class),
				HttpStatus.OK);
	}

	@DeleteMapping(value = EndPointURI.INVENTORY_BY_ID)
	public ResponseEntity<Object> deleteInventoryDetail(@PathVariable Long id) {
		if (!inventoryService.isInventoryIdExists(id)) {
			return new ResponseEntity<>(Constants.INVENTORY, HttpStatus.BAD_REQUEST);
		}
		inventoryService.deleteInventoryDetails(id);
		return new ResponseEntity<Object>(Constants.DELETE_INVENTORY_SUCCESS, HttpStatus.OK);
	}

}
