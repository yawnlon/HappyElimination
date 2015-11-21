package com.yawnlon.kitchenkongfu;

import com.yawnlon.kitchenkongfu.view.HintToolView;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import yawnlon.android.widget.YTtfHelper;

public class LevelInfoActivity extends Activity {

	private TextView target, goal;
	private ImageView yinYang;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.level_info);
		findViewById(R.id.have_fun).setVisibility(LevelConfig.getCurrentLevel() == 1 ? View.VISIBLE : View.INVISIBLE);
		((TextView) findViewById(R.id.level)).setText(String.format("LEVEL %d", LevelConfig.getCurrentLevel()));

		target = (TextView) findViewById(R.id.target);
		goal = (TextView) findViewById(R.id.goal);
		yinYang = (ImageView) findViewById(R.id.yin_yang);

		target.setText(LevelConfig.isCold() ? "COLD" : "HOT");
		target.setTextColor(LevelConfig.isCold() ? Color.rgb(83, 187, 222) : Color.rgb(255, 0, 0));
		goal.setText(LevelConfig.isCold() ? "\"Yin\"" : "\"Yang\"");
		yinYang.setImageResource(LevelConfig.isCold() ? R.drawable.level_info_cold : R.drawable.level_info_hot);

		findViewById(R.id.go).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				intent.setClass(LevelInfoActivity.this, MainActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
				finish();
			}
		});
		YTtfHelper.applyFont(this, findViewById(R.id.dialog), "mainfont.ttf");

		((LinearLayout) findViewById(R.id.hint)).addView(new HintToolView(this));
	}

}
