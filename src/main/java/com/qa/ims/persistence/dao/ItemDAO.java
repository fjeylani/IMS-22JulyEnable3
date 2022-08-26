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

import com.qa.ims.persistence.domain.Items;
import com.qa.ims.utils.DBUtils;

public class ItemDAO implements Dao<Items> {
	
	public static final Logger LOGGER = LogManager.getLogger();

	
	@Override
	public Items modelFromResultSet(ResultSet resultSet) throws SQLException {
		Long Item_ID = resultSet.getLong("Item_ID");
		String Title = resultSet.getString("Title");
		Double Price = resultSet.getDouble("Price");
		int Stock = resultSet.getInt("Stock");
		return new Items(Item_ID, Title, Price, Stock);
	}
	
	
	/**
	 * Reads all items from the database
	 * 
	 * @return A list of items
	 */
	@Override
	public List<Items> readAll() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM items");) {
			List<Items> items = new ArrayList<>();
			while (resultSet.next()) {
				items.add(modelFromResultSet(resultSet));
			}
			return items;
		} catch (SQLException e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return new ArrayList<>();
	}
	
	
	/**
	 * Reads most recent item added to the database
	 * 
	 * @return Most recent item added to the database
	 */
	public Items readLatest() {
		try (Connection connection = DBUtils.getInstance().getConnection();
				Statement statement = connection.createStatement();
				ResultSet resultSet = statement.executeQuery("SELECT * FROM items");) {
			resultSet.next();
			return modelFromResultSet(resultSet);
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	
	/**
	 * Creates an item in the database
	 * 
	 * @param item - takes in a item object. id will be ignored
	 */
	@Override
	public Items create(Items Item) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("INSERT INTO items(Title, Price, Stock) VALUES (?, ?, ?)");) {
			statement.setString(1, Item.getTitle());
			statement.setDouble(2, Item.getPrice());
			statement.setInt(3, Item.getStock());
			statement.executeUpdate();
			return readLatest();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	
	/**
	 * Reads an item from the database
	 * 
	 * @param itemId - takes in an itemId.
	 * @return item details of specified itemId
	 */
	@Override
	public Items read(Long itemId) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("SELECT * FROM items WHERE item_id = ?");) {
			statement.setLong(1, itemId);
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
	 * Updates an item in the database
	 * 
	 * @param item - takes in an item object, the Item_ID field will be used to
	 *                 update that item in the database
	 * @return  The ITEM_ID of the updated item object.
	 */
	@Override
	public Items update(Items item) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection
						.prepareStatement("UPDATE items SET Title = ?, Price = ? WHERE item_id = ?");) {
			statement.setString(1, item.getTitle());
			statement.setDouble(2, item.getPrice());
			statement.setLong(3, item.getItem_ID());
			statement.executeUpdate();
			return read(item.getItem_ID());
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return null;
	}
	
	
	/**
	 * Deletes an item in the database
	 * 
	 * @param Item_ID - ID of the item
	 */
	@Override
	public int delete(long Item_ID) {
		try (Connection connection = DBUtils.getInstance().getConnection();
				PreparedStatement statement = connection.prepareStatement("DELETE FROM items WHERE Item_ID = ?");) {
			statement.setLong(1, Item_ID);
			return statement.executeUpdate();
		} catch (Exception e) {
			LOGGER.debug(e);
			LOGGER.error(e.getMessage());
		}
		return 0;
	}


	
	
}
