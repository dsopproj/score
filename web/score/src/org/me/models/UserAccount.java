package org.me.models;

import java.math.BigDecimal;

import com.alibaba.fastjson.JSON;

public class UserAccount {

	public String username;
	public int score;
	public BigDecimal balance;
	public String datetime;
	public String source;
	public int frozenScore;
	public String inorexp;

	@Override
	public String toString() {
		return JSON.toJSONString(this);
	}
}
