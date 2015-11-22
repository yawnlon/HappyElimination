package com.yawnlon.kitchenkongfu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnLongClickListener;

public class LoadingActivity extends Activity {

	private boolean toChoose;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.loading);
		toChoose = false;
		findViewById(R.id.loading).setOnLongClickListener(new OnLongClickListener() {
			@Override
			public boolean onLongClick(View v) {
				toChoose = true;
				Intent intent = new Intent();
				intent.setClass(LoadingActivity.this, ChooseLevelActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
				finish();
				return false;
			}
		});
		new Thread() {
			public void run() {
				try {
					Thread.sleep(2000);
					if (!toChoose) {
						Intent intent = new Intent();
						intent.setClass(LoadingActivity.this, PrePagerActivity.class);
						startActivity(intent);
						overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
						finish();
					}
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
