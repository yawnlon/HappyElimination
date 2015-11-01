package com.yawnlon.kitchenkongfu.view;

import com.yawnlon.kitchenkongfu.LevelConfig;
import com.yawnlon.kitchenkongfu.R;

import android.content.Context;
import android.content.res.TypedArray;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.TextView;

public class ToolView extends FrameLayout {

	private TextView num;
	private ImageView tool;
	private FrameLayout point;
	private int type;

	public ToolView(Context context) {
		this(context, null);
	}

	public ToolView(Context context, AttributeSet attrs) {
		this(context, attrs, 0);
	}

	public ToolView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.tool_view, this, true);
		num = (TextView) findViewById(R.id.num);
		tool = (ImageView) findViewById(R.id.tool);
		point = (FrameLayout) findViewById(R.id.point);
		TypedArray a = context.obtainStyledAttributes(attrs, R.styleable.ToolView, defStyle, 0);
		type = a.getInt(R.styleable.ToolView_type, -1);
		a.recycle();
		setType();
	}

	public void setType(int type) {
		this.type = type;
		setType();
	}

	private void setType() {
		if (type < 0)
			return;
		if (LevelConfig.TOOL_NUM[type] > 0) {
			point.setVisibility(VISIBLE);
			num.setText(LevelConfig.TOOL_NUM[type] + "");
		}
		tool.setImageResource(LevelConfig.TOOL_RESID[type]);
		tool.setAdjustViewBounds(true);
	}

	public void useOneTool() {
		LevelConfig.TOOL_NUM[type]--;
		if (LevelConfig.TOOL_NUM[type] > 0) {
			num.setText(LevelConfig.TOOL_NUM[type]);
		} else {
			point.setVisibility(VISIBLE);
		}
	}

}
