package org.me.score.app;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;

import android.app.Activity;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.util.Log;
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
		shoppingId = getIntent().getStringExtra("ItemText");
		btnOrder = (Button) findViewById(R.id.btnOrder);
		btnOrder.setOnClickListener(this);
	}

	Handler handler = new Handler() {
		public void handleMessage(android.os.Message msg) {
			try {
				String strResult = msg.obj.toString();

				Toast.makeText(OrderActivity.this, strResult, Toast.LENGTH_LONG)
						.show();
			} catch (Exception e) {
				Toast.makeText(OrderActivity.this, e.getMessage(),
						Toast.LENGTH_LONG).show();
			}
		};
	};

	@Override
	public void onClick(View arg0) {
		if (arg0 == btnOrder) {

			Thread thread = new Thread(new Runnable() {

				@Override
				public void run() {
					try {
						HttpGet httpRequest = new HttpGet(HttpUtil.getOrderUrl(
								"wolf", shoppingId));
						// 取得HttpClient对象
						HttpClient httpclient = new DefaultHttpClient();
						// 请求HttpClient，取得HttpResponse
						HttpResponse httpResponse = httpclient
								.execute(httpRequest);
						// 请求成功
						if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
							// 取得返回的字符串
							String strResult = EntityUtils
									.toString(httpResponse.getEntity());
							Message msg = new Message();
							msg.obj = strResult;
							handler.sendMessage(msg);
						} else {
							Log.e("score", "请求错误!");
							Toast.makeText(OrderActivity.this, "请求错误!",
									Toast.LENGTH_LONG).show();
						}

					} catch (Exception e) {
						e.printStackTrace();
						Toast.makeText(OrderActivity.this, e.getMessage(),
								Toast.LENGTH_LONG).show();
					}

				}
			});

			thread.start();
		}
	}
}
