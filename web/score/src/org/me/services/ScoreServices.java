package org.me.services;

import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;

import org.me.mo.SystemInitialize;
import org.me.models.Score;
import org.me.utils.Pool;

public class ScoreServices {
	public static final void insert(Pool pool, Score score) {
		Connection connection = pool.acquire();
		try {
			String sql = "insert into Score(username,score,datetime,source,inorexp) values (?,?,?,?,?);";
			PreparedStatement statement = connection.prepareStatement(sql);
			statement.setQueryTimeout(30);
			statement.setString(1, score.username);
			statement.setInt(2, score.score * SystemInitialize.getValue());
			statement.setString(3, score.datetime);
			statement.setString(4, score.source);
			statement.setString(5, score.inorexp);
			statement.executeUpdate();
			statement.close();
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.recycle(connection);
		}
	}

	public static final int searchCore(Pool pool, String username, String from) {
		Connection connection = pool.acquire();
		int scoreSum = 0;
		try {
			String sql = "select  score  from Score where username='"
					+ username + "' and inorexp='" + from + "';";
			Statement statement = connection.createStatement();
			ResultSet rs = statement.executeQuery(sql);
			while (rs.next()) {
				scoreSum += Integer.parseInt(rs.getString("score"));
			}
		} catch (SQLException e) {
			e.printStackTrace();
		} finally {
			pool.recycle(connection);
		}
		return scoreSum;
	}
}
