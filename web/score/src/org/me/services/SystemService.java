package org.me.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.me.utils.Pool;

public class SystemService {

	/**
	 * 创建数据库
	 * 
	 * @param pool
	 */
	public static final boolean create(Pool pool) {
		Connection connection = pool.acquire();
		try {
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);
			statement.executeUpdate("create table IF NOT EXISTS User(username string, usernick string, password string, role string)");
			statement.executeUpdate("create table IF NOT EXISTS ScoreFlow(username string,score int,datetime string,source string,inorexp string)");
			statement.executeUpdate("create table if not EXISTS UserAccount(username string, balance decimal(15,3), score int, frozenScore int, timestamp TIMESTAMP )");
			statement.executeUpdate("create table IF NOT EXISTS Product(id string, username string, title string, content string, cash decimal(15,3), imagePath string )");
			statement.executeUpdate("create table if not EXISTS AdvertiseConfig(username string, interval int, speed int)");
			statement.executeUpdate("create table if not EXISTS Advertise(id string, username string, title string, imagePath string)");
			statement.executeUpdate("create table IF NOT EXISTS Shopping(shopingid string,shopingname string,shopingaddress string)");
			statement.executeUpdate("create table IF NOT EXISTS UserOrder(orderid string, username string, shoppingid string,state int,datetime string)");
			statement.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			pool.recycle(connection);
		}
	}

	/**
	 * 初始化数据
	 * 
	 * @param pool
	 */
	public static final boolean initTestData(Pool pool) {

		Connection connection = pool.acquire();
		try {
			Statement statement = connection.createStatement();
			statement.setQueryTimeout(30);
			statement.executeUpdate("insert into User values('wolf','wolf','wolf','m') ");
			statement.executeUpdate("insert into UserAccount values('wolf',1000,1000,0,datetime('now','localtime')) ");
			statement.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			pool.recycle(connection);
		}
	}

	/**
	 * 重置数据库
	 * 
	 * @param pool
	 */
	public static final boolean reset(Pool pool) {

//		pool.destroy();
		Connection connection = pool.acquire();
		try {
			Statement statement = connection.createStatement();
			statement.executeUpdate("drop table IF EXISTS User");
			statement.executeUpdate("drop table IF EXISTS ScoreFlow");
			statement.executeUpdate("drop table if exists UserAccount");
			statement.executeUpdate("drop table if exists Product");
			statement.executeUpdate("drop table if exists AdvertiseConfig");
			statement.executeUpdate("drop table if exists Advertise");
			statement.executeUpdate("drop table if exists Shopping");
			statement.executeUpdate("drop table if exists UserOrder");
			statement.executeUpdate("drop table IF EXISTS Score");
			statement.executeUpdate("drop table if exists UserScore");
			statement.close();
			return true;
		} catch (SQLException e) {
			e.printStackTrace();
			return false;
		} finally {
			pool.recycle(connection);
		}
	}

}
