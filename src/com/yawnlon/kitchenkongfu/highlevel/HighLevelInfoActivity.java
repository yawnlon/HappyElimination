package com.yawnlon.kitchenkongfu.highlevel;

import com.yawnlon.kitchenkongfu.LearnActivity;
import com.yawnlon.kitchenkongfu.LevelConfig;
import com.yawnlon.kitchenkongfu.R;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Color;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.TextView;
import yawnlon.android.widget.YTtfHelper;

public class HighLevelInfoActivity extends Activity {
	private TextView target;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.level_info_high);
		((TextView) findViewById(R.id.level)).setText(String.format("LEVEL %d", LevelConfig.getCurrentLevel()));

		target = (TextView) findViewById(R.id.target);

		target.setText(HighLevelConfig.isIce() ? "HOT" : "COLD");
		target.setTextColor(HighLevelConfig.isIce() ? Color.rgb(255, 0, 0) : Color.rgb(83, 187, 222));
		((TextView) findViewById(R.id.do_what))
				.setText(HighLevelConfig.isIce() ? "you break all the ice!" : "you put out all the fire!");
		((ImageView) findViewById(R.id.goal))
				.setImageResource(HighLevelConfig.isIce() ? R.drawable.ice_single : R.drawable.fire_single);
		((ImageView) findViewById(R.id.goal)).setAdjustViewBounds(true);
		findViewById(R.id.go).setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				if (LevelConfig.getCurrentLevel() == 7)
					intent.setClass(HighLevelInfoActivity.this, LearnActivity.class);
				else
					intent.setClass(HighLevelInfoActivity.this, HighMainActivity.class);
				startActivity(intent);
				overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
				finish();
			}
		});
		YTtfHelper.applyFont(this, findViewById(R.id.dialog), "mainfont.ttf");

	}
}
