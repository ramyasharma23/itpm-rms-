package com.restaurant.management.dto;

import java.sql.Date;

public class BanquetSearchDto {

	private String guestName;
	private String eventType;
	private String additionalService;
	private String decoration;
	private Date dateOfEvent;
	public String getGuestName() {
		return guestName;
	}
	public void setGuestName(String guestName) {
		this.guestName = guestName;
	}
	public String getEventType() {
		return eventType;
	}
	public void setEventType(String eventType) {
		this.eventType = eventType;
	}
	public String getAdditionalService() {
		return additionalService;
	}
	public void setAdditionalService(String additionalService) {
		this.additionalService = additionalService;
	}
	public String getDecoration() {
		return decoration;
	}
	public void setDecoration(String decoration) {
		this.decoration = decoration;
	}
	public Date getDateOfEvent() {
		return dateOfEvent;
	}
	public void setDateOfEvent(Date dateOfEvent) {
		this.dateOfEvent = dateOfEvent;
	}
	
}
