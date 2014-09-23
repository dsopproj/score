package org.me.services;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.me.models.UserAccount;

/**
 * 账户类
 * 
 * @author wolf
 * @Date 2014-9-11
 */
public class AccountService {

	/**
	 * 取得资金账户余额
	 * 
	 * @param username
	 * @return
	 */
	public BigDecimal getBalanceAccountValue(final String username) {
		return new AbstractService<BigDecimal>() {
			@Override
			public BigDecimal onExecute(Connection conn, Statement statement) throws SQLException {
				String sql = "select balance from UserAccount where username=?";
				PreparedStatement preStatement = conn.prepareStatement(sql);
				preStatement.setString(1, username);
				try {
					ResultSet ret = preStatement.executeQuery();
					if (ret.next()) {
						return new BigDecimal(ret.getDouble("balance"));
					} else {
						return null;
					}
				} finally {
					preStatement.close();
				}
			}
		}.execute();
	}

	/**
	 * 取得分数账户余额
	 * 
	 * @param username
	 * @return
	 */
	public Integer getScoreAccountValue(final String username) {
		return new AbstractService<Integer>() {
			@Override
			public Integer onExecute(Connection conn, Statement statement) throws SQLException {
				String sql = "select score from UserAccount where username=?";
				PreparedStatement pareStatement = conn.prepareStatement(sql);
				pareStatement.setString(1, username);
				ResultSet ret = pareStatement.executeQuery();
				if (ret.next()) {
					return ret.getInt("score");
				} else {
					return null;
				}
			}
		}.execute();
	}

	public boolean addScore(final String username, final Integer scoreVal) {
		return new AbstractService<Boolean>() {
			@Override
			public Boolean onExecute(Connection conn, Statement statement) throws SQLException {

				String qrySql = "select * from UserAccount where username = ?";

				PreparedStatement qrypareStatement = conn.prepareStatement(qrySql);
				qrypareStatement.setString(1, username);
				ResultSet ret = qrypareStatement.executeQuery();
				while (ret.next()) {
					System.out.println(ret.getString("username") + ":" + ret.getDouble("balance"));
				}

				String sql = "update UserAccount set score = score + ? where username = ? ";
				PreparedStatement pareStatement = conn.prepareStatement(sql);
				pareStatement.setDouble(1, scoreVal);
				pareStatement.setString(2, username);
				System.out.println(username + ":" + scoreVal);
				try {
					int updatecount = pareStatement.executeUpdate();
					return updatecount == 1;
				} catch (Exception ex) {
					ex.printStackTrace();
					return false;
				} finally {
					pareStatement.close();
				}
			}
		}.execute();
	}

	/**
	 * 根据名字，查询账户对象
	 * 
	 * @param username
	 * @return
	 */
	public UserAccount getUserAccount(final String username) {
		return new AbstractService<UserAccount>() {
			@Override
			public UserAccount onExecute(Connection conn, Statement statement) throws SQLException {
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
						account.frozenScore = ret.getInt("frozenScore");

						System.out.println(account.toString());
						return account;
					} else {
						return null;
					}
				} finally {
					ret.close();
					pareStatement.close();
				}
			}
		}.execute();
	}

	public boolean updateBalanceAndScore(final UserAccount account) {
		AbstractService<Boolean> service = new AbstractService<Boolean>() {
			@Override
			public Boolean onExecute(Connection conn, Statement statement) throws SQLException {

				String sql = "update UserAccount set score = ?, balance = ? where username = ? ";
				PreparedStatement pareStatement = conn.prepareStatement(sql);
				try {
					pareStatement.setInt(1, account.score);
					pareStatement.setDouble(2, account.balance.doubleValue());
					pareStatement.setString(3, account.username);
					System.out.println(account);
					int updatecount = pareStatement.executeUpdate();
					return updatecount == 1;
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				} finally {
					pareStatement.close();
				}
			}
		};
		return service.execute();
	}
}
