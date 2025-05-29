package com.example.borolo.dto.response;

import java.math.BigDecimal;
import java.time.LocalDate;
import java.time.LocalDateTime;

public class RentalPaymentResponseDto {
    private String renterNickName;
    private String itemTitle;
    private int pricePerDay;
    private int depositAmount;
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
	public int getTotalAmount() {
		return totalAmount;
	}
	public void setTotalAmount(int totalAmount) {
		this.totalAmount = totalAmount;
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
	public String getDistrict() {
		return district;
	}
	public void setDistrict(String district) {
		this.district = district;
	}
	public BigDecimal getLat() {
		return lat;
	}
	public void setLat(BigDecimal lat) {
		this.lat = lat;
	}
	public BigDecimal getLng() {
		return lng;
	}
	public void setLng(BigDecimal lng) {
		this.lng = lng;
	}
	private int totalAmount;	
    private LocalDate startDate;
    private LocalDate endDate;
    private LocalDateTime meetingTime;

    private String meetingLocation;   // 기존 텍스트 위치
    private String district;          // 사용자 위치 구 단위
    private BigDecimal lat;           // 위도
    private BigDecimal lng;           // 경도
}
