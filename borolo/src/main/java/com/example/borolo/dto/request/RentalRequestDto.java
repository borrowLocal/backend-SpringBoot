package com.example.borolo.dto.request;


import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.validation.constraints.FutureOrPresent;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;

public class RentalRequestDto {

	
    @NotNull(message = "상품 ID는 필수입니다.")
    private Integer item_id;

    @NotNull(message = "시작일은 필수입니다.")
    @FutureOrPresent(message = "시작일은 오늘 또는 미래 날짜여야 합니다.")
    private LocalDate start_date;

    @NotNull(message = "종료일은 필수입니다.")
    private LocalDate end_date;

    @NotBlank(message = "희망 장소는 필수입니다.")
    private String meeting_location;

    @NotNull(message = "희망 시간은 필수입니다.")
    private LocalDateTime meeting_time;

    @NotNull(message = "유저 ID는 필수입니다.")
    private Integer user_id;
    
    @NotNull(message = "1개 이상 대여 할 수 있습니다.")
    private Integer rental_quantity;
    
    @NotNull(message = "반납 일시는 필수 입니다.")
    private LocalDate expected_return_at;
    

	public LocalDate getExpected_return_at() {
		return expected_return_at;
	}
	public void setExpected_return_at(LocalDate expected_return_at) {
		this.expected_return_at = expected_return_at;
	}
	public Integer getRental_quantity() {
		return rental_quantity;
	}
	public void setRental_quantity(Integer rental_quantity) {
		this.rental_quantity = rental_quantity;
	}
	public Integer getItem_id() {
		return item_id;
	}
	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}
	public LocalDate getStart_date() {
		return start_date;
	}
	public void setStart_date(LocalDate start_date) {
		this.start_date = start_date;
	}
	public LocalDate getEnd_date() {
		return end_date;
	}
	public void setEnd_date(LocalDate end_date) {
		this.end_date = end_date;
	}
	public String getMeeting_location() {
		return meeting_location;
	}
	public void setMeeting_location(String meeting_location) {
		this.meeting_location = meeting_location;
	}
	public LocalDateTime getMeeting_time() {
		return meeting_time;
	}
	public void setMeeting_time(LocalDateTime meeting_time) {
		this.meeting_time = meeting_time;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}
} 