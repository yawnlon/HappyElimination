package com.yawnlon.kitchenkongfu;

import com.yawnlon.kitchenkongfu.view.LevelResultDialog;
import com.yawnlon.kitchenkongfu.view.YinYangView;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import elong.CrazyLink.CrazyLinkGLSurfaceView;
import elong.CrazyLink.Core.ControlCenter;
import yawnlon.android.widget.YTtfTextView;
import yawnlon.android.widget.YTtfHelper;

public class MainActivity extends Activity {

	private CrazyLinkGLSurfaceView mGLSurfaceView;
	private YTtfTextView time, score, target;
	private Button pause;
	private Context context;
	private YinYangView mYYView;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		context = this;
		mGLSurfaceView = (CrazyLinkGLSurfaceView) findViewById(R.id.game);

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

		mYYView = (YinYangView) findViewById(R.id.yin_yang);
	}

	public void setTime(int time) {
		this.time.setText(time + "s");
		if (time <= 0) {
			// TODO fail
			new LevelResultDialog(context).setScore(Integer.parseInt(score.getText().toString()))
					.setType(LevelResultDialog.FAIL).show();
		}
	}

	public void setScore(int score) {
		this.score.setText(Integer.toString(score));
	}

	public void setTargetScore(int score) {
		this.target.setText(Integer.toString(score));
		if (mYYView.setScore(score)) {
			// TODO: 达到目标！
			new LevelResultDialog(context).setScore(Integer.parseInt(this.score.getText().toString()))
					.setType(LevelResultDialog.SUCCESS).show();
		}
	}

	public void addTime(int time) {
		ControlCenter.mTimer.addTime(time);
	}

	@Override
	protected void onStart() {
		// TODO Auto-generated method stub
		super.onStart();

	}

	@Override
	protected void onStop() {
		// TODO Auto-generated method stub
		if (ControlCenter.mTimer != null)
			ControlCenter.mTimer.pause();
		super.onStop();
	}

	@Override
	protected void onResume() {
		// TODO Auto-generated method stub
		super.onResume();
		if (mGLSurfaceView != null)
			mGLSurfaceView.onResume();
		if (ControlCenter.mTimer != null) {
			ControlCenter.mTimer.resume();
			setTime(ControlCenter.mTimer.getLeftTime());
		}
		if (ControlCenter.mScore != null)
			setTargetScore(ControlCenter.mScore.getScore());
	}

	@Override
	protected void onPause() {
		// TODO Auto-generated method stub
		super.onPause();
		if (mGLSurfaceView != null)
			mGLSurfaceView.onPause();
		if (ControlCenter.mTimer != null)
			ControlCenter.mTimer.pause();
	}

	@Override
	public void onDestroy() {
		// TODO Auto-generated method stub
		super.onDestroy();
	}

}
