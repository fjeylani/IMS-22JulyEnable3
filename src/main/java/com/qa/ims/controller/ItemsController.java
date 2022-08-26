package com.qa.ims.controller;

 import java.util.List;


 import org.apache.logging.log4j.LogManager;
 import org.apache.logging.log4j.Logger;

 import com.qa.ims.persistence.dao.ItemDAO;
 import com.qa.ims.persistence.domain.Items;
 import com.qa.ims.utils.Utils;

 /**
  * Takes in item details for CRUD functionality
  *
  */

 public class ItemsController implements CrudController<Items> {


 		public static final Logger LOGGER = LogManager.getLogger();

 		private ItemDAO itemDAO;
 		private Utils utils;

 		public ItemsController(ItemDAO itemDAO, Utils utils) {
 			super();
 			this.itemDAO = itemDAO;
 			this.utils = utils;
 		}


 		/**
 		 * Reads all items to the logger
 		 */
 		@Override
 		public List<Items> readAll() {
 			List<Items> items = itemDAO.readAll();
 			for (Items item : items) {
 				LOGGER.info(item);
 			}
 			return items;
 		}


 		/**
 		 * Creates an item by taking in user input
 		 */
 		@Override
 		public Items create() {
 			LOGGER.info("Please enter item name");
 			String Title = utils.getString();
 			LOGGER.info("Please enter item price");
 			Double Price = utils.getDouble();
 			LOGGER.info("What is the stock quantity?");
 			int stock = utils.getint();
 			Items item = itemDAO.create(new Items( Title, Price, stock));
 			LOGGER.info("Items created");
 			return item;
 		}

 		/**
 		 * Updates an existing item by taking in user input
 		 */
 		@Override
 		public Items update() {
 			LOGGER.info("Please enter the id of the item you would like to update");
 			Long item_ID = utils.getLong();
 			LOGGER.info("Please enter an item name");
 			String Title = utils.getString();
 			LOGGER.info("Please enter an item price");
 			Double Price = utils.getDouble();
 			LOGGER.info("Please enter stock quantity");
 			int Stock = utils.getint();
 			Items item = itemDAO.update(new Items(item_ID, Title, Price, Stock));
 			LOGGER.info("Items Updated");
 			return item;
 		}


 		/**
 		 * Deletes an existing item by the id of the item
 		 * 
 		 * @return
 		 */
 		@Override
 		public int delete() {
 			LOGGER.info("Please enter the id of the item you would like to delete");
 			Long Item_ID = utils.getLong();
 			return itemDAO.delete(Item_ID);
 		}

 }