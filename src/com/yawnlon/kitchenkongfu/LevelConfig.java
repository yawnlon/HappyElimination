package com.yawnlon.kitchenkongfu;

public class LevelConfig {

	private static int CURRENT_LEVEL = 0;

	private static final int LEVEL_RES[] = { R.drawable.level1, R.drawable.animal, R.drawable.animal, R.drawable.animal,
			R.drawable.animal, R.drawable.animal, R.drawable.animal, R.drawable.animal };

	private static final String LEVEL_TARGET[] = { "10000000" };
	private static final String RANDOM_CONFIG[] = { "2111111" };
	private static final int LEVEL_TYPE[] = { 0 }; // 0 代表COLD, 1代表HOT

	public static int getCurrentLevel() {
		return CURRENT_LEVEL + 1;
	}

	public static void setCurrentLevel(int current) {
		CURRENT_LEVEL = current - 1;
	}

	public static boolean isTarget(int picId) {
		try {
			return LEVEL_TARGET[CURRENT_LEVEL].charAt(picId - 1) == '1';
		} catch (Exception e) {
			e.printStackTrace();
		}
		return false;
	}

	public static int getLevelRes() {
		return LEVEL_RES[CURRENT_LEVEL];
	}

	public static boolean isCold() {
		return LEVEL_TYPE[CURRENT_LEVEL] == 0;
	}

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

	/**
	 * Food Config
	 */
	private final static int[] foodsID = { R.drawable.answer_food1, R.drawable.answer_food2, R.drawable.answer_food3,
			R.drawable.answer_food4, R.drawable.answer_food5, R.drawable.answer_food6, R.drawable.answer_food7,
			R.drawable.answer_food8, R.drawable.answer_food9 };

	public static int getFood(int id) {
		return foodsID[id - 1];
	}

	private static String[] TARGET_FOOD = { "123" };

	public static String getTargetFood() {
		return TARGET_FOOD[CURRENT_LEVEL];
	}

	/**
	 * Tool Config
	 */
	public static int[] TOOL_NUM = { 1, 1, 1 };
	public static int[] TOOL_RESID = { R.drawable.tool_hint, R.drawable.tool_addtime, R.drawable.tool_spoon };
	public final static int TOOL_HINT = 0;
	public final static int TOOL_ADDTIME = 1;
	public final static int TOOL_SPOON = 2;

}
