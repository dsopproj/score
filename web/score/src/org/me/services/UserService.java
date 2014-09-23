package org.me.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;

import org.me.models.User;
import org.me.utils.Pool;

public class UserService {

	public static final void insert(Pool pool, User user) {
		Connection connection = pool.acquire();
		try {
			String sql = "insert into User(username,usernick,password,role) values (?,?,?,?);";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setQueryTimeout(30);
			statement.setString(1, user.username);
			statement.setString(2, user.usernick);
			statement.setString(3, user.password);
			statement.setString(4, user.role);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.recycle(connection);
		}
	}

	public static final ArrayList<User> search(Pool pool, String username,
			String userpwd) {
		ArrayList<User> list = new ArrayList<User>();
		Connection connection = pool.acquire();
		try {
			String sql = "select username,usernick from User where username='"
					+ username + "' and password='" + userpwd + "';";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				User user = new User();
				user.username = rs.getString("username");
				user.usernick = rs.getString("usernick");
				list.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.recycle(connection);
		}
		return list;
	}

	public static final ArrayList<User> search(Pool pool, String username) {
		ArrayList<User> list = new ArrayList<User>();
		Connection connection = pool.acquire();
		try {
			String sql = "select username,usernick from User where username='"
					+ username + "';";

			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				User user = new User();
				// user.recno = rs.getString("RecNo");
				user.username = rs.getString("username");
				user.usernick = rs.getString("usernick");
				list.add(user);
			}

		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.recycle(connection);
		}
		return list;
	}
}
