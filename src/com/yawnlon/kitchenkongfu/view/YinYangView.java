package com.yawnlon.kitchenkongfu.view;

import com.yawnlon.kitchenkongfu.LevelConfig;
import com.yawnlon.kitchenkongfu.R;

import android.content.Context;
import android.util.AttributeSet;
import android.widget.ImageView;
//import android.widget.RelativeLayout.LayoutParams;

public class YinYangView extends ImageView {
	private boolean isYin;
	private int totalTargetScore;
	private int currentLevel = 0;

	private static final int YIN_RESID[] = { R.drawable.yin0, R.drawable.yin1, R.drawable.yin2, R.drawable.yin3,
			R.drawable.yin4, R.drawable.yin5, R.drawable.yin6, R.drawable.yin7, R.drawable.yin8, R.drawable.yin9 };
	private static final int YANG_RESID[] = { R.drawable.yang0, R.drawable.yang1, R.drawable.yang2, R.drawable.yang3,
			R.drawable.yang4, R.drawable.yang5, R.drawable.yang6, R.drawable.yang7, R.drawable.yang8,
			R.drawable.yang9 };

	public YinYangView(Context context) {
		this(context, null);
	}

	public YinYangView(Context context, AttributeSet attrs) {
		super(context, attrs);
		isYin = LevelConfig.isCold();
		totalTargetScore = LevelConfig.getTotalTargetScore();
		setCurrentImage();
	}

	public boolean setScore(int score) {
		int ratio = score * 10 / totalTargetScore;
		if (ratio < 10) {
			if (ratio > currentLevel) {
				currentLevel = ratio;
				setCurrentImage();
			}
			return false;
		} else {
			setImageResource(R.drawable.yin_yang);
			return true;
		}
	}

	private void setCurrentImage() {
		if (isYin) {
			setImageResource(YIN_RESID[currentLevel]);
		} else {
			setImageResource(YANG_RESID[currentLevel]);
		}
	}

}
