/*
 * Author:	Aliqi
 * Date:		2014-8-22	
 */
package org.me.utils;

import java.util.UUID;

/**
 * The Class StringUtils.
 */
public final class StringUtils {

	/**
	 * Checks if is empty.
	 *
	 * @param value
	 *            the value
	 * @return true, if is empty
	 */
	public static boolean isEmpty(String value) {
		if (value == null)
			return true;
		return value.trim().equals("");
	}

	/**
	 * Uuid.
	 *
	 * @return the string
	 */
	public static String uuid() {
		return UUID.randomUUID().toString();
	}
}
