package com.restaurant.management.services;

import java.sql.Date;
import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.BooleanBuilder;
import com.restaurant.management.dto.BanquetSearchDto;
import com.restaurant.management.entities.Banquet;
import com.restaurant.management.entities.QBanquet;
import com.restaurant.management.repositories.BanquetRepository;
import com.restaurant.management.util.Utils;

@Service
public class BanquetServiceImpl implements BanquetService {

	@Autowired
	private BanquetRepository banquetRepository;

	@Transactional
	public void saveBanquet(Banquet banquet) {
		banquetRepository.save(banquet);
	}

	@Transactional
	public boolean isDateOfEventExists(Date dateOfEvent) {
		return banquetRepository.existsByDateOfEvent(dateOfEvent);
	}

	@Transactional(readOnly = true)
	public List<Banquet> getBanquets() {
		return banquetRepository.findAll();
	}

	@Transactional
	public boolean isBanquetIdExists(Long id) {
		return banquetRepository.existsById(id);
	}

	@Transactional(readOnly = true)
	public Banquet getBanquetById(Long id) {
		return banquetRepository.findById(id).get();
	}

	@Transactional
	public String deleteBanquetDetail(Long id) {
		banquetRepository.deleteById(id);
		return "Banquet Details Removed !! " + id;
	}

	@Transactional
	public void updateBanquet(Banquet banquet) {
		banquetRepository.save(banquet);
	}

	@Transactional(readOnly = true)
	public List<Banquet> multipulSearchBanquet(BanquetSearchDto banquetDto) {
		BooleanBuilder booleanBuilder = new BooleanBuilder();

		if (Utils.isNotNullAndEmpty(banquetDto.getGuestName())) {
			booleanBuilder.and(QBanquet.banquet.guestName.containsIgnoreCase(banquetDto.getGuestName()));
		}
		if (Utils.isNotNullAndEmpty(banquetDto.getEventType())) {
			booleanBuilder.and(QBanquet.banquet.eventType.containsIgnoreCase(banquetDto.getEventType()));
		}
		if (Utils.isNotNullAndEmpty(banquetDto.getDecoration())) {
			booleanBuilder.and(QBanquet.banquet.decoration.containsIgnoreCase(banquetDto.getDecoration()));
		}

		if (Utils.isNotNullAndEmpty(banquetDto.getAdditionalService())) {
			booleanBuilder
					.and(QBanquet.banquet.additionalService.containsIgnoreCase(banquetDto.getAdditionalService()));
		}

		return (List<Banquet>) banquetRepository.findAll(booleanBuilder);
	}

}
