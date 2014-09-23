package org.me.api.advertise;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.me.BasicHttpServlet;
import org.me.models.Advertise;
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
@WebServlet("/advertisement/publish")
public class AdvertisementPublishServlet extends BasicHttpServlet {

	private static final long serialVersionUID = -7694672927716531619L;
	private AdvertisementService service = new AdvertisementService();

	@Override
	protected void handle(HttpServletRequest req, HttpServletResponse resp) {
		Message msg = new Message();
		String username = req.getParameter("username");
		String title = req.getParameter("title");
		String imagePath = req.getParameter("imagePath");
		Advertise advertise = new Advertise();
		if (validate(username, title, imagePath, advertise)) {
			boolean ret = service.publish(advertise);
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

	private boolean validate(String username, String title, String imagePath, Advertise advertise) {
		// 非空验证
		if (StringUtils.isEmpty(username)
		// || StringUtils.isEmpty(title)
				|| StringUtils.isEmpty(imagePath)) {
			return false;
		}
		advertise.username = username;
		advertise.title = title;
		advertise.imagePath = imagePath;
		return true;
	}
}
