package com.yawnlon.kitchenkongfu;

import java.util.ArrayList;
import java.util.List;

import com.yawnlon.kitchenkongfu.highlevel.HighMainActivity;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;

public class LearnActivity extends PrePagerActivity {
	@Override
	protected List<View> initViews() {
		List<View> list = new ArrayList<View>();
		int firstPage = 0;
		if (LevelConfig.getCurrentLevel() == 1)
			firstPage = R.drawable.learn_level1_page1;
		else if (LevelConfig.getCurrentLevel() == 7)
			firstPage = R.drawable.learn_level7_page1;
		for (int i = 0; i < mPagerNum; i++) {
			ImageView imageView = new ImageView(mContext);
			imageView.setImageResource(firstPage + i);
			if (i == mPagerNum - 1) {
				imageView.setOnClickListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent = new Intent();
						if (LevelConfig.getCurrentLevel() == 1)
							intent.setClass(LearnActivity.this, MainActivity.class);
						if (LevelConfig.getCurrentLevel() == 7)
							intent.setClass(LearnActivity.this, HighMainActivity.class);
						startActivity(intent);
						overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
						finish();
					}
				});
			}
			list.add(imageView);
		}
		return list;
	}

	@Override
	protected int setPagerNum() {
		if (LevelConfig.getCurrentLevel() == 1)
			return 5;
		else if (LevelConfig.getCurrentLevel() == 7)
			return 2;
		return 0;
	}
}
