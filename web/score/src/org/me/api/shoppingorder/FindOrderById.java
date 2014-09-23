package org.me.api.shoppingorder;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.me.BasicHttpServlet;
import org.me.mo.SystemInitialize;
import org.me.models.Order;
import org.me.services.UserOrderService;

import com.alibaba.fastjson.JSON;

@WebServlet("/ShoppingOrder/findorderbyid")
public class FindOrderById extends BasicHttpServlet {

	private static final long serialVersionUID = -6039577884126052364L;

	@Override
	protected void handle(HttpServletRequest req, HttpServletResponse resp) {		
		 String orderId = req.getParameter("orderid");
		 Order order = UserOrderService.FindOrderByid(SystemInitialize.pool,orderId);
		outputString(resp, JSON.toJSONString(order));
	}
}
