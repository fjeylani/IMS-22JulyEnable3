package com.qa.ims.controller;
import java.util.List;


import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;

import com.qa.ims.persistence.domain.Order_Items;
import com.qa.ims.persistence.dao.OrderItemDAO;
import com.qa.ims.utils.Utils;

/**
 * Takes in order item details for CRUD functionality
 *
 */

public class OrderItemsController implements CrudController<Order_Items>{

	public static final Logger LOGGER = LogManager.getLogger();

	private OrderItemDAO orderItemDAO;
	private Utils utils;

	public OrderItemsController(OrderItemDAO orderItemDAO, Utils utils) {
		super();
		this.orderItemDAO = orderItemDAO;
		this.utils = utils;
	}
	

	/**
	 * Reads all order items to the logger
	 */
	@Override
	
	public List<Order_Items> readAll() {
		List<Order_Items> orderItems = orderItemDAO.readAll();
		for (Order_Items orderItem : orderItems) {
			LOGGER.info(orderItem);
		}
		return orderItems;
	}
	
	
	
	/**
	 * Creates an order ID by taking in user input
	 */
	@Override
	public Order_Items create() {
		LOGGER.info("Please enter id of order");
		Long orderId = utils.getLong();
		LOGGER.info("Please enter id of item");
		Long itemId = utils.getLong();
		LOGGER.info("Please enter quantity of item");
		int quantity = utils.getint();
		Order_Items orderItem = orderItemDAO.create(new Order_Items(orderId, itemId, quantity));
		LOGGER.info("Order ID Generated - Please READ the orderID and create orderitems");
		return orderItem  ;
		
	}
	
	
	/**
	 * Updates an existing order item by taking in user input
	 */
	@Override
	public Order_Items update() {
		LOGGER.info("Please enter the order id ");
		Long orderId = utils.getLong();
		LOGGER.info("Please enter the item id");
		Long itemId = utils.getLong();
		LOGGER.info("Please enter the quantity");
		int quantity = utils.getint();
		Order_Items orderItem = orderItemDAO.update(new Order_Items(orderId, itemId, quantity));
		LOGGER.info("Order Item Updated");
		return orderItem;
	}
	
	
	/**
	 * Deletes an existing order item by the order id of the item
	 * 
	 * @return
	 */
	@Override
	public int delete() {
		LOGGER.info("Please enter the id of the order you would like to delete");
		Long orderItemId = utils.getLong();
		return orderItemDAO.delete(orderItemId);
	}
	
	
}