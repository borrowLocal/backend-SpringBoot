package com.example.borolo.dto.request;

import jakarta.validation.constraints.AssertTrue;
import jakarta.validation.constraints.Min;
import jakarta.validation.constraints.NotBlank;
import jakarta.validation.constraints.NotNull;
import jakarta.validation.constraints.Size;

public class RegisterItemRequestDto {

    @NotBlank(message = "제목은 필수 입력입니다.")
    @Size(max = 50, message = "제목은 50자 이하만 가능합니다.")
    private String title;

    @NotBlank(message = "설명은 필수 입력입니다.")
    @Size(max = 1000, message = "설명은 1000자 이하만 가능합니다.")
    private String description;

    @NotNull(message = "수량은 필수 입력입니다.")
    @Min(value = 1, message = "수량은 1 이상이어야 합니다.")
    private Integer quantity;

    @NotNull(message = "하루 대여료는 필수 입력입니다.")
    @Min(value = 0, message = "하루 대여료는 0 이상이어야 합니다.")
    private Integer price_per_day;

    @NotNull(message = "보증금은 필수 입력입니다.")
    @Min(value = 0, message = "보증금은 0 이상이어야 합니다.")
    private Integer deposit_amount;

    @NotNull(message = "카테고리는 필수 선택입니다.")
    private Integer category_id;

    private String location;
    private Integer user_id;
    
    @AssertTrue(message = "보증금은 하루 대여료의 10% 이상이어야 합니다.")
    public boolean isDepositValid() {
        if (price_per_day == null || deposit_amount == null) return true;
        return deposit_amount >= (int) Math.ceil(price_per_day * 0.1);
    }

    
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public Integer getQuantity() {
		return quantity;
	}
	public void setQuantity(Integer quantity) {
		this.quantity = quantity;
	}
	public Integer getPrice_per_day() {
		return price_per_day;
	}
	public void setPrice_per_day(Integer price_per_day) {
		this.price_per_day = price_per_day;
	}
	public Integer getDeposit_amount() {
		return deposit_amount;
	}
	public void setDeposit_amount(Integer deposit_amount) {
		this.deposit_amount = deposit_amount;
	}
	public Integer getCategory_id() {
		return category_id;
	}
	public void setCategory_id(Integer category_id) {
		this.category_id = category_id;
	}
	public String getLocation() {
		return location;
	}
	public void setLocation(String location) {
		this.location = location;
	}
	public Integer getUser_id() {
		return user_id;
	}
	public void setUser_id(Integer user_id) {
		this.user_id = user_id;
	}   
    
}