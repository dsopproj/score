package com.test.controllers;

import android.content.Context;

import com.test.R;
import com.test.SlideMenuController;

public final class MainViewController extends SlideMenuController {

	/** The Constant Id. */
	public static final int Id = R.layout.view_main;

	/**
	 * Instantiates a new main view controller.
	 * 
	 * @param context
	 *            the context
	 */
	public MainViewController(Context context) {
		super(context, Id);
		prepare();
	}

	private void prepare() {
	}

	@Override
	protected void onLoaded() {
		slide.setSlidingEnabled(true);
	}

	@Override
	protected void onUnload() {
	}
}
