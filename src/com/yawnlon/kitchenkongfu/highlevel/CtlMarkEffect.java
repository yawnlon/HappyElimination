
package com.yawnlon.kitchenkongfu.highlevel;

import elong.CrazyLink.Control.CtlBase;

//自动提示效果
public class CtlMarkEffect extends CtlBase{
	
	int mPicId = 1;
	int mTimeCnt = 0;

	public void run()
	{
		if(mStop) return;
		mTimeCnt++;
		if (1 != (mTimeCnt % 4)) return;		//降频
		mPicId++;
		if (mPicId > 4) mPicId = 1;
	}
	
	public int getPicId()
	{
		return mPicId;
	}			
}

