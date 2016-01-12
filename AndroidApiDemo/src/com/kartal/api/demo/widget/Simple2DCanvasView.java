package com.kartal.api.demo.widget;

import android.annotation.SuppressLint;
import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Color;
import android.graphics.Paint;
import android.graphics.Path;
import android.graphics.RectF;
import android.graphics.Paint.Style;
import android.util.AttributeSet;
import android.view.View;

public class Simple2DCanvasView extends View {

	private Paint paint;
	
	public Simple2DCanvasView(Context context) {
		super(context);
		init(context);
	}
	
	public Simple2DCanvasView(Context context, AttributeSet attrs) {
		super(context, attrs);
		init(context);
	}
	
	public Simple2DCanvasView(Context context, AttributeSet attrs,
			int defStyleAttr) {
		super(context, attrs, defStyleAttr);
		init(context);
	}
	
	private void init(Context context){
		paint = new Paint();
		paint.setAntiAlias(true);
		paint.setColor(Color.GREEN);
		paint.setStyle(Style.STROKE);
		paint.setStrokeWidth(5);
	}
	
	@SuppressLint("NewApi")
	@Override
	protected void onDraw(Canvas canvas) {
		super.onDraw(canvas);
		Path path = new Path();
		canvas.save();
		//平移
		canvas.translate(100, 100);
		//画点
		canvas.drawPoint(100, 100, paint);
		canvas.restore();
		//画path
		paint.setColor(Color.BLUE);
		path.lineTo(200, 200);
		path.quadTo(300, 100, 800, 809);
		canvas.drawPath(path,paint);
		//画线
		paint.setColor(Color.RED);
		canvas.drawLine(0, 300, 300, 400, paint);
		//画多条线
		paint.setColor(Color.BLACK);
		float[] pts = {
				200,100,23,435,654,324,800,899,39,989,40,699
		};
		canvas.drawLines(pts, paint);
		//画矩形
		paint.setColor(Color.GREEN);
		canvas.drawRect(800, 0, 900, 100, paint);
		//圆角矩形
//		canvas.drawRoundRect(800, 120, 900, 220, 10, 10, paint);
		//画圆
		paint.setColor(Color.YELLOW);
		canvas.drawCircle(500, 500, 180, paint);
		//画扇形
		paint.setStyle(Style.FILL);
		canvas.drawArc(new RectF(400, 400, 600, 600), 100F, -270F, true, paint);
		
		paint.setColor(Color.BLACK);
		paint.setTextSize(30);
		canvas.drawText("This is A test demo for Canvas and paint!!!", 0, 100, paint);
		
		drawColock(canvas);
	}
	
	private void drawColock(Canvas canvas) {
		Paint paintCircle = new Paint();
		paintCircle.setColor(Color.BLACK);
		paintCircle.setStyle(Style.STROKE);
		paintCircle.setAntiAlias(true);
		paintCircle.setStrokeWidth(5);
		int width = 500;
		canvas.drawCircle(1000, 550, width/2, paintCircle);
		
		Paint paintDegree = new Paint();
		paintDegree.setColor(Color.GREEN);
		paintDegree.setStyle(Style.STROKE);
		paintDegree.setStrokeWidth(3);
		for(int i=0;i<24;i++) {
			//区分整点与非整点
			if(i==0 || i==6 || i==12 || i==18) {
				paintDegree.setStrokeWidth(5);
				paintDegree.setTextSize(30);
				canvas.drawLine(1000, 550 - width/2, 1000, 550-width/2 + 60, paintDegree);
				String degree = String.valueOf(i);
				canvas.drawText(degree, 1000 - paintDegree.measureText(degree) / 2,
						550 - width/2 + 90, paintDegree);
			} else {
				paintDegree.setStrokeWidth(3);
				paintDegree.setTextSize(15);
				canvas.drawLine(1000, 550 - width/2, 1000, 550-width/2 + 30, paintDegree);
				String degree = String.valueOf(i);
				canvas.drawText(degree, 1000 - paintDegree.measureText(degree) / 2,
						550 - width/2 + 60, paintDegree);
			}
			canvas.rotate(15, 1000, 550);
		}
		
		Paint paintHour = new Paint();
		paintHour.setColor(Color.RED);
		paintHour.setStyle(Style.STROKE);
		paintHour.setStrokeWidth(20);
		
		Paint paintMinute = new Paint();
		paintMinute.setColor(Color.BLUE);
		paintMinute.setStyle(Style.STROKE);
		paintMinute.setStrokeWidth(10);
		
		canvas.save();
		canvas.translate(1000, 550);
		canvas.drawLine(0, 0, 100, 100, paintHour);
		canvas.drawLine(0, 0, 100, 200, paintMinute);
		canvas.restore();
	}

}
