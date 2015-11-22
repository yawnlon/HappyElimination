package com.yawnlon.kitchenkongfu.highlevel;

import com.yawnlon.kitchenkongfu.LevelConfig;

public class HighLevelConfig extends LevelConfig {

	/**
	 * High Level的LevelResource、MaxTime、isTarget、TargetFood、ConfigRandom继续在父类中配置
	 */

	/**
	 * HighLevel的type，0是Ice、1是Fire
	 */
	private final static int[] HIGH_LEVEL_TYPE = { 0, 1, 0, 1 };

	public static boolean isIce() {
		return HIGH_LEVEL_TYPE[CURRENT_LEVEL - 6] == 0;
	}

	/**
	 * "abcdef"表示初始化时的坐标为(a b), (c d), (e f), 以此类推, a~(0, 6) 注意从左下角开始
	 */
	private final static String[] HIGH_LEVEL_MARK_POS = { "233354", "4524", "0324523454", "3341643526" };

	public static String getMarkPos() {
		return HIGH_LEVEL_MARK_POS[CURRENT_LEVEL - 6];
	}
}
