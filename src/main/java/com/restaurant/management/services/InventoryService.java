package com.restaurant.management.services;

import java.util.List;

import com.restaurant.management.dto.InventorySearchDto;
import com.restaurant.management.entities.Inventory;

public interface InventoryService {

	public void saveInventory(Inventory inventory);

	public void editInventory(Inventory inventory);

	public List<Inventory> getInventoryDetails();

	public Inventory getInventoryDetailsById(Long id);

	public String deleteInventoryDetails(Long id);

	public boolean isInventoryIdExists(Long id);

	public List<Inventory> multipulSearchInventory(InventorySearchDto inventorySearchDto);
}
