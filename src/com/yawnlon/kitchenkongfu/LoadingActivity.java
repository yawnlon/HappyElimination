package com.yawnlon.kitchenkongfu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;

public class LoadingActivity extends Activity {
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loading);

		new Thread() {
			public void run() {
				try {
					Thread.sleep(2000);
					Intent intent = new Intent();
					intent.setClass(LoadingActivity.this, PrePagerActivity.class);
					startActivity(intent);
					overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
					finish();
				} catch (InterruptedException e) {
					e.printStackTrace();
				}
			}
		}.start();
		Intent intent = new Intent("com.angel.Android.MUSIC");
		startService(intent);
	}

	@Override
	public void finish() {
		super.finish();
	}
}
