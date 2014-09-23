/*
 * Author:	Aliqi
 * Date:		2014-8-5	
 */
package org.me.utils;

import java.util.Collection;

/**
 * The Class CollectionUtils.
 */
public final class CollectionUtils {

	/**
	 * Checks if is empty.
	 *
	 * @param value
	 *            the value
	 * @return true, if is empty
	 */
	public static boolean isEmpty(Collection<?> value) {
		if (value == null)
			return true;
		return value.isEmpty();
	}

	/**
	 * Checks if is empty.
	 *
	 * @param <T>
	 *            the generic type
	 * @param value
	 *            the value
	 * @return true, if is empty
	 */
	public static <T> boolean isEmpty(T[] value) {
		if (value == null)
			return true;
		return value.length == 0;
	}
}
