package org.me.api;

import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.me.BasicHttpServlet;
import org.me.mo.SystemInitialize;
import org.me.models.User;
import org.me.services.UserService;
import org.me.utils.Const;

@WebServlet("/login")
public class LoginServlet extends BasicHttpServlet {

	private static final long serialVersionUID = -533205021520343128L;

	@Override
	protected void handle(HttpServletRequest req, HttpServletResponse resp) {
		String username = req.getParameter("user");
		String password = req.getParameter("pwd");
	
		ArrayList<User> userList = UserService.search(SystemInitialize.pool,
				username, password);
		if (userList.size()!=1)
			error(resp, Const.NoSuchUser);
		else {
			getUser(req.getSession(), userList.get(0));
			ok(resp);
		}
	}

	private void getUser(HttpSession session, User user) {
		Object obj = session.getAttribute(Const.User);
		if (obj == null)
			user.setHttpSession(session);
	}
}
