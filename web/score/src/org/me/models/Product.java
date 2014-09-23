package org.me.models;

import java.math.BigDecimal;

public class Product {

	public String id;
	public String username;
	public String title;
	public String imagePath;
	public String content;
	public BigDecimal cash;
	public String qrCode;

	public static Product generateProduct(String a) {
		if (a == null)
			a = "a";
		Product po = new Product();
		po.title = "product " + a;
		po.imagePath = "image " + a + " path";
		po.content = "content " + a;
		po.cash = new BigDecimal(100);
		po.qrCode = "qrCode " + a;
		return po;
	}

}
