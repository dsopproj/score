package com.test.utils;

import java.util.ArrayList;
import java.util.List;

import org.apache.http.NameValuePair;
import org.apache.http.client.HttpClient;
import org.apache.http.client.entity.UrlEncodedFormEntity;
import org.apache.http.client.methods.HttpPost;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.message.BasicNameValuePair;
import org.apache.http.protocol.HTTP;

import android.content.Context;

import com.alibaba.fastjson.JSON;

public class Hit extends Thread {

	private String id;
	private HttpClient client = new DefaultHttpClient();

	public Hit(Context ctx) {
		this.id = Installation.id(ctx);
	}

	@Override
	public void run() {
		try {
			HttpPost post = new HttpPost(
					"http://192.168.8.199:8080/best/connect");
			Message msg = new Message(id);
			List<NameValuePair> params = new ArrayList<NameValuePair>();
			params.add(new BasicNameValuePair("c", JSON.toJSONString(msg)));
			post.setEntity(new UrlEncodedFormEntity(params, HTTP.UTF_8));
			client.execute(post);
			client.getConnectionManager().shutdown();
		} catch (Exception e) {
			e.printStackTrace();
		}
	}
}
