/*
 * Author:	Aliqi
 * Date:		2014-8-20	
 */
package org.me.models;

import java.util.Date;

import org.me.utils.Action;

/**
 * The Class Message.
 */
public class Message {

	/** The action. */
	public int action = Action.OK;

	/** The content. */
	public String content;

	/** The sender. */
	public String sender;

	/** The tag. */
	public String tag;

	/** The time. */
	public long time;

	/**
	 * Instantiates a new message.
	 */
	public Message() {
		time = new Date().getTime();
	}

	/**
	 * Instantiates a new message.
	 *
	 * @param sender
	 *            the sender
	 */
	public Message(String sender) {
		this();
		this.sender = sender;
	}

	/**
	 * Instantiates a new message.
	 *
	 * @param sender
	 *            the sender
	 * @param content
	 *            the content
	 */
	public Message(String sender, String content) {
		this(sender);
		this.content = content;
	}

	/**
	 * Instantiates a new message.
	 *
	 * @param sender
	 *            the sender
	 * @param action
	 *            the action
	 */
	public Message(String sender, int action) {
		this(sender);
		this.action = action;
	}

	/**
	 * Instantiates a new message.
	 *
	 * @param sender
	 *            the sender
	 * @param action
	 *            the action
	 * @param content
	 *            the content
	 */
	public Message(String sender, int action, String content) {
		this(sender);
		this.action = action;
		this.content = content;
	}
}
