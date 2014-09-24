package org.me.score.app;

import java.util.ArrayList;
import java.util.HashMap;
import java.util.Iterator;
import java.util.Map;
import java.util.Map.Entry;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.os.Message;
import android.view.View;
import android.widget.AdapterView;
import android.widget.AdapterView.OnItemClickListener;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;

public class ProductListActivity extends Activity implements
		OnItemClickListener {

	private ListView listViewAdvertise;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product_list);
		listViewAdvertise = (ListView) findViewById(R.id.listViewAdvertise);
		listViewAdvertise.setOnItemClickListener(ProductListActivity.this);
	}

	Handler getListHandler = new Handler() {
		public void handleMessage(android.os.Message msg) {

			String strResult = (String) msg.obj;
			Toast.makeText(ProductListActivity.this, strResult,
					Toast.LENGTH_LONG).show();
			JSONArray ja;
			try {
				ja = new JSONArray(strResult);

				ArrayList<HashMap<String, Object>> mylist = new ArrayList<HashMap<String, Object>>();
				for (int i = 0; i < ja.length(); i++) {
					JSONObject jo = ja.getJSONObject(i);
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("icon", (R.drawable.viewtime));
					map.put("ItemTitle", jo.getString("title"));
					map.put("ItemText", jo.getString("id"));
					mylist.add(map);
				}
				SimpleAdapter adapter = initAdapter(mylist);
				adapter.notifyDataSetChanged();
				listViewAdvertise.setAdapter(adapter); // 这里我们调用initAdapter()函数源码如下
			} catch (Exception e) {
				e.printStackTrace();
			}
		};
	};

	@Override
	protected void onStart() {
		super.onStart();

		Thread thread = new Thread(new Runnable() {

			@Override
			public void run() {

				try {
					HttpGet httpRequest = new HttpGet(HttpUtil
							.getProductsUrl("wolf"));
					// 取得HttpClient对象
					HttpClient httpclient = new DefaultHttpClient();
					// 请求HttpClient，取得HttpResponse
					HttpResponse httpResponse = httpclient.execute(httpRequest);
					// 请求成功
					if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
						// 取得返回的字符串
						String strResult = EntityUtils.toString(httpResponse
								.getEntity());
						Message msg = new Message();
						msg.obj = strResult;
						getListHandler.sendMessage(msg);
					} else {
						System.out.println("请求错误");
						Toast.makeText(ProductListActivity.this, "请求错误!",
								Toast.LENGTH_LONG).show();
					}
				} catch (Exception e) {
					e.printStackTrace();
					Toast.makeText(ProductListActivity.this, e.getMessage(),
							Toast.LENGTH_LONG).show();
				}
			}
		});
		thread.start();

	}

	public SimpleAdapter initAdapter(ArrayList<HashMap<String, Object>> mylist) {
		SimpleAdapter mSchedule = new SimpleAdapter(this, // 没什么解释
				mylist,// 数据来源
				R.layout.listitem_advertise,// ListItem的XML实现
				new String[] { "icon", "ItemTitle", "ItemText" }, new int[] {
						R.id.ItemImage, R.id.ItemTitle, R.id.ItemText });
		// 添加并且显示
		return mSchedule;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		HashMap<String, String> map = ((HashMap) ((SimpleAdapter) arg0
				.getAdapter()).getItem(arg2));
		Intent intent = new Intent(this, OrderActivity.class);
		intent.putExtra("ItemText", map.get("ItemText"));
		// Bundle b = new Bundle();
		// b.putString("ItemText", );
		startActivity(intent);
	}
}
