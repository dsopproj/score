package org.me.api.product;

import java.math.BigDecimal;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.me.BasicHttpServlet;
import org.me.models.Message;
import org.me.models.Product;
import org.me.services.ProductService;
import org.me.utils.Action;
import org.me.utils.Const;
import org.me.utils.StringUtils;

/**
 * 商品 发布
 * 
 * @author wolf
 * @Date 2014-9-15
 *
 */
@WebServlet("/product/publish")
public class ProductPublishServlet extends BasicHttpServlet {

	private static final long serialVersionUID = -7694672927716531619L;
	private ProductService service = new ProductService();

	@Override
	protected void handle(HttpServletRequest req, HttpServletResponse resp) {
		Message msg = new Message();
		Product product = new Product();
		product.username = req.getParameter("username");
		product.title = req.getParameter("title");
		product.content = req.getParameter("content");
		String cash = req.getParameter("cash");
		product.qrCode = req.getParameter("qrCode");

		if (validate(product, cash)) {
			boolean ret = service.publish(product);
			if (!ret) {
				msg.action = Action.Error;
			}
			msg.content = String.valueOf(ret);
		} else {
			msg.action = Action.Error;
			msg.content = Const.CHECK_PARAM;
		}
		output(resp, msg);

	}

	private boolean validate(Product product, String cash) {
		// 非空验证
		if (StringUtils.isEmpty(product.username) || StringUtils.isEmpty(product.title) || StringUtils.isEmpty(product.content) || StringUtils.isEmpty(cash)
		// || StringUtils.isEmpty(product.qrCode)
		) {
			return false;
		} else {
			// 金额的验证
			try {
				product.cash = new BigDecimal(cash);
				if (product.cash.doubleValue() <= 0)
					return false;
			} catch (Exception e) {
				return false;
			}
		}
		return true;
	}
}
