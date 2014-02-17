package com.example.zombear.view;

import java.lang.ref.WeakReference;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Rect;
import android.os.Handler;
import android.os.Message;
import android.util.AttributeSet;
import android.view.MotionEvent;
import android.view.SurfaceHolder;
import android.view.SurfaceHolder.Callback;
import android.view.SurfaceView;

// moteur du jeu....

public class GameView extends SurfaceView implements Callback {

	private Bear bear;
	private Background background;
	
	private boolean running;
	
	static class RefreshHandler extends Handler {
		WeakReference<GameView> weak;
		
		RefreshHandler(GameView gameRenderer){
			weak = new WeakReference(gameRenderer);
		}

		@Override
		public void handleMessage(Message msg) {
			if (weak.get() == null) return;
			weak.get().update();
		}

		public void sleep(long delayMillis) {
			this.removeMessages(0);
			sendMessageDelayed(obtainMessage(0), delayMillis);
		}
	};
	
	RefreshHandler handler = new RefreshHandler(this);
	public static final int DELAY = 30; // ms

	
	private Rect rect;
	

	public GameView(Context context, AttributeSet attrs, int defStyle) {
		super(context, attrs, defStyle);
		init(context);
	}

	public GameView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}

	public GameView(Context context) {
		super(context);
		init(context);
	}

	private void init(Context context) {
		running = false;
		background = new Background(context);
		getHolder().addCallback(this);
	}

	public void setBear(Bear bear){
		this.bear = bear;
	}

	@Override
	protected void onDraw(Canvas canvas) {
		if (rect == null) return;
		if (background != null){
			background.paint(canvas,rect);
		}
		if (bear != null)
			bear.paint( canvas);
	}

	public void update() {
		if (!running) return;
		handler.sleep(DELAY);
		if (bear != null) bear.act();
		updateCanvas();
	}

	@SuppressLint("WrongCall")
	public synchronized void updateCanvas(){
		Canvas c = getHolder().lockCanvas();
		if (c != null){
		onDraw(c);
		getHolder().unlockCanvasAndPost(c);
		}
	}


	@Override
	public void surfaceChanged(SurfaceHolder arg0, int arg1, int arg2, int arg3) {
		rect = new Rect(0,0,arg2,arg3);
		// TODO Auto-generated method stub
	}

	@Override
	public void surfaceCreated(SurfaceHolder arg0) {
		running = true;
		handler.sleep(DELAY);
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder arg0) {
		running = false;
		// TODO Auto-generated method stub		
	}
	
	@Override
	public boolean onTouchEvent(MotionEvent event) {
		if (event.getAction() == MotionEvent.ACTION_DOWN ||
				event.getAction() == MotionEvent.ACTION_MOVE){
			bear.setTarget(event.getX()/getWidth(),event.getY()/getHeight());
		}
		return true;
	}


}
