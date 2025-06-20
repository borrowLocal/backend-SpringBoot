package com.example.borolo.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class RentalPaymentResponseDto {
	private String renterNickName;
	private String itemTitle;
    private String itemImageUrl;
	private int pricePerDay;
	private int depositAmount;
	private int rentalQuantity;
	private LocalDate startDate;
	private LocalDate endDate;
	private LocalDate exceptedReturnAt;
	private LocalDateTime meetingTime;
	private String meetingLocation;
	private int totalAmount; // 계산된 값
	
	

	public String getItemImageUrl() {
		return itemImageUrl;
	}
	public void setItemImageUrl(String itemImageUrl) {
		this.itemImageUrl = itemImageUrl;
	}
	public String getRenterNickName() {
		return renterNickName;
	}
	public void setRenterNickName(String renterNickName) {
		this.renterNickName = renterNickName;
	}
	public String getItemTitle() {
		return itemTitle;
	}
	public void setItemTitle(String itemTitle) {
		this.itemTitle = itemTitle;
	}
	public int getPricePerDay() {
		return pricePerDay;
	}
	public void setPricePerDay(int pricePerDay) {
		this.pricePerDay = pricePerDay;
	}
	public int getDepositAmount() {
		return depositAmount;
	}
	public void setDepositAmount(int depositAmount) {
		this.depositAmount = depositAmount;
	}
	public int getRentalQuantity() {
		return rentalQuantity;
	}
	public void setRentalQuantity(int rentalQuantity) {
		this.rentalQuantity = rentalQuantity;
	}
	public LocalDate getStartDate() {
		return startDate;
	}
	public void setStartDate(LocalDate startDate) {
		this.startDate = startDate;
	}
	public LocalDate getEndDate() {
		return endDate;
	}
	public void setEndDate(LocalDate endDate) {
		this.endDate = endDate;
	}
	public LocalDate getExceptedReturnAt() {
		return exceptedReturnAt;
	}
	public void setExceptedReturnAt(LocalDate exceptedReturnAt) {
		this.exceptedReturnAt = exceptedReturnAt;
	}
	public LocalDateTime getMeetingTime() {
		return meetingTime;
	}
	public void setMeetingTime(LocalDateTime meetingTime) {
		this.meetingTime = meetingTime;
	}
	public String getMeetingLocation() {
		return meetingLocation;
	}
	public void setMeetingLocation(String meetingLocation) {
		this.meetingLocation = meetingLocation;
	}
	public int getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
	}

}
