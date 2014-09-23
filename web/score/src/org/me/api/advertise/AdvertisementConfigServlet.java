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
@WebServlet("/advertisement/config")
public class AdvertisementConfigServlet extends BasicHttpServlet {

	private static final long serialVersionUID = -7694672927716531619L;
	private AdvertisementService service = new AdvertisementService();

	@Override
	protected void handle(HttpServletRequest req, HttpServletResponse resp) {
		Message msg = new Message();
		String username = req.getParameter("username");
		String intervalStr = req.getParameter("interval");
		String speedStr = req.getParameter("speed");
		Integer[] interval = { 0 };
		Integer[] speed = { 0 };
		if (validate(username, intervalStr, speedStr, interval, speed)) {
			boolean ret = service.config(username, interval[0], speed[0]);
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

	private boolean validate(String username, String intervalStr, String speedStr, Integer[] interval, Integer[] speed) {
		// 非空验证
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(intervalStr) || StringUtils.isEmpty(speedStr)) {
			return false;
		} else {
			// 数字的验证
			try {
				interval[0] = Integer.valueOf(intervalStr);
				speed[0] = Integer.valueOf(speedStr);
			} catch (Exception e) {
				return false;
			}
		}
		return true;
	}
}
