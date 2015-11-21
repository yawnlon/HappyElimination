package com.yawnlon.kitchenkongfu.view;

import com.yawnlon.kitchenkongfu.R;

import android.content.Context;
import android.util.AttributeSet;
import android.view.LayoutInflater;
import android.widget.ImageView;
import android.widget.LinearLayout;

public class PrePagerView extends LinearLayout {
	private int firstPage = -1;
	protected ImageView dialog, next;

	public PrePagerView(Context context) {
		this(context, null);
	}

	public PrePagerView(Context context, AttributeSet attrs) {
		super(context, attrs);
		LayoutInflater.from(context).inflate(R.layout.pre, this, true);
		dialog = (ImageView) findViewById(R.id.dialog);
		next = (ImageView) findViewById(R.id.next);
		dialog.setAdjustViewBounds(true);
	}

	public PrePagerView setNextVisibility(boolean visible) {
		next.setVisibility(visible ? VISIBLE : GONE);
		return this;
	}

	public PrePagerView setNextListener(OnClickListener listener) {
		next.setOnClickListener(listener);
		return this;
	}

	public PrePagerView setFisrtPageId(int id) {
		firstPage = id;
		return this;
	}

	public PrePagerView setCurrentPage(int currentPage) {
		if (firstPage < 0)
			return null;
		dialog.setImageResource(firstPage + currentPage);
		return this;
	}

}
