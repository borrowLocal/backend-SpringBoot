package com.example.borolo.dto.response;


public class UserProfileResponseDto {
    private String nick_name;
    private Float rating;

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
}
