package org.me.score.app;

import java.io.IOException;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.app.AlertDialog;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.Toast;

public class OrderActivity extends Activity implements OnClickListener {

	private Button btnOrder;
	private String shoppingId;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_order);
		btnOrder = (Button) findViewById(R.id.btnOrder);
		shoppingId = "1";
	}

	@Override
	public void onClick(View arg0) {
		if (arg0 == btnOrder) {
			try {
				HttpGet httpRequest = new HttpGet(HttpUtil.getOrderUrl("wolf", shoppingId));
				// 取得HttpClient对象
				HttpClient httpclient = new DefaultHttpClient();
				// 请求HttpClient，取得HttpResponse
				HttpResponse httpResponse = httpclient.execute(httpRequest);
				// 请求成功
				if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
					// 取得返回的字符串
					String strResult = EntityUtils.toString(httpResponse.getEntity());
					Toast.makeText(this, strResult, Toast.LENGTH_LONG);
				} else {
					Toast.makeText(this, "请求错误!", Toast.LENGTH_LONG);
				}
			} catch (ClientProtocolException e) {
				Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG);
			} catch (IOException e) {
				Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG);
			} catch (Exception e) {
				Toast.makeText(this, e.getMessage(), Toast.LENGTH_LONG);
			}
		}
	}
}
