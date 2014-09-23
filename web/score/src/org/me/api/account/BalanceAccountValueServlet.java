package org.me.api.account;

import java.math.BigDecimal;

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
 * 资金账户接口-查询余额
 * 
 * @author wolf
 * @Date 2014-9-11
 *
 */
@WebServlet("/account/balance/value")
public class BalanceAccountValueServlet extends BasicHttpServlet {

	private static final long serialVersionUID = 5285770864612291694L;

	private AccountService service = new AccountService();

	@Override
	protected void handle(HttpServletRequest req, HttpServletResponse resp) {
		String username = req.getParameter("username");

		if (!StringUtils.isEmpty(username)) {
			BigDecimal balance = service.getBalanceAccountValue(username);
			if (balance == null)
				balance = new BigDecimal(0);
			Score score = new Score();
			score.balance = balance;
			score.username = username;
			outputString(resp, JSON.toJSONString(score));
		} else {
			outputString(resp, Const.CHECK_PARAM);
		}
	}
}
