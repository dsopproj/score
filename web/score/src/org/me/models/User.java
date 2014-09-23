/*
 * Author:	Aliqi
 * Date:		2014-9-3	
 */
package org.me.models;

import javax.servlet.http.HttpSession;

import org.me.utils.Const;

/**
 * The Class User.
 */
public final class User {

	private HttpSession http;
	
//	public String recno;
	
	public String username;
	
	public String usernick;
	
	public String password;
	
	public String role;
	
	/**
	 * Sets the http session.
	 *
	 * @param session
	 *            the new http session
	 */
	public void setHttpSession(HttpSession session) {
		if (http == null) {
			if (session != null) {
				session.setAttribute(Const.User, this);
			}
			http = session;
		} else
			throw new RuntimeException(Const.HttpSessionExist);
	}

	
	public User(){
		
	}
}
