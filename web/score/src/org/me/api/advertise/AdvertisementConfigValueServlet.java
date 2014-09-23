package org.me.api.advertise;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.me.BasicHttpServlet;
import org.me.models.Message;
import org.me.services.AdvertisementService;
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
@WebServlet("/advertisement/config/value")
public class AdvertisementConfigValueServlet extends BasicHttpServlet {

	private static final long serialVersionUID = -7694672927716531619L;
	private AdvertisementService service = new AdvertisementService();

	@Override
	protected void handle(HttpServletRequest req, HttpServletResponse resp) {
		Message msg = new Message();
		String username = req.getParameter("username");
		if (validate(username)) {
			msg.content = service.getConfig(username);
		} else {
			msg.action = Action.Error;
			msg.content = Const.CHECK_PARAM;
		}
		output(resp, msg);
	}

	private boolean validate(String username) {
		// 非空验证
		if (StringUtils.isEmpty(username)) {
			return false;
		}
		return true;
	}
}
