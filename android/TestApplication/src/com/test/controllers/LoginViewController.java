package com.test.controllers;

import java.util.ArrayList;

import android.content.Context;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageButton;

import com.test.BaseViewController;
import com.test.R;

/**
 * The Class LoginViewController.
 * 
 * @Author Aliqi
 * @Date 2014-6-5
 */
public final class LoginViewController extends BaseViewController implements
		OnClickListener {

	/** The Constant Id. */
	public static final int Id = R.layout.view_login;

	private long waitTime = 2000;
	private long touchTime = 0;
	private Button btnLogin;
	private ImageButton btnConfig;
	ArrayList<String> arr = new ArrayList<String>();

	/**
	 * Instantiates a new login view controller.
	 * 
	 * @param context
	 *            the context
	 */
	public LoginViewController(Context context) {
		super(context, Id);
		prepare();
		setEvents();
	}

	private void prepare() {
	}

	private void setEvents() {
		btnLogin = (Button) findViewById(R.id.btnLogin);
		btnLogin.setOnClickListener(this);
		btnConfig = (ImageButton) findViewById(R.id.btnConfig);
		btnConfig.setOnClickListener(this);
	}

	@Override
	protected void onLoaded() {
		// TODO Auto-generated method stub
	}

	@Override
	protected void onUnload() {
		// TODO Auto-generated method stub
	}

	@Override
	public void onClick(View v) {
		if (v == btnLogin)
			mainActivity.showMainView();
	}

	@Override
	protected boolean back() {

		long currentTime = System.currentTimeMillis();
		if ((currentTime - touchTime) >= waitTime) {
			toast("再按一次退出");
			touchTime = currentTime;
		} else {
			mainActivity.finish();
		}
		return true;
	}

}