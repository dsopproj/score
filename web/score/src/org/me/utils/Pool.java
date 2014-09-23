package org.me.utils;

import java.security.InvalidParameterException;
import java.sql.Connection;
import java.sql.DriverManager;
import java.sql.SQLException;
import java.util.Map;
import java.util.Queue;
import java.util.concurrent.ConcurrentHashMap;
import java.util.concurrent.ConcurrentLinkedDeque;

public class Pool {
	public static final byte[] locker = new byte[0];
	private String uri;
	private Queue<Connection> cached = new ConcurrentLinkedDeque<>();
	private Map<Integer, Connection> all = new ConcurrentHashMap<>();

	/**
	 * Instantiates a new pool.
	 *
	 * @param url
	 *            the url
	 */
	public Pool(String url) {
		this(1, url);
	}

	/**
	 * Instantiates a new pool.
	 *
	 * @param capacity
	 *            the capacity
	 * @param url
	 *            the url
	 */
	public Pool(int capacity, String url) {
		try {
			Class.forName("org.sqlite.JDBC");
		} catch (ClassNotFoundException e) {
			e.printStackTrace();
		}
		uri = "jdbc:sqlite:" + url;
		try {
			for (int i = 0; i < capacity; i++) {
				Connection c = DriverManager.getConnection(uri);
				cached.offer(c);
				all.put(c.hashCode(), c);
			}
		} catch (SQLException e) {
			e.printStackTrace();
		}
	}

	/**
	 * Acquire.
	 *
	 * @return the connection
	 */
	public Connection acquire() {
		synchronized (locker) {
			Connection retval = cached.poll();
			System.out.println("acquire Connection :" + retval);
			if (retval == null) {
				try {
					retval = DriverManager.getConnection(uri);
					all.put(retval.hashCode(), retval);
					System.out.println("acquire new connection.");
				} catch (SQLException e) {
					e.printStackTrace();
				}
			}
			return retval;
		}
	}

	/**
	 * Recycle.
	 *
	 * @param connection
	 *            the connection
	 */
	public void recycle(Connection connection) {
		synchronized (locker) {
			System.out.println("recycle Connection:" + connection + "\n");
			if (connection != null) {
				if (!all.containsKey(connection.hashCode()))
					throw new InvalidParameterException("Connection not come from this pool.");
				if (!cached.contains(connection))
					cached.offer(connection);
			}
		}
	}

	/**
	 * Destroy.
	 */
	public void destroy() {
		for (Connection connection : all.values()) {
			try {
				connection.close();
			} catch (SQLException e) {
				e.printStackTrace();
			}
		}
		all.clear();
	}
}
