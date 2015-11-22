package com.yawnlon.kitchenkongfu.view;

import com.yawnlon.kitchenkongfu.R;

import android.app.Activity;
import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.view.View;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class FinalPagerView extends LinearLayout {
	private int firstPage = -1;
	protected ImageView dialog, goodbye;
	private Context context;

	public FinalPagerView(Context context) {
		this(context, null);
	}

	public FinalPagerView(final Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.final_view, this, true);
		dialog = (ImageView) findViewById(R.id.dialog);
		goodbye = (ImageView) findViewById(R.id.good_bye);
		this.context = context;
		dialog.setAdjustViewBounds(true);
	}

	public FinalPagerView setGoodByeVisibility(boolean visibility) {
		goodbye.setVisibility(visibility ? View.VISIBLE : View.GONE);
		if (visibility) {
			goodbye.setOnClickListener(new OnClickListener() {
				@Override
				public void onClick(View v) {
					((Activity) context).finish();
				}
			});
		}
		return this;
	}

	public FinalPagerView setFisrtPageId(int id) {
		firstPage = id;
		return this;
	}

	public FinalPagerView setCurrentPage(int currentPage) {
		if (firstPage < 0)
			return null;
		dialog.setImageResource(firstPage + currentPage);
		return this;
	}

}
