package com.yawnlon.kitchenkongfu.highlevel;

import javax.microedition.khronos.egl.EGLConfig;
import javax.microedition.khronos.opengles.GL10;
import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.PixelFormat;
import android.opengl.GLSurfaceView;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import elong.CrazyLink.CrazyLinkConstent;
import elong.CrazyLink.CrazyLinkGLSurfaceView;
import elong.CrazyLink.CrazyLinkConstent.E_SCENARIO;
import elong.CrazyLink.Interaction.ScreenTouch;

public class HighCrazyLinkGLSurfaceView extends CrazyLinkGLSurfaceView {

	SceneRenderer mRenderer;
	HighControlCenter mControlCenter;

	public HighCrazyLinkGLSurfaceView(Context context) {
		super(context);
	}

	@Override
	protected void init(Context context) {
		mContext = this.getContext();
		mRenderer = new SceneRenderer();
		setZOrderOnTop(true);
		getHolder().setFormat(PixelFormat.TRANSLUCENT);
		setEGLConfigChooser(8, 8, 8, 8, 16, 0);
		setRenderer(mRenderer);
		setRenderMode(GLSurfaceView.RENDERMODE_CONTINUOUSLY);

		if (!m_bThreadRun) {
			m_bThreadRun = true;
			mControlCenter = new HighControlCenter(mContext);
			new Thread() {
				public void run() {
					while (true) {
						try {
							mControlCenter.run();
							Thread.sleep(CrazyLinkConstent.DELAY_MS);
						} catch (Exception e) {
							e.printStackTrace();
						}
					}
				}
			}.start();
		}
	}

	public HighCrazyLinkGLSurfaceView(Context context, AttributeSet attributeSet) {
		this(context);
	}

	@SuppressLint("ClickableViewAccessibility")
	@Override
	public boolean onTouchEvent(MotionEvent e) {
		if (screenTouch != null) {
			if (HighControlCenter.mScene == E_SCENARIO.GAME) {
				screenTouch.touchGameView(e);
			}
		}
		return true;
	}

	private class SceneRenderer implements GLSurfaceView.Renderer {

		public void onDrawFrame(GL10 gl) {
			gl.glShadeModel(GL10.GL_SMOOTH);
			gl.glClear(GL10.GL_COLOR_BUFFER_BIT | GL10.GL_DEPTH_BUFFER_BIT);
			gl.glMatrixMode(GL10.GL_MODELVIEW);
			gl.glLoadIdentity();
			gl.glTranslatef(0f, 0f, -10f);

			if (HighControlCenter.mScene == E_SCENARIO.GAME) {
				mControlCenter.drawGameScene(gl);
			} else if (HighControlCenter.mScene == E_SCENARIO.MENU) {
				screenTouch.raiseTouchMenuViewEvent();
			}

		}

		public void onSurfaceChanged(GL10 gl, int width, int height) {

			CrazyLinkConstent.REAL_WIDTH = width;
			CrazyLinkConstent.REAL_HEIGHT = height;
			CrazyLinkConstent.translateRatio = (float) width / height;
			CrazyLinkConstent.screentRatio = (float) width / height;
			CrazyLinkConstent.ADP_SIZE = CrazyLinkConstent.UNIT_SIZE * CrazyLinkConstent.VIEW_HEIGHT / height * width
					/ CrazyLinkConstent.VIEW_WIDTH;
			screenTouch = new ScreenTouch(mContext, width, height);
			gl.glViewport(0, 0, width, height);
			gl.glMatrixMode(GL10.GL_PROJECTION);
			gl.glLoadIdentity();

			gl.glOrthof(-CrazyLinkConstent.screentRatio * CrazyLinkConstent.GRID_NUM / 2,
					CrazyLinkConstent.screentRatio * CrazyLinkConstent.GRID_NUM / 2,
					-1 * CrazyLinkConstent.GRID_NUM / 2, 1 * CrazyLinkConstent.GRID_NUM / 2, 10, 100);
		}

		public void onSurfaceCreated(GL10 gl, EGLConfig config) {
			gl.glDisable(GL10.GL_DITHER);
			gl.glHint(GL10.GL_PERSPECTIVE_CORRECTION_HINT, GL10.GL_FASTEST);
			// gl.glClearColor(255, 255, 255, 255);
			gl.glShadeModel(GL10.GL_SMOOTH);
			gl.glEnable(GL10.GL_DEPTH_TEST);

			gl.glEnable(GL10.GL_BLEND);
			gl.glBlendFunc(GL10.GL_SRC_ALPHA, GL10.GL_ONE_MINUS_SRC_ALPHA);
			gl.glEnable(GL10.GL_ALPHA_TEST);
			gl.glAlphaFunc(GL10.GL_GREATER, 0.1f);

			mControlCenter.initTexture(gl);
			mControlCenter.initDraw(gl);
			if (HighControlCenter.mScene == E_SCENARIO.GAME) {
				Message msg = new Message();
				msg.what = HighControlCenter.LOADING_START;
				HighControlCenter.mHandler.sendMessage(msg);
			}

		}
	}
}
