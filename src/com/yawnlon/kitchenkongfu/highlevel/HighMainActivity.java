package com.yawnlon.kitchenkongfu.highlevel;

import com.yawnlon.kitchenkongfu.MainActivity;
import com.yawnlon.kitchenkongfu.R;
import com.yawnlon.kitchenkongfu.view.LevelResultDialog;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import yawnlon.android.widget.YTtfHelper;
import yawnlon.android.widget.YTtfTextView;

public class HighMainActivity extends MainActivity {

	@Override
	protected void init() {
		setContentView(R.layout.main_high);
		context = this;
		mGLSurfaceView = (HighCrazyLinkGLSurfaceView) findViewById(R.id.game);

		time = (YTtfTextView) findViewById(R.id.time);
		score = (YTtfTextView) findViewById(R.id.score);
		target = (YTtfTextView) findViewById(R.id.target_score);
		YTtfHelper.applyFont(context, findViewById(R.id.score_board), "lewime.ttf");

		pause = (Button) findViewById(R.id.pause);
		pause.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				onPause();
				new AlertDialog.Builder(context).setCancelable(false).setTitle("暂停").setMessage("是否继续？")
						.setPositiveButton("确定", new DialogInterface.OnClickListener() {
					@Override
					public void onClick(DialogInterface dialog, int which) {
						onResume();
					}
				}).show();
			}
		});
		bGameOver = false;
	}

	@Override
	public void setTargetScore(int score) {
		if (!bGameOver) {
			this.target.setText(Integer.toString(score));
		}
	}

	@Override
	public void setMarkNum(int num) {
		if (!bGameOver) {
			if (num == 0) {
				// 达到目标！
				bGameOver = true;
				new LevelResultDialog(context).setScore(Integer.parseInt(this.score.getText().toString()))
						.setType(LevelResultDialog.SUCCESS).show();
			}
		}
	}

	public boolean useSpoonTool() {
		return HighControlCenter.useSpoonTool();
	}
}
