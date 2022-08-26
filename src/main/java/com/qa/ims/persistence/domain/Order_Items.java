package com.qa.ims.persistence.domain;
import java.util.Objects;

public class Order_Items {
	
	private Long Order_Item_ID;
	private int Quantity;
	private  Long fk_Item_ID;
	

	public Order_Items(Long order_Item_ID,  Long item_ID, int quantity) {
		Order_Item_ID = order_Item_ID;
		fk_Item_ID = item_ID;
		Quantity = quantity;
		
	}
	public Long getOrder_Item_ID() {
		return Order_Item_ID;
	}
	public void setOrder_Item_ID(Long order_Item_ID) {
		Order_Item_ID = order_Item_ID;
	}
	public Integer getQuantity() {
		return Quantity;
	}
	public void setQuantity(Integer quantity) {
		Quantity = quantity;
	}
	public Long getItem_ID() {
		return fk_Item_ID;
	}
	public void setItem_ID(Long item_ID) {
		fk_Item_ID = item_ID;
	}
	@Override
	public int hashCode() {
		return Objects.hash(fk_Item_ID, Order_Item_ID, Quantity);
	}
	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Order_Items other = (Order_Items) obj;
		return Objects.equals(fk_Item_ID, other.fk_Item_ID) && Objects.equals(Order_Item_ID, other.Order_Item_ID)
				&& Objects.equals(Quantity, other.Quantity);
	}
	@Override
	public String toString() {
		return "Order_Items [Order_Item_ID=" + Order_Item_ID + ", Quantity=" + Quantity + ", Item_ID=" + fk_Item_ID + "]";
	}


}
