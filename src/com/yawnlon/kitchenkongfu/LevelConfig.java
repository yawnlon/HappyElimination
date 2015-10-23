package com.yawnlon.kitchenkongfu;

public class LevelConfig {

	private static int CURRENT_LEVEL = 0;

	private static final int LEVEL_RES[] = { R.drawable.animal, R.drawable.animal, R.drawable.animal, R.drawable.animal,
			R.drawable.animal, R.drawable.animal, R.drawable.animal, R.drawable.animal };

	private static final String LEVEL_TARGET[] = { "10000000" };
	private static final String RANDOM_CONFIG[] = { "2111111" };

	public static void setCurrentLevel(int current) {
		CURRENT_LEVEL = current - 1;
	}

	public static boolean isTarget(int picId) {
		return LEVEL_TARGET[CURRENT_LEVEL].charAt(picId - 1) == '1';
	}

	public static int getLevelRes() {
		return LEVEL_RES[CURRENT_LEVEL];
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

}
