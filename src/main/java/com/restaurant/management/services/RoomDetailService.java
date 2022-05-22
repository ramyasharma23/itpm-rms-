package com.restaurant.management.services;

import java.util.List;

import com.restaurant.management.dto.RoomDetailSearchDto;
import com.restaurant.management.entities.RoomDetail;

public interface RoomDetailService {

	public boolean isRoomNoExists(Long roomNo);

	public void saveRoomDetail(RoomDetail roomDetail);

	public void editRoomDetail(RoomDetail roomDetail);

	public String deleteRoomDetailById(Long id);

	public List<RoomDetail> getRoomDetails();

	public RoomDetail getRoomDetailById(Long id);

	public boolean isRoomDetailIdExists(Long id);

	public List<RoomDetail> multipleSearchRoomDetail(RoomDetailSearchDto roomDetailSearchDto);

}
