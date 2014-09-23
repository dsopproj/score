package org.me.api;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.me.BasicHttpServlet;
import org.me.mo.SystemInitialize;
import org.me.services.ScoreServices;

@WebServlet("/getReceive")
public class ScoreNumberServlet extends BasicHttpServlet {

	private static final long serialVersionUID = 4926999893966086539L;

	@Override
	protected void handle(HttpServletRequest req, HttpServletResponse resp) {
		String username = req.getParameter("user");
		String way = req.getParameter("inorexp");
		int number = ScoreServices.searchCore(SystemInitialize.pool, username,
				way);
		outputString(resp, Integer.toString(number));
	}
}
