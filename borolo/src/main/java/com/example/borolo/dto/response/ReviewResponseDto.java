package com.example.borolo.dto.response;


import java.time.LocalDateTime;

public class ReviewResponseDto {
    private Integer review_id;
    private String content;
    private Integer rating;
    private LocalDateTime created_at;
    private String item_title;  // 물품명
    private String days_ago;    // 경과일 -> "3일 전"
    
	public Integer getReview_id() {
		return review_id;
	}
	public void setReview_id(Integer review_id) {
		this.review_id = review_id;
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
	public LocalDateTime getCreated_at() {
		return created_at;
	}
	public void setCreated_at(LocalDateTime created_at) {
		this.created_at = created_at;
	}
	public String getItem_title() {
		return item_title;
	}
	public void setItem_title(String item_title) {
		this.item_title = item_title;
	}
	public String getDays_ago() {
		return days_ago;
	}
	public void setDays_ago(String days_ago) {
		this.days_ago = days_ago;
	}
    
}
