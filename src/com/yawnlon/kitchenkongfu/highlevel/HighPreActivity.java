package com.yawnlon.kitchenkongfu.highlevel;

import com.yawnlon.kitchenkongfu.PreActivity;
import com.yawnlon.kitchenkongfu.R;

import android.content.Intent;
import android.view.View;
import android.view.View.OnClickListener;

public class HighPreActivity extends PreActivity {

	public static String type;
	public static final String TYPE_YIN = "yin";
	public static final String TYPE_YANG = "yang";

	@Override
	protected void init() {
		next.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				Intent intent = new Intent();
				if (currentPage == 1) {
					intent.setClass(HighPreActivity.this, HighChooseYinYangActivity.class);
				} else if (currentPage < 5) {
					intent.setClass(HighPreActivity.this, HighPreActivity.class);
					intent.putExtra(CURRENT_PAGE, currentPage + 1);
				} else {
					intent.setClass(HighPreActivity.this, HighLevelInfoActivity.class);
				}
				startActivity(intent);
				overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
				finish();
			}
		});
	}

	@Override
	protected int getDialogId() {
		int result = 0;
		switch (currentPage) {
		case 1:
			result = R.drawable.high_pre_page1;
			break;
		case 3:
			if (type.equals(TYPE_YIN)) {
				result = R.drawable.high_pre_page3_yin;
			} else if (type.equals(TYPE_YANG)) {
				result = R.drawable.high_pre_page3_yang;
			}
			break;
		case 4:
			if (type.equals(TYPE_YIN)) {
				result = R.drawable.high_pre_page4_yin;
			} else if (type.equals(TYPE_YANG)) {
				result = R.drawable.high_pre_page4_yang;
			}
			break;
		case 5:
			result = R.drawable.high_pre_page5;
			break;
		default:
			break;
		}
		return result;
	}
}
