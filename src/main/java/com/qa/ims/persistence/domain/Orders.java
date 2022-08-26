package com.qa.ims.persistence.domain;

import java.sql.Date;
import java.util.Objects;



public class Orders {

	private Long Order_ID;
	private Date Date_Placed;
	private Long fk_id;
	private Double Total;




	public Orders(Long order_ID,Long fk_id ,Double total,Date date_Placed) {
		super();
		Order_ID = order_ID;
		Date_Placed = date_Placed;
		this.fk_id = fk_id;
		Total = total;
	}
	




	public Orders(Long Order_ID) {
		
		Order_ID = this.Order_ID;
		// TODO Auto-generated constructor stub
	}





	public Orders(Long orderId, Long fk_id) {
		
		fk_id = this.fk_id;
	
		// TODO Auto-generated constructor stub
	}





	public Long getOrder_ID() {
		return Order_ID;
	}




	public void setOrder_ID(Long order_ID) {
		this.Order_ID = order_ID;
	}




	public Date getDate_Placed() {
		return Date_Placed;
	}




	public void setDate_Placed(Date date_Placed) {
		this.Date_Placed = date_Placed;
	}




	public Long getCustomer_ID() {
		return fk_id;
	}




	public void setCustomer_ID(Long customer_ID) {
		this.fk_id = customer_ID;
	}




	public Double getTotal() {
		return Total;
	}




	public void setTotal(Double total) {
		this.Total = total;
	}




	@Override
	public int hashCode() {
		return Objects.hash(fk_id, Date_Placed, Order_ID, Total);
	}

	@Override
	public boolean equals(Object obj) {
		if (this == obj)
			return true;
		if (obj == null)
			return false;
		if (getClass() != obj.getClass())
			return false;
		Orders other = (Orders) obj;
		return Objects.equals(fk_id, other.fk_id) && Objects.equals(Date_Placed, other.Date_Placed)
				&& Objects.equals(Order_ID, other.Order_ID) && Objects.equals(Total, other.Total);
	}

	@Override
	public String toString() {
		return "Items [Order_ID=" + Order_ID + ", Date_Placed=" + Date_Placed + ", Customer_ID=" + fk_id
				+ ", Total=" + Total + "]";
	}

}