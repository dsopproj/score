package org.me.api.account;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.me.BasicHttpServlet;
import org.me.models.Score;
import org.me.services.AccountService;
import org.me.utils.Const;
import org.me.utils.StringUtils;

import com.alibaba.fastjson.JSON;

/**
 * 分数账户接口-查询余额
 * 
 * @author wolf
 * @Date 2014-9-11
 *
 */
@WebServlet("/account/score/value")
public class ScoreAccountValueServlet extends BasicHttpServlet {

	private static final long serialVersionUID = -4589697955006254854L;

	private AccountService service = new AccountService();

	@Override
	protected void handle(HttpServletRequest req, HttpServletResponse resp) {
		String username = req.getParameter("username");

		if (!StringUtils.isEmpty(username)) {
			Integer scoreVal = service.getScoreAccountValue(username);
			if (scoreVal == null)
				scoreVal = new Integer(0);
			Score score = new Score();
			score.score = scoreVal;
			score.username = username;
			outputString(resp, JSON.toJSONString(score));
		} else {
			outputString(resp, Const.CHECK_PARAM);
		}
	}
}
