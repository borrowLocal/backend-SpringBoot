package com.example.borolo.dto.response;

import java.time.LocalDateTime;

public class ItemListResponseDto {
    private int item_id;
    private String title;
    private String image_url;
    private LocalDateTime update_time;
	public int getItem_id() {
		return item_id;
	}
	public void setItem_id(int item_id) {
		this.item_id = item_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public LocalDateTime getUpdate_time() {
		return update_time;
	}
	public void setUpdate_time(LocalDateTime update_time) {
		this.update_time = update_time;
	}
}
