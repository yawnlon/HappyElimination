package com.yawnlon.kitchenkongfu;

import java.util.ArrayList;
import java.util.List;

import com.yawnlon.kitchenkongfu.view.PrePagerView;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.support.v4.view.PagerAdapter;
import android.support.v4.view.ViewPager;
import android.support.v4.view.ViewPager.OnPageChangeListener;
import android.view.View;
import android.view.View.OnClickListener;

public class PrePagerActivity extends Activity {

	private ViewPager mPager;
	private Context mContext;
	private List<View> mViewList;
	private int mPagerNum;

	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		mContext = this;
		mPager = new ViewPager(mContext);
		mPagerNum = setPagerNum();
		mViewList = initViews();
		mPager.setAdapter(new MyPagerAdapter());
		mPager.setCurrentItem(0);
		mPager.setOnPageChangeListener(setPageChangeListener());
		setContentView(mPager);
	}

	protected List<View> initViews() {
		List<View> list = new ArrayList<View>();
		for (int i = 0; i < mPagerNum; i++) {
			if (i != mPagerNum - 1)
				list.add(new PrePagerView(mContext).setNextVisibility(false).setFisrtPageId(R.drawable.pre_page1)
						.setCurrentPage(i));
			else
				list.add(new PrePagerView(mContext).setNextVisibility(true).setNextListener(new OnClickListener() {
					@Override
					public void onClick(View v) {
						Intent intent = new Intent();
						intent.setClass(PrePagerActivity.this, AnswerActivity.class);
						startActivity(intent);
						overridePendingTransition(R.anim.in_from_right, R.anim.out_to_left);
						finish();
					}
				}).setFisrtPageId(R.drawable.pre_page1).setCurrentPage(i));
		}
		return list;
	}

	protected int setPagerNum() {
		return 4;
	}

	protected OnPageChangeListener setPageChangeListener() {
		return null;
	}

	class MyPagerAdapter extends PagerAdapter {

		@Override
		public int getCount() {
			return mPagerNum;
		}

		@Override
		public boolean isViewFromObject(View arg0, Object arg1) {
			return arg0 == arg1;
		}

		@Override
		public void destroyItem(View container, int position, Object object) {
			((ViewPager) container).removeView(mViewList.get(position));
		}

		@Override
		public Object instantiateItem(View container, int position) {
			((ViewPager) container).addView(mViewList.get(position), 0);
			return mViewList.get(position);
		}

	}
}
