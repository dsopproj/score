package org.me.services;

import java.sql.Connection;
import java.sql.SQLException;
import java.sql.Statement;

import org.me.mo.SystemInitialize;
import org.me.utils.Pool;

/**
 * 抽象service类，处理通用的connection和exception.
 * 
 * @author wolf
 *
 * @param <T>
 */
public abstract class AbstractService<T> {

	public abstract T onExecute(Connection conn, Statement statement) throws SQLException;

	public T execute() {
		synchronized (Pool.locker) {
			Connection conn = SystemInitialize.pool.acquire();
			Statement statement = null;
			try {
				statement = conn.createStatement();
				return onExecute(conn, statement);
			} catch (SQLException e) {
				e.printStackTrace();
				// LOG. ERROR
				return null;
			} catch (Exception e) {
				e.printStackTrace();
				// LOG. ERROR
				return null;
			} finally {
				if (statement != null) {
					try {
						statement.close();
					} catch (Exception e) {
						e.printStackTrace();
					}
				}
				SystemInitialize.pool.recycle(conn);
			}
		}
	}
}
