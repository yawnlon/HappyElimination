package com.yawnlon.kitchenkongfu;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class AnswerActivity extends Activity {

	private ImageView food, cold, neutral, hot;
	private int currentFood = 1;
	private final static int[] answers = { 1, 1, 3, 2, 3, 3, 2, 2, 1 };
	private int rightNum = 0;

	public final static String RIGHT_NUM = "right_num";

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.answer);

		food = (ImageView) findViewById(R.id.food);
		cold = (ImageView) findViewById(R.id.cold);
		neutral = (ImageView) findViewById(R.id.neutral);
		hot = (ImageView) findViewById(R.id.hot);

		food.setImageResource(LevelConfig.getFood(currentFood));
		cold.setOnClickListener(listener);
		neutral.setOnClickListener(listener);
		hot.setOnClickListener(listener);
	}

	private OnClickListener listener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			switch (v.getId()) {
			case R.id.cold:
				if (answers[currentFood - 1] == 1)
					rightNum++;
				break;
			case R.id.neutral:
				if (answers[currentFood - 1] == 2)
					rightNum++;
				break;
			case R.id.hot:
				if (answers[currentFood - 1] == 3)
					rightNum++;
				break;
			default:
				break;
			}
			if (currentFood < 9) {
				currentFood++;
				food.setImageResource(LevelConfig.getFood(currentFood));
			} else {
				// 跳转到AnswerResultActivity
				Intent intent = new Intent();
				intent.setClass(AnswerActivity.this, AnswerResultActivity.class);
				intent.putExtra(RIGHT_NUM, rightNum);
				startActivity(intent);
				overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
				finish();
			}
		}
	};
}
