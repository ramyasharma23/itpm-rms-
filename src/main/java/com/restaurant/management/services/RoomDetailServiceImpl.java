package com.restaurant.management.services;

import java.util.List;

import org.springframework.beans.factory.annotation.Autowired;
import org.springframework.stereotype.Service;
import org.springframework.transaction.annotation.Transactional;

import com.querydsl.core.BooleanBuilder;
import com.restaurant.management.dto.RoomDetailSearchDto;
import com.restaurant.management.entities.QRoomDetail;
import com.restaurant.management.entities.RoomDetail;
import com.restaurant.management.repositories.RoomDetailRepository;
import com.restaurant.management.util.Utils;

@Service
public class RoomDetailServiceImpl implements RoomDetailService {

	@Autowired
	private RoomDetailRepository roomDetailRepository;

	@Override
	public boolean isRoomNoExists(Long roomNo) {
		return roomDetailRepository.existsByRoomNo(roomNo);
	}

	@Transactional
	public void saveRoomDetail(RoomDetail roomDetail) {
		roomDetailRepository.save(roomDetail);

	}

	@Override
	public void editRoomDetail(RoomDetail roomDetail) {
		roomDetailRepository.save(roomDetail);

	}

	@Override
	public String deleteRoomDetailById(Long id) {
		roomDetailRepository.deleteById(id);
		return "RoomDetail Removed !! " + id;
	}

	@Transactional(readOnly = true)
	public List<RoomDetail> getRoomDetails() {
		return roomDetailRepository.findAll();
	}

	@Transactional(readOnly = true)
	public RoomDetail getRoomDetailById(Long id) {
		return roomDetailRepository.findById(id).get();
	}

	@Override
	public boolean isRoomDetailIdExists(Long id) {
		return roomDetailRepository.existsById(id);
	}

	@Transactional
	public List<RoomDetail> multipleSearchRoomDetail(RoomDetailSearchDto roomDetailSearchDto) {
		BooleanBuilder booleanBuilder = new BooleanBuilder();

		if (Utils.isNotNullAndEmpty(roomDetailSearchDto.getCustomerNo())) {
			booleanBuilder
					.and(QRoomDetail.roomDetail.customerNo.containsIgnoreCase(roomDetailSearchDto.getCustomerNo()));
		}
		if (Utils.isNotNullAndEmpty(roomDetailSearchDto.getFacilities())) {
			booleanBuilder
					.and(QRoomDetail.roomDetail.facilities.containsIgnoreCase(roomDetailSearchDto.getFacilities()));
		}
		if (Utils.isNotNullAndEmpty(roomDetailSearchDto.getRoomType())) {
			booleanBuilder.and(QRoomDetail.roomDetail.roomType.containsIgnoreCase(roomDetailSearchDto.getRoomType()));
		}
		if (Utils.isNotNullAndEmptyId(roomDetailSearchDto.getRoomNo())) {
			booleanBuilder.and(QRoomDetail.roomDetail.roomNo.eq(roomDetailSearchDto.getRoomNo()));
		}
		return (List<RoomDetail>) roomDetailRepository.findAll(booleanBuilder);
	}
}
