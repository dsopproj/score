package org.me.services;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.me.models.Order;
import org.me.utils.Pool;

/**
 * The Class UserOrderService.
 */
public class UserOrderService {

	/**
	 * Change order state.
	 *
	 * @param pool
	 *            the pool
	 * @param orderId
	 *            the order id
	 * @param state
	 *            the state
	 * @param username
	 * @param cash
	 */
	public static final boolean customeOrder(Pool pool, String orderId, String username, BigDecimal cash) {
		Connection connection = pool.acquire();
		PreparedStatement statement = null;
		PreparedStatement upStatement = null;
		try {
			String querySql = "update UserAccount  set frozenScore = frozenScore -?  where username=?";
			upStatement = connection.prepareStatement(querySql);
			upStatement.setDouble(1, cash.doubleValue());
			upStatement.setString(2, username);
			int updatecount = upStatement.executeUpdate();
			if (updatecount == 1) {
				String sql = "update UserOrder set state=? where orderid=?;";
				statement = connection.prepareStatement(sql);
				statement.setQueryTimeout(10);
				statement.setInt(1, 1);//消费是1;  未消费是0
				statement.setString(2, orderId);
				int count = statement.executeUpdate();
				return count == 1;
			} else {
				connection.rollback();
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (upStatement != null)
					upStatement.close();
				if (statement != null)
					statement.close();
			} catch (Exception e) {
			}
			pool.recycle(connection);
		}
	}

	/**
	 * Insert.
	 *
	 * @param pool
	 *            the pool
	 * @param username
	 *            the username
	 * @param cash
	 * @param shopingid
	 *            the shopingid
	 */
	public static final boolean insert(Pool pool, String username, String shoppingid, BigDecimal cash) {
		Connection connection = pool.acquire();
		PreparedStatement upStatement = null;
		PreparedStatement statement = null;
		try {
			String querySql = "update UserAccount  set score = score - ?,frozenScore = frozenScore +?  where username=?";
			upStatement = connection.prepareStatement(querySql);
			upStatement.setDouble(1, cash.doubleValue());
			upStatement.setDouble(2, cash.doubleValue());
			upStatement.setString(3, username);
			int updatecount = upStatement.executeUpdate();
			if (updatecount == 1) {
				String sql = "insert into UserOrder(orderid,username,shoppingid,state,datetime) values (?,?,?,?,?);";
				statement = connection.prepareStatement(sql);
				statement.setQueryTimeout(30);
				statement.setString(1, UUID.randomUUID().toString());
				statement.setString(2, username);
				statement.setString(3, shoppingid);
				statement.setInt(4, 0);
				statement.setString(5, Long.toString(System.currentTimeMillis()));
				statement.executeUpdate();
				return true;
			} else {
				connection.rollback();
				return false;
			}
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			try {
				if (upStatement != null)
					upStatement.close();
				if (statement != null)
					statement.close();
			} catch (Exception e) {
			}
			pool.recycle(connection);
		}
	}

	/**
	 * Find order byid.
	 *
	 * @param pool
	 *            the pool
	 * @param orderId
	 *            the order id
	 * @return the order
	 */
	public static final Order FindOrderByid(Pool pool, String orderId) {
		Connection connection = pool.acquire();
		Order order = null;
		try {
			String sql = "select * from UserOrder where orderid=? ;";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setQueryTimeout(30);
			statement.setString(1, orderId);
			ResultSet rs = statement.executeQuery();
			while (rs.next()) {
				order = new Order();
				order.orderid = rs.getString("orderid");
				order.shopingid = rs.getString("shoppingid");
				order.state = rs.getInt("state");
				order.username = rs.getString("username");
				order.datetime = rs.getString("datetime");
			}
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.recycle(connection);
		}
		return order;
	}

	/**
	 * Find order byid.
	 *
	 * @param pool
	 *            the pool
	 * @param orderId
	 *            the order id
	 * @return the order
	 */
	public static final List<Order> FindOrderByUserName(Pool pool, String userName) {
		Connection connection = pool.acquire();

		List<Order> listOrder = new ArrayList<Order>();
		try {
			String sql = "select * from UserOrder where username=? ;";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setQueryTimeout(30);
			statement.setString(1, userName);
			ResultSet rs = statement.executeQuery();

			while (rs.next()) {
				Order order = new Order();
				order.orderid = rs.getString("orderid");
				order.shopingid = rs.getString("shoppingid");
				order.state = rs.getInt("state");
				order.username = rs.getString("username");
				order.datetime = rs.getString("datetime");
				listOrder.add(order);
			}
			statement.close();

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.recycle(connection);
		}
		return listOrder;
	}
}
