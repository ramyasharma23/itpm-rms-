package com.restaurant.management.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.BooleanBuilder;
import com.restaurant.management.dto.InventorySearchDto;
import com.restaurant.management.entities.Inventory;
import com.restaurant.management.entities.QBanquet;
import com.restaurant.management.entities.QInventory;
import com.restaurant.management.repositories.InventoryRepository;
import com.restaurant.management.util.Utils;

@Service
public class InventoryServiceImpl implements InventoryService {

	@Autowired
	private InventoryRepository inventoryRepository;

	@Transactional
	public void saveInventory(Inventory inventory) {
		inventoryRepository.save(inventory);
	}

	@Transactional
	public void editInventory(Inventory inventory) {
		inventoryRepository.save(inventory);
	}

	@Transactional(readOnly = true)
	public List<Inventory> getInventoryDetails() {
		return inventoryRepository.findAll();
	}

	@Transactional(readOnly = true)
	public Inventory getInventoryDetailsById(Long id) {
		return inventoryRepository.findById(id).get();
	}

	@Transactional
	public String deleteInventoryDetails(Long id) {
		inventoryRepository.deleteById(id);
		return "Inventory Detail Removed !! " + id;
	}

	@Override
	public boolean isInventoryIdExists(Long id) {
		return inventoryRepository.existsById(id);
	}

	@Transactional(readOnly = true)
	public List<Inventory> multipulSearchInventory(InventorySearchDto inventorySearchDto) {
		BooleanBuilder booleanBuilder = new BooleanBuilder();

		if (Utils.isNotNullAndEmpty(inventorySearchDto.getSupplierName())) {
			booleanBuilder
					.and(QInventory.inventory.supplierName.containsIgnoreCase(inventorySearchDto.getSupplierName()));
		}

		return (List<Inventory>) inventoryRepository.findAll(booleanBuilder);
	}

}
