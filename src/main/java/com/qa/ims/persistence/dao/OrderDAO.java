package com.qa.ims.persistence.dao;

	import java.sql.Connection;
import java.sql.Date;
import java.sql.PreparedStatement;
	import java.sql.ResultSet;
	import java.sql.SQLException;
	import java.sql.Statement;
	import java.util.ArrayList;
	import java.util.List;

	import org.apache.logging.log4j.LogManager;
	import org.apache.logging.log4j.Logger;


	import com.qa.ims.persistence.domain.Orders;
import com.qa.ims.utils.DBUtils;

	public class OrderDAO implements Dao<Orders>  {


			
			public static final Logger LOGGER = LogManager.getLogger();
			
			@Override
			public Orders modelFromResultSet(ResultSet resultSet) throws SQLException {
				Long Order_ID = resultSet.getLong("Order_ID");
				Long fk_id = resultSet.getLong("fk_id");
				Double Total = resultSet.getDouble("Total");
				Date Date_Placed = resultSet.getDate("Date_Placed");
				return new Orders(Order_ID, fk_id, Total, Date_Placed);
			}
		
			
			/**
			 * Reads all orders from the database
			 * 
			 * @return A list of orders
			 */
			@Override
			public List<Orders> readAll() {
				try (Connection connection = DBUtils.getInstance().getConnection();
						Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery("SELECT  * FROM orders");) {
					List<Orders> orders = new ArrayList<>();
					while (resultSet.next()) {
						orders.add(modelFromResultSet(resultSet));
					}
					return orders;
				} catch (SQLException e) {
					LOGGER.debug(e);
					LOGGER.error(e.getMessage());
				}
				return new ArrayList<>();
			}
			
			
			/**
			 * Reads most recent order added to the database
			 * 
			 * @return Most recent order added to the database
			 */
			public Orders readLatest() {
				try (Connection connection = DBUtils.getInstance().getConnection();
						Statement statement = connection.createStatement();
						ResultSet resultSet = statement.executeQuery("SELECT * FROM orders ORDER BY order_id DESC LIMIT 1");) {
					resultSet.next();
					return modelFromResultSet(resultSet);
				} catch (Exception e) {
					LOGGER.debug(e);
					LOGGER.error(e.getMessage());
				}
				return null;
			}
			
			/**
			 * Creates an order in the database
			 * 
			 * @param order - takes in a order object. id will be ignored
			 */
			@Override
			public Orders create(Orders order) {
				try (Connection connection = DBUtils.getInstance().getConnection();
						PreparedStatement statement = connection
								.prepareStatement("INSERT INTO orders(fk_id) VALUES (?)");) { 
					statement.setLong(1, order.getOrder_ID());
					statement.setDouble(2, order.getTotal());
					statement.setDate(1, order.getDate_Placed());
					statement.setLong(2, order.getCustomer_ID());
					statement.executeUpdate();
					return readLatest();
				} catch (Exception e) {
					LOGGER.debug(e);
					LOGGER.error(e.getMessage());
				}
				return null;
			}
			
			
			
			/**
			 * Reads an order from the database
			 * 
			 * @param orderId - takes in an orderId.
			 * @return order details of specified orderId
			 */
			@Override
			public Orders read(Long orderId) {
				try (Connection connection = DBUtils.getInstance().getConnection();
						PreparedStatement statement = connection.prepareStatement("SELECT * FROM orders");) {
					statement.setLong(1, orderId);
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
			 * Updates an order in the database
			 * 
			 * @param order - takes in an order object, the order_id field will be used to
			 *                 update that order in the database
			 * @return  The order_id of the updated item object.
			 */
			@Override
			public Orders update(Orders order) {
				try (Connection connection = DBUtils.getInstance().getConnection();
						PreparedStatement statement = connection
								.prepareStatement("UPDATE orders SET fk_id = ? WHERE order_id = ?");) {
					statement.setLong(1, order.getCustomer_ID());
					statement.setLong(2, order.getOrder_ID());
					statement.executeUpdate();
					return read(order.getOrder_ID());
				} catch (Exception e) {
					LOGGER.debug(e);
					LOGGER.error(e.getMessage());
				}
				return null;
			}
			
			/**
			 * Deletes an order in the database
			 * 
			 * @param orderId - id of the order
			 */
			@Override
			public int delete(long orderId) {
				try (Connection connection = DBUtils.getInstance().getConnection();
						PreparedStatement statement = connection.prepareStatement("DELETE FROM orders WHERE order_id = ?");) {
					statement.setLong(1, orderId);
					return statement.executeUpdate();
				} catch (Exception e) {
					LOGGER.debug(e);
					LOGGER.error(e.getMessage());
				}
				return 0;
			}
	}