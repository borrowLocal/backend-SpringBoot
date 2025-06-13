package com.example.borolo.dto.request;

import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.Size;

public class ReportRequestDto {
    private Integer rental_id;
    private Integer target_user_id;
    
    @NotBlank(message = "신고 사유는 필수입니다.")
    @Size(min = 10, message = "신고 사유는 최소 10자 이상이어야 합니다.")
    private String reason;
    
	public Integer getRental_id() {
		return rental_id;
	}
	public void setRental_id(Integer rental_id) {
		this.rental_id = rental_id;
	}
	public Integer getTarget_user_id() {
		return target_user_id;
	}
	public void setTarget_user_id(Integer target_user_id) {
		this.target_user_id = target_user_id;
	}
	public String getReason() {
		return reason;
	}
	public void setReason(String reason) {
		this.reason = reason;
	}
} 
