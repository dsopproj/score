package org.me.score.app;

import java.io.IOException;
import java.util.ArrayList;
import java.util.HashMap;

import org.apache.http.HttpResponse;
import org.apache.http.HttpStatus;
import org.apache.http.client.ClientProtocolException;
import org.apache.http.client.HttpClient;
import org.apache.http.client.methods.HttpGet;
import org.apache.http.impl.client.DefaultHttpClient;
import org.apache.http.util.EntityUtils;
import org.json.JSONArray;
import org.json.JSONObject;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.util.JsonReader;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ListView;
import android.widget.SimpleAdapter;
import android.widget.Toast;
import android.widget.AdapterView.OnItemClickListener;

public class ProductListActivity extends Activity implements OnItemClickListener {

	private ListView listViewAdvertise;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_product_list);
		listViewAdvertise = (ListView) findViewById(R.id.listViewAdvertise);
	}

	@Override
	protected void onStart() {
		super.onStart();

		try {
			HttpGet httpRequest = new HttpGet(HttpUtil.getProductsUrl("wolf"));
			// 取得HttpClient对象
			HttpClient httpclient = new DefaultHttpClient();
			// 请求HttpClient，取得HttpResponse
			HttpResponse httpResponse = httpclient.execute(httpRequest);
			// 请求成功
			if (httpResponse.getStatusLine().getStatusCode() == HttpStatus.SC_OK) {
				// 取得返回的字符串
				String strResult = EntityUtils.toString(httpResponse.getEntity());
				Toast.makeText(this, strResult, Toast.LENGTH_LONG);
				JSONArray ja = new JSONArray(strResult);

				ArrayList<HashMap<String, Object>> mylist = new ArrayList<HashMap<String, Object>>();
				for (int i = 0; i < ja.length(); i++) {
					JSONObject jo = ja.getJSONObject(i);
					HashMap<String, Object> map = new HashMap<String, Object>();
					map.put("icon", (R.drawable.viewtime));
					map.put("ItemTitle", jo.getString("title"));
					map.put("ItemText", jo.getString("id"));
					mylist.add(map);
				}
				listViewAdvertise.setAdapter(initAdapter(mylist)); // 这里我们调用initAdapter()函数源码如下
				listViewAdvertise.setOnItemClickListener(this);
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

	public SimpleAdapter initAdapter(ArrayList<HashMap<String, Object>> mylist) {
		SimpleAdapter mSchedule = new SimpleAdapter(this, // 没什么解释
				mylist,// 数据来源
				R.layout.activity_product_list,// ListItem的XML实现
				new String[] { "icon", "ItemTitle", "ItemText" }, new int[] { R.id.ItemImage, R.id.ItemTitle, R.id.ItemText });
		// 添加并且显示
		return mSchedule;
	}

	@Override
	public void onItemClick(AdapterView<?> arg0, View arg1, int arg2, long arg3) {
		Intent intent = new Intent(this, ProductListActivity.class);
		startActivity(intent);
	}

}
