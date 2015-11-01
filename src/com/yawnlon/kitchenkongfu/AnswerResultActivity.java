package com.yawnlon.kitchenkongfu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import yawnlon.android.widget.YTtfHelper;

public class AnswerResultActivity extends Activity {

	private TextView result;
	private ImageView next;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.answer_result);
		result = (TextView) findViewById(R.id.result);
		result.setText(String.format("%d/9", getIntent().getIntExtra(AnswerActivity.RIGHT_NUM, 0)));
		next = (ImageView) findViewById(R.id.next);
		next.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(AnswerResultActivity.this, LevelInfoActivity.class);
				startActivity(intent);
				finish();
			}
		});
		YTtfHelper.applyFont(this, findViewById(R.id.dialog), "mainfont.ttf");
	}
}
