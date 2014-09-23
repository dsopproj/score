package org.me.api.account;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.me.BasicHttpServlet;
import org.me.models.UserAccount;
import org.me.services.AccountService;

import com.alibaba.fastjson.JSON;

@WebServlet("/account/user/value")
public class UserAccountValueServlet  extends BasicHttpServlet {

	private static final long serialVersionUID = -8904723435138695814L;

	private AccountService service = new AccountService();
	
	@Override
	protected void handle(HttpServletRequest req, HttpServletResponse resp) {
		String username = req.getParameter("username");
		UserAccount account = service.getUserAccount(username);
		if (account != null) {
			outputString(resp, JSON.toJSONString(account));
		} 
	}

}
