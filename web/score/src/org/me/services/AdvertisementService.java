package org.me.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.UUID;

import org.me.models.Advertise;

import com.alibaba.fastjson.JSONArray;
import com.alibaba.fastjson.JSONObject;

public class AdvertisementService {

	public boolean config(final String username, final Integer interval, final Integer speed) {
		AbstractService<Boolean> service = new AbstractService<Boolean>() {
			@Override
			public Boolean onExecute(Connection conn, Statement statement) throws SQLException {
				String checkSql = "select * from AdvertiseConfig where username = ?";
				PreparedStatement pareStatement = conn.prepareStatement(checkSql);
				PreparedStatement pareStatementx = null;
				try {
					pareStatement.setString(1, username);
					ResultSet ret = pareStatement.executeQuery();
					if (ret.next()) {
						// update data
						String updateSql = "update AdvertiseConfig set interval=? , speed =? where username = ?";
						pareStatementx = conn.prepareStatement(updateSql);
						pareStatementx.setString(3, username);
						pareStatementx.setInt(1, interval);
						pareStatementx.setInt(2, speed);
					} else {
						// insert data
						String insertSql = "insert into AdvertiseConfig (username, interval, speed) values (?,?,?)";
						pareStatementx = conn.prepareStatement(insertSql);
						pareStatementx.setString(1, username);
						pareStatementx.setInt(2, interval);
						pareStatementx.setInt(3, speed);
					}
					pareStatementx.execute();
					int updatecount = pareStatementx.getUpdateCount();
					return updatecount == 1;
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				} finally {
					pareStatement.close();
					if (pareStatementx != null)
						pareStatementx.close();
				}
			}
		};
		return service.execute();
	}

	public String getConfig(final String username) {
		AbstractService<String> service = new AbstractService<String>() {
			@Override
			public String onExecute(Connection conn, Statement statement) throws SQLException {
				String checkSql = "select * from AdvertiseConfig where username = ?";
				PreparedStatement pareStatement = conn.prepareStatement(checkSql);
				PreparedStatement pareStatementx = null;
				try {
					pareStatement.setString(1, username);
					ResultSet ret = pareStatement.executeQuery();
					if (ret.next()) {
						JSONObject jo = new JSONObject();
						jo.put("username", username);
						jo.put("interval", ret.getInt("interval"));
						jo.put("speed", ret.getInt("speed"));
						return jo.toJSONString();
					} else {
						return null;
					}
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				} finally {
					pareStatement.close();
					if (pareStatementx != null)
						pareStatementx.close();
				}
			}
		};
		return service.execute();
	}

	public boolean publish(final Advertise advertise) {
		AbstractService<Boolean> service = new AbstractService<Boolean>() {
			@Override
			public Boolean onExecute(Connection conn, Statement statement) throws SQLException {
				String checkSql = "insert into Advertise (id, username, title, imagePath) values (?,?,?,?)";
				PreparedStatement pareStatement = conn.prepareStatement(checkSql);
				try {
					pareStatement.setString(1, UUID.randomUUID().toString());
					pareStatement.setString(2, advertise.username);
					pareStatement.setString(3, advertise.title);
					pareStatement.setString(4, advertise.imagePath);
					pareStatement.execute();
					int updatecount = pareStatement.getUpdateCount();
					return updatecount == 1;
				} catch (Exception e) {
					e.printStackTrace();
					return false;
				} finally {
					pareStatement.close();
				}
			}
		};
		return service.execute();
	}

	public String getAdvertisements(final String username) {
		AbstractService<String> service = new AbstractService<String>() {
			@Override
			public String onExecute(Connection conn, Statement statement) throws SQLException {
				String checkSql = "select * from Advertise where username = ?";
				PreparedStatement pareStatement = conn.prepareStatement(checkSql);
				PreparedStatement pareStatementx = null;
				try {
					pareStatement.setString(1, username);
					ResultSet ret = pareStatement.executeQuery();
					JSONArray ja = new JSONArray();
					while (ret.next()) {
						JSONObject jo = new JSONObject();
						jo.put("username", username);
						jo.put("id", ret.getString("id"));
						jo.put("title", ret.getInt("title"));
						jo.put("imagePath", ret.getInt("imagePath"));
						ja.add(jo);
					}
					return ja.toJSONString();
				} catch (Exception e) {
					e.printStackTrace();
					return null;
				} finally {
					pareStatement.close();
					if (pareStatementx != null)
						pareStatementx.close();
				}
			}
		};
		return service.execute();
	}

}
