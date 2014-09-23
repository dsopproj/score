package org.me.api.shoppingorder;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.me.BasicHttpServlet;
import org.me.mo.SystemInitialize;
import org.me.models.Order;
import org.me.services.UserOrderService;

import com.alibaba.fastjson.JSON;

@WebServlet("/ShoppingOrder/findorderbyuser")
public class FindOrderByUserName extends BasicHttpServlet{

	private static final long serialVersionUID = 3542540677876701024L;

	@Override
	protected void handle(HttpServletRequest req, HttpServletResponse resp) {
		 String username = req.getParameter("username");
		 List<Order> listorder = UserOrderService.FindOrderByUserName(SystemInitialize.pool,username);
		outputString(resp, JSON.toJSONString(listorder));
	}

}
