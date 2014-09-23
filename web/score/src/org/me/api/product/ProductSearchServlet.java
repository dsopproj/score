package org.me.api.product;

import java.util.List;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.me.BasicHttpServlet;
import org.me.models.Product;
import org.me.services.ProductService;
import org.me.utils.StringUtils;

import com.alibaba.fastjson.JSON;

/**
 * 商品 查询
 * 
 * @author wolf
 * @Date 2014-9-15
 *
 */
@WebServlet("/products")
public class ProductSearchServlet extends BasicHttpServlet {

	private static final long serialVersionUID = -2967537250608695070L;

	private ProductService service = new ProductService();

	@Override
	protected void handle(HttpServletRequest req, HttpServletResponse resp) {
		String username = req.getParameter("username");

		if (!StringUtils.isEmpty(username)) {
			List<Product> listx = service.findProducts(username);
			outputString(resp, JSON.toJSONString(listx));
		}
	}
}
