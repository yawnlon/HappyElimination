package com.yawnlon.kitchenkongfu;

public class LevelConfig {

	/**
	 * 当前关卡
	 */
	private static int CURRENT_LEVEL = 0;

	public static int getCurrentLevel() {
		return CURRENT_LEVEL + 1;
	}

	public static void setCurrentLevel(int current) {
		CURRENT_LEVEL = current - 1;
	}

	/**
	 * Level Config
	 */
	private static final int LEVEL_RES[] = { R.drawable.level1, R.drawable.level2, R.drawable.level3, R.drawable.level4,
			R.drawable.level5, R.drawable.level6, };// 每一关的食物对应的PicId
	private static final String LEVEL_TARGET[] = { "10000000", "10000000", "11000000", "11000000", "11100000",
			"11100000" };// 每一关的Pic对应的目标食物，例如level1.png香蕉是目标食物，则为10000000
	private static final int LEVEL_TARGET_TYPE[] = { 0, 1, 0, 1, 0, 1 }; // 每一关的目标属性,
																			// 0代表COLD,
																			// 1代表HOT
	private static final int LEVEL_TOTAL_TARGET_SCORE[] = { 120, 120, 120, 120, 120, 120 };// 每一关的目标分数，达到该分数即可过关
	private static final int LEVEL_MAX_TIME[] = { 100, 100, 100, 100, 100, 100 };// 每一关的最大时间

	public static boolean isTarget(int picId) {
		if (picId >= 0 && picId < 8)
			return LEVEL_TARGET[CURRENT_LEVEL].charAt(picId - 1) == '1';
		return false;
	}

	public static int getLevelRes() {
		return LEVEL_RES[CURRENT_LEVEL];
	}

	public static boolean isCold() {
		return LEVEL_TARGET_TYPE[CURRENT_LEVEL] == 0;
	}

	public static int getTotalTargetScore() {
		return LEVEL_TOTAL_TARGET_SCORE[CURRENT_LEVEL];
	}

	public static int getMaxTime() {
		return LEVEL_MAX_TIME[CURRENT_LEVEL];
	}

	/**
	 * Food Picture Config
	 */
	private final static int[] foodsID = { R.drawable.food1, R.drawable.food2, R.drawable.food3, R.drawable.food4,
			R.drawable.food5, R.drawable.food6, R.drawable.food7, R.drawable.food8, R.drawable.food9, R.drawable.food10,
			R.drawable.food11, R.drawable.food12, R.drawable.food13, R.drawable.food14, R.drawable.food15 };

	public static int getFood(int id) {
		return foodsID[id - 1];
	}

	// 每一关的targetFood，n对应R.drawable.answer_foodn
	// : -> 10,
	// ; -> 11,
	// < -> 12,
	// = -> 13
	// > -> 14
	// ? -> 15
	private static String[] LEVEL_TARGET_FOOD = { "1", "3", "24", "62", "412", "784" };

	public static String getTargetFood() {
		return LEVEL_TARGET_FOOD[CURRENT_LEVEL];
	}

	/**
	 * Tool Config
	 */
	public static int[] TOOL_NUM = { 1, 3, 0 };
	public static int[] TOOL_RESID = { R.drawable.tool_hint, R.drawable.tool_addtime, R.drawable.tool_spoon };
	public final static int TOOL_HINT = 0;
	public final static int TOOL_ADDTIME = 1;
	public final static int TOOL_SPOON = 2;

	/**
	 * Random Config
	 */
	private static final String RANDOM_CONFIG[] = { "4111111", "4111111", "4111111", "4111111", "4111111", "4111111" };// 每一关的食物比例

	/**
	 * 根据RANDOM_CONFIG中的概率得到随机数
	 * 
	 * @return 得到的随机数
	 */
	public static int getConfigRandom() {
		char[] rConfig = RANDOM_CONFIG[CURRENT_LEVEL].toCharArray();
		int sum = 0;
		for (int i = 0; i < rConfig.length; i++) {
			sum += (rConfig[i] - '0');
		}
		int random = (int) (Math.random() * 100) % sum;
		int temp = 0;
		for (int i = 0; i < rConfig.length; i++) {
			temp += (rConfig[i] - '0');
			if (random < temp) {
				return (i + 1);
			}
		}
		return 0;
	}

}
