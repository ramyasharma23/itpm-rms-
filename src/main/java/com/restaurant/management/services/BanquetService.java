package com.restaurant.management.services;

import java.sql.Date;
import java.util.List;

import com.restaurant.management.dto.BanquetSearchDto;
import com.restaurant.management.entities.Banquet;

public interface BanquetService {

	public void saveBanquet(Banquet banquet);

	public boolean isDateOfEventExists(Date dateOfEvent);

	public List<Banquet> getBanquets();

	public boolean isBanquetIdExists(Long id);

	public Banquet getBanquetById(Long id);

	public String deleteBanquetDetail(Long id);

	public void updateBanquet(Banquet banquet);

	public List<Banquet> multipulSearchBanquet(BanquetSearchDto banquetSearchDto);
}
