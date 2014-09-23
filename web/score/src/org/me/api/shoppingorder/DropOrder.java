package org.me.api.shoppingorder;

import java.math.BigDecimal;
import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import org.me.BasicHttpServlet;
import org.me.mo.SystemInitialize;
import org.me.services.AccountService;
import org.me.services.ProductService;
import org.me.services.UserOrderService;

@WebServlet("/ShoppingOrder/droporder")
public class DropOrder extends BasicHttpServlet {

	private static final long serialVersionUID = 2376284016715125432L;
	private ProductService productService = new ProductService();
	private AccountService accountService = new AccountService();

	@Override
	protected void handle(HttpServletRequest req, HttpServletResponse resp) {
		String username = req.getParameter("username");
		String shoppingId = req.getParameter("shoppingid");
		if (username == null || shoppingId == null)
			outputString(resp, "false");
		else {
			BigDecimal cash = productService.getCashById(shoppingId);
			if (cash == null) {
				outputString(resp, "can not found shoppingId! false");
			} else {
				Integer score = accountService.getScoreAccountValue(username);
				BigDecimal scoreBD = new BigDecimal(score);
				if (score > 0 && scoreBD.compareTo(cash) >= 0) {
					boolean ret = UserOrderService.insert(SystemInitialize.pool, username, shoppingId, cash);
					if (ret) {
						outputString(resp, "true");
					} else {
						outputString(resp, "insert error!");
					}
				} else {
					outputString(resp, "account score is not enough! false");
				}
			}
		}
	}

}
