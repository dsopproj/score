package org.me;

/*
 * Author:	Aliqi
 * Date:		2014-8-5	
 */

import java.io.IOException;
import java.io.PrintWriter;

import javax.servlet.ServletException;
import javax.servlet.http.HttpServlet;
import javax.servlet.http.HttpServletRequest;
import javax.servlet.http.HttpServletResponse;
import javax.servlet.http.HttpSession;

import org.me.models.Message;
import org.me.models.User;
import org.me.utils.Action;
import org.me.utils.Const;
//import org.me.utils.Pool;
//import org.me.utils.ReflectionUtils;
import org.me.utils.StringUtils;

import com.alibaba.fastjson.JSON;

/**
 * The Class BasicHttpServlet.
 */
public abstract class BasicHttpServlet extends HttpServlet {

	private static final long serialVersionUID = -4337525025712838181L;
	
//	/** The Constant pool. */
//	public static final Pool pool = new Pool(
//			ReflectionUtils.getClassPath(BasicHttpServlet.class) + "/score.db");

	@Override
	protected final void doGet(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		handle(req, resp);
	}

	@Override
	protected final void doPost(HttpServletRequest req, HttpServletResponse resp)
			throws ServletException, IOException {
		handle(req, resp);
	}

	/**
	 * Handle.
	 *
	 * @param req
	 *            the req
	 * @param resp
	 *            the resp
	 */
	protected abstract void handle(HttpServletRequest req,
			HttpServletResponse resp);

	/**
	 * Gets the parameter.
	 *
	 * @param <T>
	 *            the generic type
	 * @param req
	 *            the req
	 * @param clazz
	 *            the clazz
	 * @return the parameter
	 */
	protected static final <T> T getParameter(HttpServletRequest req,
			Class<T> clazz) {
		String content = req.getParameter(Const.RequestMark);
		if (StringUtils.isEmpty(content))
			return null;
		return parse(content, clazz);
	}

	/**
	 * Gets the user.
	 *
	 * @param req
	 *            the req
	 * @return the user
	 */
	protected static final User getUser(HttpServletRequest req) {
		HttpSession session = req.getSession();
		Object obj = session.getAttribute(Const.User);
		if (obj instanceof User)
			return (User) obj;
		return null;
	}

	/**
	 * Output.
	 *
	 * @param resp
	 *            the response
	 * @param message
	 *            the message
	 */
	protected static final void output(HttpServletResponse resp, Message message) {
		if (message == null)
			message = new Message();
		String result = JSON.toJSONString(message);
		try {
			resp.setCharacterEncoding(Const.Encoding);
			PrintWriter writer = resp.getWriter();
			writer.write(result);
			writer.flush();
		} catch (IOException e) {
		}
	}

	/**
	 * OutputString.
	 *
	 * @param resp
	 *            the response
	 * @param content
	 *            the content
	 */
	protected static final void outputString(HttpServletResponse resp,String content){
		try{
			resp.setCharacterEncoding(Const.Encoding);
			PrintWriter writer = resp.getWriter();
			writer.write(content);
			writer.flush();
		}catch(IOException e){			
		}
	}

	/**
	 * Error.
	 *
	 * @param resp
	 *            the resp
	 * @param err
	 *            the err
	 */
	protected static final void error(HttpServletResponse resp, String err) {
		Message error = new Message();
		error.action = Action.Error;
		error.content = err;
		output(resp, error);
	}

	/**
	 * Ok.
	 *
	 * @param resp
	 *            the resp
	 */
	protected static final void ok(HttpServletResponse resp) {
		output(resp, null);
	}

	/**
	 * Parses the text to object.
	 *
	 * @param <T>
	 *            the generic type
	 * @param text
	 *            the text
	 * @param clazz
	 *            the clazz
	 * @return the t
	 */
	protected static final <T> T parse(String text, Class<T> clazz) {
		return JSON.parseObject(text, clazz);
	}
}
