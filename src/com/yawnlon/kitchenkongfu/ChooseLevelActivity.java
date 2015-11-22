package com.yawnlon.kitchenkongfu;

import com.yawnlon.kitchenkongfu.highlevel.HighLevelInfoActivity;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.LinearLayout;

public class ChooseLevelActivity extends Activity {

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		LinearLayout root = new LinearLayout(this);
		root.setOrientation(LinearLayout.VERTICAL);
		root.setBackgroundResource(R.drawable.background_pre);
		for (int i = 1; i <= LevelConfig.LEVEL_NUM; i++) {
			Button button = new Button(this);
			button.setText("LEVEL " + i);
			final int level = i;
			button.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					LevelConfig.setCurrentLevel(level);
					Intent intent = new Intent();
					if (level <= 6) {
						intent.setClass(ChooseLevelActivity.this, LevelInfoActivity.class);
					} else {
						intent.setClass(ChooseLevelActivity.this, HighLevelInfoActivity.class);
					}
					startActivity(intent);
					overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
					finish();
				}
			});
			root.addView(button);
		}
		setContentView(root);
	}

}
