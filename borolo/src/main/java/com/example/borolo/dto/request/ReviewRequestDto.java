package com.example.borolo.dto.request;

import jakarta.validation.constraints.Max;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class ReviewRequestDto {
    @NotNull(message = "렌탈 ID는 필수입니다.")
    private Integer rental_id;

    @NotNull(message = "평가 대상 유저 ID는 필수입니다.")
    private Integer user_target_id;

    @NotBlank(message = "리뷰 내용은 필수입니다.")
    @Size(min = 10, message = "리뷰 내용은 최소 10자 이상이어야 합니다.")
    private String content;

    @NotNull(message = "평점은 필수입니다.")
    @Min(value = 0, message = "평점은 최소 0점이어야 합니다.")
    @Max(value = 9, message = "평점은 최대 9점이어야 합니다.")
    private Integer rating;
    
	public Integer getRental_id() {
		return rental_id;
	}
	public void setRental_id(Integer rental_id) {
		this.rental_id = rental_id;
	}
	public Integer getUser_target_id() {
		return user_target_id;
	}
	public void setUser_target_id(Integer user_target_id) {
		this.user_target_id = user_target_id;
	}
	public String getContent() {
		return content;
	}
	public void setContent(String content) {
		this.content = content;
	}
	public Integer getRating() {
		return rating;
	}
	public void setRating(Integer rating) {
		this.rating = rating;
	}
} 