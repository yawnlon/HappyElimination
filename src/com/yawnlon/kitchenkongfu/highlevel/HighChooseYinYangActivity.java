package com.yawnlon.kitchenkongfu.highlevel;

import com.yawnlon.kitchenkongfu.R;

import android.app.Activity;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageView;
import android.widget.Toast;
import yawnlon.android.widget.YTtfHelper;

public class HighChooseYinYangActivity extends Activity implements OnClickListener {

	private ImageView yin, yang, next;
	private boolean selected = false;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.choose_yinyang);
		yin = (ImageView) findViewById(R.id.yin);
		yang = (ImageView) findViewById(R.id.yang);
		next = (ImageView) findViewById(R.id.next);
		YTtfHelper.applyFont(this, findViewById(R.id.text), "mainfont.ttf");
		yin.setAdjustViewBounds(true);
		yang.setAdjustViewBounds(true);
		clearSelected();
		yin.setOnClickListener(this);
		yang.setOnClickListener(this);
		next.setOnClickListener(this);

	}

	private void clearSelected() {
		yin.setImageResource(R.drawable.high_pre_page2_yin);
		yang.setImageResource(R.drawable.high_pre_page2_yang);
	}

	@Override
	public void onClick(View v) {
		switch (v.getId()) {
		case R.id.yin:
			clearSelected();
			yin.setImageResource(R.drawable.high_pre_page2_yin_selected);
			selected = true;
			HighPreActivity.type = HighPreActivity.TYPE_YIN;
			break;
		case R.id.yang:
			clearSelected();
			yang.setImageResource(R.drawable.high_pre_page2_yang_selected);
			selected = true;
			HighPreActivity.type = HighPreActivity.TYPE_YANG;
			break;
		case R.id.next:
			if (selected) {
				Intent intent = new Intent();
				intent.setClass(HighChooseYinYangActivity.this, HighPreActivity.class);
				intent.putExtra(HighPreActivity.CURRENT_PAGE, 3);
				startActivity(intent);
				overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
				finish();
			} else {
				Toast.makeText(v.getContext(), "Haven't chose a type!", Toast.LENGTH_SHORT).show();
			}
			break;

		default:
			break;
		}
	}
}
