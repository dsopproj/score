/*
 * Author:	Aliqi
 * Date:		2014-8-20	
 */
package com.test.utils;

/**
 * The Class Message.
 */
public class Message {

	/** The content. */
	public String content;

	/** The sender. */
	public String sender;

	/** The tag. */
	public String tag;

	/** The platform. */
	public int platform = 4;

	/**
	 * Instantiates a new message.
	 */
	public Message() {
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
}
