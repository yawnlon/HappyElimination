package com.yawnlon.kitchenkongfu;

import android.app.Activity;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
import elong.CrazyLink.CrazyLinkGLSurfaceView;
import elong.CrazyLink.Core.ControlCenter;

public class MainActivity extends Activity {

	private CrazyLinkGLSurfaceView mGLSurfaceView;
	private TextView time, score;
	private Button refresh, pause;
	private Context context;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		context = this;
		mGLSurfaceView = (CrazyLinkGLSurfaceView) findViewById(R.id.game);

		time = (TextView) findViewById(R.id.time);
		score = (TextView) findViewById(R.id.score);

		refresh = (Button) findViewById(R.id.refresh);
		refresh.setOnClickListener(new OnClickListener() {
			@Override
			public void onClick(View v) {
				ControlCenter.refresh();
			}
		});

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
	}

	public void setTime(int time) {
		this.time.setText(time + "s");
	}

	public void setScore(int score) {
		this.score.setText(Integer.toString(score));
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
		if (ControlCenter.mTimer != null)
			ControlCenter.mTimer.resume();
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
