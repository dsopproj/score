package org.me.services;

import java.math.BigDecimal;
import java.sql.Connection;
import java.sql.PreparedStatement;
import java.sql.ResultSet;
import java.sql.SQLException;
import java.sql.Statement;
import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

import org.me.models.Product;

public class ProductService {

	public boolean publish(final Product product) {
		AbstractService<Boolean> service = new AbstractService<Boolean>() {
			@Override
			public Boolean onExecute(Connection conn, Statement statement) throws SQLException {

				String sql = "insert into Product (id, username,title,imagePath,content,cash) values (?,?,?,?,?,?) ";
				PreparedStatement pareStatement = conn.prepareStatement(sql);
				try {
					pareStatement.setString(1, UUID.randomUUID().toString());
					pareStatement.setString(2, product.username);
					pareStatement.setString(3, product.title);
					pareStatement.setString(4, product.imagePath);
					pareStatement.setString(5, product.content);
					pareStatement.setDouble(6, product.cash.doubleValue());
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

	public List<Product> findProducts(final String username) {

		AbstractService<List<Product>> service = new AbstractService<List<Product>>() {
			@Override
			public List<Product> onExecute(Connection conn, Statement statement) throws SQLException {

				String sql = "select * from Product where username = ? ";
				PreparedStatement pareStatement = conn.prepareStatement(sql);
				try {
					pareStatement.setString(1, username);
					ResultSet ret = pareStatement.executeQuery();
					List<Product> list = new ArrayList<Product>();
					while (ret.next()) {
						Product po = new Product();
						po.id = ret.getString("id");
						po.username = username;
						po.title = ret.getString("title");
						po.content = ret.getString("content");
						po.cash = BigDecimal.valueOf(ret.getDouble("cash"));
						po.imagePath = ret.getString("imagePath");
						list.add(po);
					}
					return list;
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

	public BigDecimal getCashById(final String id) {

		AbstractService<BigDecimal> service = new AbstractService<BigDecimal>() {
			@Override
			public BigDecimal onExecute(Connection conn, Statement statement) throws SQLException {

				String sql = "select cash from Product where id = ? ";
				PreparedStatement pareStatement = conn.prepareStatement(sql);
				try {
					pareStatement.setString(1, id);
					ResultSet ret = pareStatement.executeQuery();
					if (ret.next()) {
						return BigDecimal.valueOf(ret.getDouble("cash"));
					} else {
						return null;
					}
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
