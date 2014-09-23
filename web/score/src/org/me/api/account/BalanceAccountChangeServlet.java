package org.me.api.account;

import java.math.BigDecimal;

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
 * 
 * 资金账户接口-更改余额
 * 
 * @author wolf
 * @Date 2014-9-11
 */
@WebServlet("/account/balance/change")
public class BalanceAccountChangeServlet extends BasicHttpServlet {

	private static final long serialVersionUID = 8113861961906061627L;

	private AccountService service = new AccountService();

	@Override
	protected void handle(HttpServletRequest req, HttpServletResponse resp) {
		String username = req.getParameter("username");
		String balance = req.getParameter("balance");
		String oper = req.getParameter("oper");

		BigDecimal[] balanceVal = new BigDecimal[1];
		if (validate(username, balance, oper, balanceVal)) {

			if (Const.OPER_DEC.equals(oper))
				balanceVal[0] = balanceVal[0].multiply(new BigDecimal(-1));

			UserAccount account = service.getUserAccount(username);
			if (account == null) {

				outputString(resp, "can not found :" + username);
			} else {
				account.balance = account.balance.add(balanceVal[0]);

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

	private boolean validate(String username, String balance, String oper, BigDecimal[] balanceVal) {
		// 非空验证
		if (StringUtils.isEmpty(username) || StringUtils.isEmpty(balance) || StringUtils.isEmpty(oper)) {
			return false;
		} else if (!Const.OPER_DEC.equals(oper) && !Const.OPER_INC.equals(oper)) {
			// 合法性验证
			return false;
		} else {
			// 金额的验证
			try {
				balanceVal[0] = new BigDecimal(balance);
				if (balanceVal[0].doubleValue() <= 0)
					return false;
			} catch (Exception e) {
				return false;
			}
		}
		return true;
	}

}
