package org.me.score.app;

import java.net.URI;
import java.net.URISyntaxException;

public class HttpUtil {
	private static String baseUrl = "http://192.168.22.101:8086/score";

	public static void setUrl(String baseUrl) {
		HttpUtil.baseUrl = baseUrl;
	}

	public static String getUrl() {
		return HttpUtil.baseUrl;
	}

	public static URI getOrderUrl(String username, String shoppingId) throws URISyntaxException {
		return new URI(HttpUtil.baseUrl + "/ShoppingOrder/droporder?username=" + username + "&shoppingid=" + shoppingId);
	}

	public static URI getProductsUrl(String username) throws URISyntaxException {
		return new URI(HttpUtil.baseUrl + "/products?username=" + username);
	}

}
