package com.yawnlon.kitchenkongfu.view;

import com.yawnlon.kitchenkongfu.LevelConfig;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class HintToolView extends LinearLayout {

	public HintToolView(Context context) {
		this(context, null);
	}

	public HintToolView(Context context, AttributeSet attrs) {
		super(context, attrs);
		setOrientation(HORIZONTAL);
		String targetFood = LevelConfig.getTargetFood();
		for (int i = 0; i < targetFood.length(); i++) {
			ImageView taget = new ImageView(context);
			taget.setImageResource(LevelConfig.getFood(targetFood.charAt(i) - '0'));
			LayoutParams layoutParams = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.MATCH_PARENT);
			taget.setLayoutParams(layoutParams);
			taget.setAdjustViewBounds(true);
			addView(taget);
		}
	}

	public int getHintNum() {
		return LevelConfig.getTargetFood().length();
	}
}
