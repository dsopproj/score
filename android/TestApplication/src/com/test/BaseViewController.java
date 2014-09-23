package com.test;

import org.dragonscale.ViewController;

import android.content.Context;
import android.view.KeyEvent;
import android.view.MenuItem;

/**
 * @Author Aliqi
 * @Date 2014-6-9
 **/
public class BaseViewController extends ViewController {

	protected final MainActivity mainActivity;

	protected BaseViewController(Context context, int layout) {
		super(context, layout);
		mainActivity = MainActivity.getInstance();
	}

	protected boolean back() {
		mainActivity.back();
		return true;
	}

	protected boolean home() {
		return false;
	}

	@Override
	protected boolean onOptionsItemSelected(MenuItem item) {
		switch (item.getItemId()) {
		case android.R.id.home:
			return home();
		default:
			break;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	protected boolean onKeyDown(int keyCode, KeyEvent event) {
		if (keyCode == KeyEvent.KEYCODE_BACK)
			return back();
		return false;
	}
}
