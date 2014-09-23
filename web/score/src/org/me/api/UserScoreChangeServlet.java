package org.me.api;


import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.me.BasicHttpServlet;
import org.me.mo.SystemInitialize;
import org.me.models.Score;
import org.me.services.ScoreServices;
import org.me.utils.Const;

@WebServlet("/UserScoreChange")
public class UserScoreChangeServlet extends BasicHttpServlet {

	private static final long serialVersionUID = 5544119681408764290L;

	@Override
	protected void handle(HttpServletRequest req, HttpServletResponse resp) {
		Score score = getParameter(req, Score.class);
		if (score == null) {
			error(resp, Const.InvalidRequest);
			return;
		}
		score.datetime = Long.toString(System.currentTimeMillis());
		ScoreServices.insert(SystemInitialize.pool, score);
		ok(resp);
	}
}
