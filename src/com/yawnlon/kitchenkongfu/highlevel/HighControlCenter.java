package com.yawnlon.kitchenkongfu.highlevel;

import javax.microedition.khronos.opengles.GL10;

import com.yawnlon.kitchenkongfu.R;

import android.content.Context;
import elong.CrazyLink.CrazyLinkConstent;
import elong.CrazyLink.Core.ControlCenter;

public class HighControlCenter extends ControlCenter {

	int markEffectTextureId;
	static public DrawMarkEffect drawMarkEffect;

	public HighControlCenter(Context context) {
		super(context);
		mMarkEffect = new int[(int) CrazyLinkConstent.GRID_NUM][(int) CrazyLinkConstent.GRID_NUM];
		initMarkEffect();
	}

	private void initMarkEffect() {
		for (int i = 0; i < (int) CrazyLinkConstent.GRID_NUM; i++) {
			for (int j = 0; j < (int) CrazyLinkConstent.GRID_NUM; j++) {
				mMarkEffect[i][j] = MARK_EFFECT_NORMAL;
			}
		}
		String markPos = HighLevelConfig.getMarkPos();
		for (int i = 0; i < markPos.length(); i = i + 2) {
			int x = markPos.charAt(i) - '0';
			int y = markPos.charAt(i + 1) - '0';
			mMarkEffect[x][y] = MARK_EFFECT_IOF;
			mMarkNum++;
		}
	}
	
	

	@Override
	public void initTexture(GL10 gl) {
		super.initTexture(gl);
		markEffectTextureId = HighLevelConfig.isIce() ? initTexture(gl, R.drawable.ice)
				: initTexture(gl, R.drawable.fire);
	}

	@Override
	public void initDraw(GL10 gl) {
		super.initDraw(gl);
		drawMarkEffect = new DrawMarkEffect(markEffectTextureId);
		controlRegister(drawMarkEffect.control);
		drawMarkEffect.control.start();
	}

	@Override
	public void drawGameScene(GL10 gl) {
		if (mIsLoading) {
			drawLoading.draw(gl);
			return;
		}
		for (int i = 0; i < (int) CrazyLinkConstent.GRID_NUM; i++) {
			for (int j = 0; j < (int) CrazyLinkConstent.GRID_NUM; j++) {
				switch (mMarkEffect[i][j]) {
				case MARK_EFFECT_IOF:
					drawMarkEffect.draw(gl, i, j);
					break;
				default:
					break;
				}
			}
		}
		drawGame(gl);
	}
}
