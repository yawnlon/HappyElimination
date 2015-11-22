package com.yawnlon.kitchenkongfu.highlevel;

import java.util.ArrayList;
import java.util.List;

import com.yawnlon.kitchenkongfu.PrePagerActivity;
import com.yawnlon.kitchenkongfu.R;
import com.yawnlon.kitchenkongfu.view.FinalPagerView;

import android.view.View;

public class FinalPagerActivity extends PrePagerActivity {
	@Override
	protected List<View> initViews() {
		List<View> list = new ArrayList<View>();
		for (int i = 0; i < mPagerNum; i++) {
			if (i != mPagerNum - 1)
				list.add(new FinalPagerView(mContext).setGoodByeVisibility(false).setFisrtPageId(R.drawable.final_page1)
						.setCurrentPage(i));
			else
				list.add(new FinalPagerView(mContext).setGoodByeVisibility(true).setFisrtPageId(R.drawable.final_page1)
						.setCurrentPage(i));
		}
		return list;
	}

	@Override
	protected int setPagerNum() {
		return 3;
	}
}
