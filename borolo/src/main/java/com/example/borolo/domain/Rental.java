package com.example.borolo.domain;


import java.time.LocalDate;
import java.time.LocalDateTime;

import jakarta.validation.constraints.NotNull;

public class Rental {
    private Integer rental_id;
    private LocalDate start_date;
    private LocalDate end_date;
    private LocalDateTime meeting_time;
    private LocalDate expected_return_at;
    private String meeting_location;
    private String rental_status;
    private Boolean is_approved;
    private Boolean is_completed;
	private Integer rental_quantity;
    private Integer item_id;
    private Integer user_id;

    public LocalDate getExpected_return_at() {
		return expected_return_at;
	}
	public void setExpected_return_at(LocalDate expected_return_at) {
		this.expected_return_at = expected_return_at;
	}
	public Integer getRental_quantity() { return rental_quantity; }
	public void setRental_quantity(Integer rental_quantity) { this.rental_quantity = rental_quantity; }
	
    public Integer getRental_id() { return rental_id; }
    public void setRental_id(Integer rental_id) { this.rental_id = rental_id; }

    public LocalDate getStart_date() { return start_date; }
    public void setStart_date(LocalDate start_date) { this.start_date = start_date; }

    public LocalDate getEnd_date() { return end_date; }
    public void setEnd_date(LocalDate end_date) { this.end_date = end_date; }

    public LocalDateTime getMeeting_time() { return meeting_time; }
    public void setMeeting_time(LocalDateTime meeting_time) { this.meeting_time = meeting_time; }

    public String getMeeting_location() { return meeting_location; }
    public void setMeeting_location(String meeting_location) { this.meeting_location = meeting_location; }

    public String getRental_status() { return rental_status; }
    public void setRental_status(String rental_status) { this.rental_status = rental_status; }

    public Boolean getIs_approved() { return is_approved; }
    public void setIs_approved(Boolean is_approved) { this.is_approved = is_approved; }

    public Boolean getIs_completed() { return is_completed; }
    public void setIs_completed(Boolean is_completed) { this.is_completed = is_completed; }

    public Integer getItem_id() { return item_id; }
    public void setItem_id(Integer item_id) { this.item_id = item_id; }

    public Integer getUser_id() { return user_id; }
    public void setUser_id(Integer user_id) { this.user_id = user_id; }
}
