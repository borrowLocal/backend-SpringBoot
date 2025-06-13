package com.example.borolo.dto.response;


import java.time.LocalDate;
import java.time.LocalDateTime;

public class RentalApplicationListResponseDto {
    private String nick_name;
    private Float rating;
    private String title;
    private Integer deposit_amount;
    private Integer price_per_day;
    private String rental_status;
    private LocalDate start_date;
    private LocalDate end_date;
    private LocalDate expected_return_at;
    private Integer rental_quantity;
    private LocalDateTime meeting_time;
    private String meeting_location;
    private Integer rental_id;
    
	public Integer getRental_id() {
		return rental_id;
	}
	public void setRental_id(Integer rental_id) {
		this.rental_id = rental_id;
	}
	public String getNick_name() {
		return nick_name;
	}
	public void setNick_name(String nick_name) {
		this.nick_name = nick_name;
	}
	public Float getRating() {
		return rating;
	}
	public void setRating(Float rating) {
		this.rating = rating;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public Integer getDeposit_amount() {
		return deposit_amount;
	}
	public void setDeposit_amount(Integer deposit_amount) {
		this.deposit_amount = deposit_amount;
	}
	public LocalDate getExpected_return_at() {
		return expected_return_at;
	}
	public void setExpected_return_at(LocalDate expected_return_at) {
		this.expected_return_at = expected_return_at;
	}
	public Integer getPrice_per_day() {
		return price_per_day;
	}
	public void setPrice_per_day(Integer price_per_day) {
		this.price_per_day = price_per_day;
	}
	public String getRental_status() {
		return rental_status;
	}
	public void setRental_status(String rental_status) {
		this.rental_status = rental_status;
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
	public Integer getRental_quantity() {
		return rental_quantity;
	}
	public void setRental_quantity(Integer rental_quantity) {
		this.rental_quantity = rental_quantity;
	}
	public LocalDateTime getMeeting_time() {
		return meeting_time;
	}
	public void setMeeting_time(LocalDateTime meeting_time) {
		this.meeting_time = meeting_time;
	}
	public String getMeeting_location() {
		return meeting_location;
	}
	public void setMeeting_location(String meeting_location) {
		this.meeting_location = meeting_location;
	}
    
    
    
}
