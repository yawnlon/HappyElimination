package com.yawnlon.kitchenkongfu.view;

import com.yawnlon.kitchenkongfu.LevelConfig;
import com.yawnlon.kitchenkongfu.MainActivity;
import com.yawnlon.kitchenkongfu.R;
import com.yawnlon.kitchenkongfu.highlevel.HighMainActivity;

import android.content.Context;
import android.content.res.TypedArray;
import android.graphics.drawable.BitmapDrawable;
import android.util.AttributeSet;
import android.view.Gravity;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.PopupWindow;
import android.widget.TextView;
import android.widget.Toast;

public class ToolView extends FrameLayout {

	private TextView num;
	private ImageView tool;
	private FrameLayout point;
	private int type;
	private HintToolView hintView;
	private PopupWindow popupWindow;

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
		hintView = new HintToolView(context);
		setType();
		setOnClickListener(toolListener);
	}

	public void setType(int type) {
		this.type = type;
		setType();
	}

	@SuppressWarnings("deprecation")
	private void setType() {
		if (type < 0)
			return;
		if (LevelConfig.TOOL_NUM[type] > 0) {
			point.setVisibility(VISIBLE);
			num.setText(LevelConfig.TOOL_NUM[type] + "");
		}
		tool.setImageResource(LevelConfig.TOOL_RESID[type]);
		tool.setAdjustViewBounds(true);
		if (type == LevelConfig.TOOL_HINT) {
			hintView.setBackgroundResource(R.drawable.hint_tool_back);
			popupWindow = new PopupWindow(hintView, 100 * hintView.getHintNum(), 100);
			popupWindow.setFocusable(true);
			popupWindow.setAnimationStyle(R.style.HintAnimationPreview);
			popupWindow.setOutsideTouchable(true);
			popupWindow.setBackgroundDrawable(new BitmapDrawable());
		}
	}

	private void useOneTool() {
		LevelConfig.TOOL_NUM[type]--;
		if (LevelConfig.TOOL_NUM[type] > 0) {
			num.setText(LevelConfig.TOOL_NUM[type] + "");
		} else {
			point.setVisibility(GONE);
		}
	}

	public OnClickListener toolListener = new OnClickListener() {
		@Override
		public void onClick(View v) {
			Context context = v.getContext();
			if (LevelConfig.TOOL_NUM[type] == 0) {
				Toast.makeText(context, "Can't use because the number is zero!", Toast.LENGTH_SHORT).show();
			} else {
				switch (type) {
				case LevelConfig.TOOL_HINT:
					int[] location = new int[2];
					v.getLocationOnScreen(location);
					popupWindow.showAtLocation(v, Gravity.NO_GRAVITY, location[0] - 50,
							location[1] - popupWindow.getHeight());
					break;
				case LevelConfig.TOOL_ADDTIME:
					((MainActivity) context).addTime(5);
					break;
				case LevelConfig.TOOL_SPOON:
					if (!(v.getContext() instanceof HighMainActivity))
						Toast.makeText(context, "NOT DEVELOPED!", Toast.LENGTH_SHORT).show();
					else {
						((HighMainActivity) v.getContext()).useSpoonTool();
					}
					break;
				default:
					Toast.makeText(context, "INVALID TOOL TYPE!", Toast.LENGTH_SHORT).show();
					return;
				}
				useOneTool();
			}
		}
	};
}
