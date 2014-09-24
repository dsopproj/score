package org.me.score.app;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.EditText;

public class MainActivity extends Activity implements OnClickListener {

	private Button btnProdList;
	private EditText editTextUrl;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		btnProdList = (Button) this.findViewById(R.id.btnProductList);
		btnProdList.setOnClickListener(this);
		editTextUrl = (EditText) findViewById(R.id.editTextUrl);
		editTextUrl.setText(HttpUtil.getUrl());
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public void onClick(View v) {
		if (v == btnProdList) {
			Intent intent = new Intent(this, ProductListActivity.class);
			startActivity(intent);
		}

	}

	@Override
	protected void onPause() {
		super.onPause();
		HttpUtil.setUrl(editTextUrl.getText().toString());
	}

}
