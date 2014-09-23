package org.me.api;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.me.BasicHttpServlet;
import org.me.models.User;
//import org.me.services.UserService;
import org.me.utils.Const;

@WebServlet("/logout")
public class LogoutServlet extends BasicHttpServlet {

	private static final long serialVersionUID = -941389706955995118L;

	@Override
	protected void handle(HttpServletRequest req, HttpServletResponse resp) {
		// TODO Auto-generated method stub
		User user = getUser(req);
		if (user != null)
			req.getSession().removeAttribute(Const.User);
	}
}
