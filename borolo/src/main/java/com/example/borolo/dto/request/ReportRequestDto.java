package com.example.borolo.dto.request;

import java.time.LocalDateTime;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ReportRequestDto {
	
    private int reporter_id;
    private int target_user_id;
    private int rental_id;
    private LocalDateTime created_at;
    
    @NotBlank(message = "신고 사유는 필수입니다.")
    @Size(min = 10, message = "신고 사유는 최소 10자 이상이어야 합니다.")
    private String reason;

	public int getReporter_id() {
		return reporter_id;
	}

	public void setReporter_id(int reporter_id) {
		this.reporter_id = reporter_id;
	}

	public int getTarget_user_id() {
		return target_user_id;
	}

	public void setTarget_user_id(int target_user_id) {
		this.target_user_id = target_user_id;
	}

	public int getRental_id() {
		return rental_id;
	}

	public void setRental_id(int rental_id) {
		this.rental_id = rental_id;
	}

	public LocalDateTime getCreated_at() {
		return created_at;
	}

	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}

	public String getReason() {
		return reason;
	}

	public void setReason(String reason) {
		this.reason = reason;
	}
    
    
} 
