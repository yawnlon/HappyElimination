package com.yawnlon.kitchenkongfu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class PreActivity extends Activity {

	private int currentPage;
	private final static String CURRENT_PAGE = "current_page";
	private ImageView dialog, next;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.pre);
		dialog = (ImageView) findViewById(R.id.dialog);
		next = (ImageView) findViewById(R.id.next);
		currentPage = getIntent().getIntExtra(CURRENT_PAGE, 1);
		dialog.setImageResource(getDialogId());
		dialog.setAdjustViewBounds(true);
		next.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				if (currentPage < 4) {
					intent.setClass(PreActivity.this, PreActivity.class);
					intent.putExtra(CURRENT_PAGE, currentPage + 1);
				} else {
					intent.setClass(PreActivity.this, AnswerActivity.class);
				}
				startActivity(intent);
				overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
				finish();
			}
		});
	}

	private int getDialogId() {
		int result;
		switch (currentPage) {
		case 1:
			result = R.drawable.pre_page1;
			break;
		case 2:
			result = R.drawable.pre_page2;
			break;
		case 3:
			result = R.drawable.pre_page3;
			break;
		case 4:
			result = R.drawable.pre_page4;
			break;
		default:
			result = -1;
			break;
		}
		return result;
	}
}
