package com.test;

import org.dragonscale.controls.SlidingMenu;

import android.content.Context;

public class SlideMenuController extends BaseViewController {

	protected final SlidingMenu slide;

	protected SlideMenuController(Context context, int layout) {
		super(context, layout);
		slide = mainActivity.getSlidingMenu();
	}

	@Override
	protected boolean home() {
		slide.toggle();
		return true;
	}

	@Override
	protected boolean back() {
		if (slide.isMenuShowing()) {
			slide.toggle();
			return true;
		}
		return super.back();
	}

}
