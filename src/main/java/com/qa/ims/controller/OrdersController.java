package com.qa.ims.controller;

 import java.util.List;


 import org.apache.logging.log4j.LogManager;
 import org.apache.logging.log4j.Logger;

 import com.qa.ims.persistence.dao.OrderDAO;
 import com.qa.ims.persistence.domain.Orders;

 import com.qa.ims.utils.Utils;

 /**
  * Takes in order details for CRUD functionality
  *
  */

 public class OrdersController implements CrudController<Orders> {


 		public static final Logger LOGGER = LogManager.getLogger();

 		private OrderDAO orderDAO;
 		private Utils utils;

 		public OrdersController(OrderDAO orderDAO, Utils utils) {
 			super();
 			this.orderDAO = orderDAO;
 			this.utils = utils;
 		}



 		/**
 		 * Reads all orders to the logger
 		 */
 		@Override

 		public List<Orders> readAll() {
 			List<Orders> orders = orderDAO.readAll();
 			for (Orders order : orders) {
 				LOGGER.info(order);
 			}
 			return orders;
 		}


 		/**
 		 * Creates an order ID by taking in user input
 		 */
 		@Override
 		public Orders create() {
 			LOGGER.info("Please enter id of customer");
 			Long id = utils.getLong();
 			Orders order = orderDAO.create(new Orders(id));
 			LOGGER.info("Order ID Generated - Please READ the orderID and create OrderItems");
 			return order  ;
 		}


 		/**
 		 * Updates an existing order by taking in user input
 		 */
 		@Override
 		public Orders update() {
 			LOGGER.info("Please enter the order id ");
 			Long orderId = utils.getLong();
 			LOGGER.info("Please enter a customer id");
 			Long id = utils.getLong();
 			Orders order = orderDAO.update(new Orders(orderId, id));
 			LOGGER.info("Customer Updated");
 			return order;
 		}

 		/**
 		 * Deletes an existing order by the order id of the item
 		 * 
 		 * @return
 		 */
 		@Override
 		public int delete() {
 			LOGGER.info("Please enter the id of the order you would like to delete");
 			Long Order_ID = utils.getLong();
 			return orderDAO.delete(Order_ID);
 		}
 		
 }