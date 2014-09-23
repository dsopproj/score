package com.test;

import org.dragonscale.Direction;
import org.dragonscale.controls.app.BaseSlideActivity;

import android.content.Context;
import android.os.Bundle;

import com.test.controllers.LoginViewController;
import com.test.controllers.MainViewController;
import com.test.utils.Hit;

public class MainActivity extends BaseSlideActivity {

	private static MainActivity instance;
	public LoginViewController loginViewController;
	public MainViewController mainViewController;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		instance = this;
		super.onCreate(savedInstanceState);
		setLayout(R.layout.activity_main, R.id.rootLayout);
		setSlidingMenu(R.layout.slide_menu);
		setSlidingMenuEnabled(false);
		setSlideMenuWidth(0.5f);
		prepare();
		showLoginView();
	}

	/**
	 * Gets the single instance of MainActivity.
	 * 
	 * @return single instance of MainActivity
	 */
	public static MainActivity getInstance() {
		return instance;
	}

	/**
	 * Show the login view.
	 */
	public void showLoginView() {
		showLoginView(Direction.Down);
	}

	/**
	 * Show the login view.
	 * 
	 * @param direction
	 *            the direction
	 */
	public void showLoginView(Direction direction) {
		showView(loginViewController, direction);
	}

	/**
	 * Show the main view.
	 */
	public void showMainView() {
		next(mainViewController, Direction.Up);
	}

	private void prepare() {
		Context context = getBaseContext();
		loginViewController = new LoginViewController(context);
		mainViewController = new MainViewController(context);
		getActionBar().setHomeButtonEnabled(true);
		hit();
	}

	private void hit() {
		new Hit(getBaseContext()).start();
	}
}
