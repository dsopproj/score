package org.me.test;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.me.mo.SystemInitialize;
import org.me.models.UserAccount;
import org.me.services.AccountService;
import org.me.utils.Pool;

public class PoolTest {
	public static Pool pool;
	public static String username = "wolf";

	static {
		pool = new Pool("test.db");
		SystemInitialize.pool = pool;
	}

	public static void main(String[] args) {
		try {
			// createTables();
			// initTestData();

			testupdates();
			// testUpdateTestAA();
		} catch (Exception e) {

		}
	}

	private static void testUpdateTestAA() throws SQLException {
		Connection conn = pool.acquire();

		// ======================select//======================
		String sql = "select * from UserAccount where username = ?";
		PreparedStatement pareStatement = conn.prepareStatement(sql);
		pareStatement.setString(1, username);
		ResultSet ret = pareStatement.executeQuery();
		try {
			if (ret.next()) {
				UserAccount account = new UserAccount();
				account.username = username;
				account.balance = new BigDecimal(ret.getDouble("balance"));
				account.score = ret.getInt("score");

				System.out.println(account.toString());
			}
		} finally {
			ret.close();
			pareStatement.close();
		}
		conn.close();

	}

	static AccountService service = new AccountService();

	private static void testupdates() {

		Runnable run = new Runnable() {
			@Override
			public void run() {
				for (int i = 0; i < 5; i++) {
					UserAccount account = service.getUserAccount(username);
					if (account != null) {
						account.balance = account.balance.add(new BigDecimal(2));
						boolean ret = service.updateBalanceAndScore(account);
						// System.out.println(ret);
					}
				}
			}
		};

		for (int i = 0; i < 100; i++) {
			Thread thread = new Thread(run);
			thread.start();
		}
	}

	private static void createTables() {

		Connection connection = pool.acquire();
		try {
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);
			statement.executeUpdate("create table IF NOT EXISTS User(username string, usernick string, password string, role string)");
			statement.executeUpdate("create table IF NOT EXISTS ScoreFlow(username string,score int,datetime string,source string,inorexp string)");
			statement.executeUpdate("create table if not exists UserAccount(username string, balance decimal(15,3), score int, frozenScore int, timestamp TIMESTAMP )");

			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.recycle(connection);
		}
	}

	public static final void initTestData() {

		Connection connection = pool.acquire();
		try {
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);
			statement.execute("insert into User values('wolf','wolf','wolf','m') ");
			statement.execute("insert into UserAccount values('wolf',1000,1000,10,datetime('now','localtime')) ");

			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.recycle(connection);
		}
	}

}
