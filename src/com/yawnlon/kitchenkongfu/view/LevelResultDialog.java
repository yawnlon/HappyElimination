package com.yawnlon.kitchenkongfu.view;

import com.yawnlon.kitchenkongfu.LevelConfig;
import com.yawnlon.kitchenkongfu.LevelInfoActivity;
import com.yawnlon.kitchenkongfu.R;
import com.yawnlon.kitchenkongfu.highlevel.HighLevelInfoActivity;
import com.yawnlon.kitchenkongfu.highlevel.HighPreActivity;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import yawnlon.android.widget.YTtfHelper;
import yawnlon.android.util.DensityUtil;

public class LevelResultDialog {

	public final static int SUCCESS = 0;
	public final static int FAIL = 1;

	private TextView score;
	private LinearLayout bonusLayout;
	private ImageView bonus;
	private FrameLayout root;
	private ImageView btn;
	private Activity context;
	private AlertDialog alertDialog;

	private View view;

	@SuppressLint("InflateParams")
	public LevelResultDialog(Context context) {
		this.context = (Activity) context;
		view = LayoutInflater.from(context).inflate(R.layout.level_result, null);
		score = (TextView) view.findViewById(R.id.score);
		bonusLayout = (LinearLayout) view.findViewById(R.id.bonus_layout);
		bonus = (ImageView) view.findViewById(R.id.bonus);
		bonus.setAdjustViewBounds(true);
		root = (FrameLayout) view.findViewById(R.id.back);
		YTtfHelper.applyFont(context, root, "mainfont.ttf");
		btn = (ImageView) view.findViewById(R.id.btn);
		btn.setAdjustViewBounds(true);

		alertDialog = new AlertDialog.Builder(context).create();
		alertDialog.setCancelable(false);

	}

	public LevelResultDialog setScore(int score) {
		this.score.setText(String.format("Score: %d", score));
		return this;
	}

	public LevelResultDialog setType(int type) {
		if (type == SUCCESS) {
			root.setBackgroundResource(R.drawable.level_result_back_suc);
			bonusLayout.setVisibility(View.VISIBLE);
			btn.setImageResource(R.drawable.level_result_btn_suc);
			btn.setOnClickListener(new View.OnClickListener() {
				@Override
				public void onClick(View v) {
					LevelConfig.setCurrentLevel(LevelConfig.getCurrentLevel() + 1);
					newGame();
				}
			});
		} else {
			root.setBackgroundResource(R.drawable.level_result_back_fail);
			bonusLayout.setVisibility(View.GONE);
			btn.setImageResource(R.drawable.level_result_btn_fail);
			btn.setOnClickListener(new View.OnClickListener() {

				@Override
				public void onClick(View v) {
					newGame();
				}
			});
		}
		return this;
	}

	private void newGame() {
		Intent intent = new Intent();
		if (LevelConfig.getCurrentLevel() <= 6)
			intent.setClass(context, LevelInfoActivity.class);
		else if (LevelConfig.getCurrentLevel() == 7)
			intent.setClass(context, HighPreActivity.class);
		else
			intent.setClass(context, HighLevelInfoActivity.class);
		context.startActivity(intent);
		context.overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
		context.finish();
	}

	public void show() {
		alertDialog.show();
		alertDialog.getWindow().setLayout(DensityUtil.dp2px(context, 350), DensityUtil.dp2px(context, 170));
		alertDialog.setContentView(view);
	}

	public void dismiss() {
		alertDialog.dismiss();
	}

}
