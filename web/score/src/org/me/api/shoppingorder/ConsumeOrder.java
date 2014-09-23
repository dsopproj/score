package org.me.api.shoppingorder;

import java.math.BigDecimal;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.me.BasicHttpServlet;
import org.me.mo.SystemInitialize;
import org.me.models.Order;
import org.me.services.AccountService;
import org.me.services.ProductService;
import org.me.services.UserOrderService;
import org.me.utils.StringUtils;

@WebServlet("/ShoppingOrder/consume")
public class ConsumeOrder extends BasicHttpServlet {

	private static final long serialVersionUID = -2905820336895899963L;
	private ProductService productService = new ProductService();

	@Override
	protected void handle(HttpServletRequest req, HttpServletResponse resp) {
		String orderId = req.getParameter("orderid");
		if (!StringUtils.isEmpty(orderId)) {
			Order order = UserOrderService.FindOrderByid(SystemInitialize.pool, orderId);
			if (order != null) {
				BigDecimal cash = productService.getCashById(order.shopingid);
				if (cash != null) {
					boolean ret = UserOrderService.customeOrder(SystemInitialize.pool, orderId, order.username, cash);
					if (ret)
						outputString(resp, "true");
				}
			}
		}
		outputString(resp, "false");
	}
}
