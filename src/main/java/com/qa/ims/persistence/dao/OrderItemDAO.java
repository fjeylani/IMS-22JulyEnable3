package com.qa.ims.persistence.dao;

import java.sql.Connection;

import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;

import org.apache.logging.log4j.LogManager;
import org.apache.logging.log4j.Logger;



import com.qa.ims.persistence.domain.Order_Items;
import com.qa.ims.utils.DBUtils;

public class OrderItemDAO implements Dao<Order_Items> {
	
	
public static final Logger LOGGER = LogManager.getLogger();
	
	@Override
	public Order_Items modelFromResultSet(ResultSet resultSet) throws SQLException {
		
		Long Order_Item_ID = resultSet.getLong("order_item_id");
		Long fk_Item_Id = resultSet.getLong("fk_item_id");
		int Quantity = resultSet.getInt("Quantity");
		return new Order_Items(Order_Item_ID, fk_Item_Id, Quantity);
		
	}
	
	
	/**
	 * Reads all order items from the database
	 * 
	 * @return A list of order items
	 */
	@Override
	public List<Order_Items> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM order_items");) {
			List<Order_Items> orderItem = new ArrayList<>();
			while (resultSet.next()) {
				orderItem.add(modelFromResultSet(resultSet));
			}
			return orderItem;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}
	
	/**
	 * Reads most recent order item added to the database
	 * 
	 * @return Most recent order item added to the database
	 */
	public Order_Items readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM order_items ORDER BY order_item_id DESC LIMIT 1");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	
	/**
	 * Creates an order item in the database
	 * 
	 * @param item - takes in a order items object. id will be ignored
	 */
	@Override
	public Order_Items create(Order_Items orderItem) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO order_items(fk_item_id, Quantity) VALUES (?, ?)");){
			statement.setLong(2, orderItem.getItem_ID());
			statement.setInt(1, orderItem.getQuantity());
			statement.executeUpdate();
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	
	/**
	 * Reads an order items from the database
	 * 
	 * @param orderItemId - takes in an itemId.
	 * @return item details of specified itemId
	 */
	@Override
	public Order_Items read(Long order_Item_Id) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM order_items WHERE order_items_id = ?");) {
			statement.setLong(1, order_Item_Id);
			try (ResultSet resultSet = statement.executeQuery();) {
				resultSet.next();
				return modelFromResultSet(resultSet);
			}
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	
	/**
	 * Updates an order item in the database
	 * @param orderItem - takes in an order item object, the item_id field will be used to
	 *                 update that item in the database
	 * @return  The item_id of the updated item object.
	 */
	@Override
	public Order_Items update(Order_Items orderItem) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("UPDATE order_items SET fk_item_id = ?, Quantity =? WHERE order_items_id = ?");) {
			statement.setLong(2, orderItem.getItem_ID());
			statement.setLong(3, orderItem.getQuantity());
			statement.setLong(4, orderItem.getOrder_Item_ID());
			statement.executeUpdate();
			return read(orderItem.getOrder_Item_ID());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	/**
	 * Deletes an order item in the database
	 * 
	 * @param orderItemId - id of the order item
	 */
	@Override
	public int delete(long orderItemId) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM order_items WHERE order_items_id = ?");) {
			statement.setLong(1, orderItemId);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}

}
