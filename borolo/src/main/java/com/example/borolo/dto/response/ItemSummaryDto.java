package com.example.borolo.dto.response;


public class ItemSummaryDto {
    private Integer item_id;
    private String title;
    private String description;
	private Integer quantity;
    private Integer deposit_amount;
    private Integer price_per_day;
    private String image_url;
    private String item_status;
    private int rented_quantity;
    
    public Integer getDeposit_amount() {
		return deposit_amount;
	}
	public void setDeposit_amount(Integer deposit_amount) {
		this.deposit_amount = deposit_amount;
	}
	public Integer getItem_id() {
		return item_id;
	}
	public void setItem_id(Integer item_id) {
		this.item_id = item_id;
	}
	public String getTitle() {
		return title;
	}
	public void setTitle(String title) {
		this.title = title;
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
	public String getImage_url() {
		return image_url;
	}
	public void setImage_url(String image_url) {
		this.image_url = image_url;
	}
	public String getDescription() {
		return description;
	}
	public void setDescription(String description) {
		this.description = description;
	}
	public int getRented_quantity() {
		return rented_quantity;
	}
	public void setRented_quantity(int rented_quantity) {
		this.rented_quantity = rented_quantity;
	}
	public String getItem_status() {
		return item_status;
	}
	public void setItem_status(String item_status) {
		this.item_status = item_status;
	}
}
