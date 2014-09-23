package org.me.api;

import java.util.ArrayList;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.me.BasicHttpServlet;
import org.me.mo.SystemInitialize;
import org.me.models.User;
import org.me.services.UserService;
import org.me.utils.Const;

@WebServlet("/register")
public class RegisterServlet extends BasicHttpServlet {

	private static final long serialVersionUID = 5115346272074349360L;

	@Override
	protected void handle(HttpServletRequest req, HttpServletResponse resp) {
		User user = getParameter(req, User.class);
		if(user==null){
			error(resp, Const.InvalidRequest);
				return;
		}
		ArrayList<User> userList = UserService.search(SystemInitialize.pool,
				user.username);
		if (userList.size() == 0) {
		UserService.insert(SystemInitialize.pool, user);	
		ok(resp);
		} else {
			error(resp, Const.UserAlreadyExists);
		}
	}	
}
