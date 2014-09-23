package org.app.api;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.me.BasicHttpServlet;

@WebServlet("/Shopping/Order")
public class ShoppingOrder  extends BasicHttpServlet{

	private static final long serialVersionUID = 4305562213775684056L;

	@Override
	protected void handle(HttpServletRequest req, HttpServletResponse resp) {		
		req.getParameter("");
	}
}
