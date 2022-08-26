package com.qa.ims.persistence.domain;

import java.util.Objects;

public class Items {
		private Long Item_ID;
	private String Title;
	private Double Price;
	private int Stock; 
	

	
	public Items(Long item_ID, String title, Double price, int stock) {
	
		Item_ID = item_ID;
		Title = title;
		Price = price;
		Stock = stock;
	}
		
		
	public Items(String Title, Double Price, int Stock) {
		
		Title = getTitle();
		Price = getPrice();
		Stock = getStock();
		
		// TODO Auto-generated constructor stub
	}


	public Long getItem_ID() {
		return Item_ID;
	}
	public void setItem_ID(Long item_ID) {
		Item_ID = item_ID;
	}
	public String getTitle() {
		return Title;
	}
	public void setTitle(String title) {
		Title = title;
	}
	public Double getPrice() {
		return Price;
	}
	public void setPrice(Double price) {
		Price = price;
	}
	public Integer getStock() {
		return Stock;
	}
	public void setStock(Integer stock) {
		Stock = stock;
	}
	@Override
	public int hashCode() {
		return Objects.hash(Item_ID, Price, Stock, Title);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Items other = (Items) obj;
		return Objects.equals(Item_ID, other.Item_ID) && Objects.equals(Price, other.Price)
				&& Objects.equals(Stock, other.Stock) && Objects.equals(Title, other.Title);
	}
	@Override
	public String toString() {
		return "Items [Item_ID=" + Item_ID + ", Title=" + Title + ", Price=" + Price + ", Stock=" + Stock + "]";
	}
}


		


	
	


