package com.yawnlon.kitchenkongfu;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;
import elong.CrazyLink.CrazyLinkGLSurfaceView;

public class MainActivity extends Activity {

	private CrazyLinkGLSurfaceView mGameSurfaceView;
	private TextView time, score;

	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.main);
		mGameSurfaceView = (CrazyLinkGLSurfaceView) findViewById(R.id.game);
		
		time = (TextView) findViewById(R.id.time);
		score = (TextView) findViewById(R.id.score);
	}
	
	public void setTime(int time) {
		this.time.setText(time + "s");
	}
	
	public void setScore(int score) {
		this.score.setText(Integer.toString(score));
	}
	
}
