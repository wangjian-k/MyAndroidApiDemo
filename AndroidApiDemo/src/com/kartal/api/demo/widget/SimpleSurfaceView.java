package com.kartal.api.demo.widget;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.Paint.Style;
import android.graphics.RectF;
import android.util.AttributeSet;
import android.util.Log;
import android.view.SurfaceHolder;
import android.view.SurfaceView;

public class SimpleSurfaceView extends SurfaceView implements SurfaceHolder.Callback {

	private static final String TAG = SimpleSurfaceView.class.getSimpleName();
	private MyThread thread;
	public SimpleSurfaceView(Context context) {
		super(context);
		init(context);
	}
	
	public SimpleSurfaceView(Context context,AttributeSet set) {
		super(context, set);
		init(context);
	}
	
	private void init(Context context) {
		
		/*重写surfaceView的步骤主要包含 1：获得surfaceView的surfaceHolder；2：未holder设置对应的callback；
		 *在SurfaceHolder.callback中执行draw方法，不断刷新surfaceview。
		 */
		//surfaceHolder相当于surfaceView的遥控器，用来控制surfaceView
		SurfaceHolder mHolder = this.getHolder();
		//设置holder的callback方法，这个是必须要设置的，用于控制surfaceView的创建，变更及销毁。
		mHolder.addCallback(this);
		
		thread = new MyThread(mHolder, context);
	}
	
	@Override
	public void surfaceChanged(SurfaceHolder holder, int format, int width,
			int height) {
		Log.d(TAG,"surfaceChanged...");
	}

	@Override
	public void surfaceCreated(SurfaceHolder holder) {
		Log.d(TAG,"surfaceCreated...");
		thread.isThreadRunning = true;
		thread.start();
	}

	@Override
	public void surfaceDestroyed(SurfaceHolder holder) {
		Log.d(TAG,"surfaceDestroyed...");
		thread.isThreadRunning = false;
		try {
			thread.join();
		} catch(InterruptedException e) {
			e.printStackTrace();
		}
	}

	public class MyThread extends Thread {
		SurfaceHolder surfaceHolder;
		Context context;
		boolean isThreadRunning;
		float radius = 10f;
		Paint paint;
		Paint pathPaint;
		Path path = new Path();
		int x=300,y;
		
		public MyThread(SurfaceHolder holder,Context context) {
			surfaceHolder = holder;
			this.context = context;
			paint = new Paint();
			paint.setStyle(Style.FILL);
			paint.setAntiAlias(true);
			paint.setColor(Color.RED);
			
			path.moveTo(300, 300);
			pathPaint = new Paint();
			pathPaint.setStyle(Style.STROKE);
			pathPaint.setStrokeWidth(5);
			pathPaint.setColor(Color.WHITE);
		}
		
		@Override
		public void run() {
			Canvas canvas = null;
			while(isThreadRunning) {
				try {
					synchronized (surfaceHolder) {
						canvas = surfaceHolder.lockCanvas();
//						doDraw(canvas);
						doDrawSinPath(canvas);
						Thread.sleep(50);
					}
				} catch(Exception e) {
					e.printStackTrace();
				} finally {
					/*notes:这里需要同时判断surfaceHolder以及canvas是否为空。如果只判断了holder而未判断canvas，
					 将出现 canvas object must be the same instance that was previously returned by lockCanvas
					solved by :
					http://stackoverflow.com/questions/29948673/canvas-object-must-be-the-same-instance-that-was-previously-returned-by-lockcanv
					*/
					if(surfaceHolder != null && canvas != null) {
						surfaceHolder.unlockCanvasAndPost(canvas);
					}
				}
			}
		}
		
		private void doDraw(Canvas canvas) {
			canvas.drawColor(Color.BLACK);
			
			canvas.translate(500, 500);
//			if(radius % 2 == 0) {
				canvas.drawCircle(0, 0, radius++, paint);
//			} else {
//				canvas.drawArc(new RectF(0,0,400,400), radius + 10f, radius + 90f, true, paint);
				radius++;
//			}
			
			if(radius > 500) {
				radius = 10f;
			}
		}
		
		private void doDrawSinPath(Canvas canvas) {
			canvas.drawColor(Color.BLACK);
			canvas.translate(300, 300);
			x += 1;
			y = (int)(100*Math.sin(x*2*Math.PI / 180) + 400);
			path.lineTo(x, y);
			canvas.drawPath(path, pathPaint);
		}
	}

}
