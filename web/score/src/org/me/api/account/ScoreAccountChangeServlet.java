package org.me.api.account;

import javax.servlet.annotation.WebServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;

import org.me.BasicHttpServlet;
import org.me.models.UserAccount;
import org.me.services.AccountService;
import org.me.utils.Const;
import org.me.utils.StringUtils;

import com.alibaba.fastjson.JSON;

/**
 * 分数账户接口-更新余额
 * 
 * @author wolf
 * @Date 2014-9-11
 *
 */
@WebServlet("/account/score/change")
public class ScoreAccountChangeServlet extends BasicHttpServlet {

	private static final long serialVersionUID = -4589697955006254854L;

	private AccountService service = new AccountService();

	@Override
	protected void handle(HttpServletRequest req, HttpServletResponse resp) {

		String username = req.getParameter("username");
		String score = req.getParameter("score");
		String oper = req.getParameter("oper");

		Integer[] scoreeVal = new Integer[1];
		if (validate(username, score, oper, scoreeVal)) {

			if (Const.OPER_DEC.equals(oper))
				scoreeVal[0] = -scoreeVal[0];

			UserAccount account = service.getUserAccount(username);
			if (account == null) {

				outputString(resp, "can not found :" + username);
			} else {
				account.score = account.score + scoreeVal[0];

				boolean ret = service.updateBalanceAndScore(account);
				if (ret) {
					outputString(resp, JSON.toJSONString(account));
				} else {
					outputString(resp, "change balance:" + ret);
				}
			}
		} else {
			outputString(resp, Const.CHECK_PARAM);
		}
	}

	private boolean validate(String username, String score, String oper, Integer[] scoreeVal) {
		// 非空验证
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(score) || StringUtils.isEmpty(oper)) {
			return false;
		} else if (!Const.OPER_DEC.equals(oper) && !Const.OPER_INC.equals(oper)) {
			// 合法性验证
			return false;
		} else {
			// 金额的验证
			try {
				scoreeVal[0] = Integer.parseInt(score);
				if (scoreeVal[0] <= 0)
					return false;
			} catch (Exception e) {
				return false;
			}
		}
		return true;
	}

}
