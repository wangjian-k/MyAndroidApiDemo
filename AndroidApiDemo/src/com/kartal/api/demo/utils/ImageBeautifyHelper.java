package com.kartal.api.demo.utils;

import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.ColorMatrix;
import android.graphics.ColorMatrixColorFilter;
import android.graphics.Paint;

public class ImageBeautifyHelper {

	private static final String TAG = ImageBeautifyHelper.class.getSimpleName();
	
	/**
	 * 返回使用色调、饱和度、亮度处理后的bitmap
	 * @param bm
	 * @param hue
	 * @param saturation
	 * @param lum
	 * @return
	 */
	public static Bitmap handleImageEffect(Bitmap bm,float hue,float saturation,
			float lum) {
		Bitmap bmp = Bitmap.createBitmap(bm.getWidth(),bm.getHeight(),Bitmap.Config.ARGB_8888);
		Canvas canvas = new Canvas(bmp);
		Paint paint = new Paint();
		
		ColorMatrix hueMatrix = new ColorMatrix();
		hueMatrix.setRotate(0, hue);
		hueMatrix.setRotate(1, hue);
		hueMatrix.setRotate(2, hue);
		
		ColorMatrix saturationMatrix = new ColorMatrix();
		saturationMatrix.setSaturation(saturation);
		
		ColorMatrix lumMatrix = new ColorMatrix();
		lumMatrix.setScale(lum, lum, lum, 1);
		
		ColorMatrix imageMatrix = new ColorMatrix();
		imageMatrix.postConcat(hueMatrix);
		imageMatrix.postConcat(saturationMatrix);
		imageMatrix.postConcat(lumMatrix);
		
		paint.setColorFilter(new ColorMatrixColorFilter(imageMatrix));
		canvas.drawBitmap(bm, 0, 0, paint);
		return bmp;
	}
	
	/**
	 * 灰度效果matrix
	 * @return
	 */
	public static float[] getGrayMatrix() {
		float[] colorMatrix = {0.33F,0.59F,0.11F,0,0,
							   0.33F,0.59F,0.11F,0,0,
							   0.33F,0.59F,0.11F,0,0,
							   0,    0,    0,  1, 0
							  };
		return colorMatrix;
	}
	
	/**
	 * 图像反转
	 * @return
	 */
	public static float[] getReverseMatrix() {
		float[] colorMatrix = {-1,0,0,1,1,
				   			   0,-1,0,1,1,
				   			   0,0,-1,1,1,
				   			   0,0,0,1,0
				  };
		return colorMatrix;
	}
	
	/**
	 * 怀旧效果
	 * @return
	 */
	public static float[] getReminiscenceMatrix() {
		float[] colorMatrix = {0.393F,0.769F,0.189F,0,0,
				   			   0.349F,0.686F,0.168F,0,0,
				   			   0.272F,0.534F,0.131F,0,0,
				   			   0,0,0,1,0
				  };
		return colorMatrix;
	}
	
	/**
	 * 去色效果
	 * @return
	 */
	public static float[] getRemoveColorMatrix() {
		float[] colorMatrix = {1.5F,1.5F,1.5F,0,-1,
							   1.5F,1.5F,1.5F,0,-1,
							   1.5F,1.5F,1.5F,0,-1,
				   			   0,0,0,1,0
				  };
		return colorMatrix;
	}
	
	/**
	 * 高饱和度效果
	 * @return
	 */
	public static float[] getHighSaturationMatrix() {
		float[] colorMatrix = {1.438F,-0.122F,-0.016F,0,-0.03F,
							   -0.062F,1.378F,-0.016F,0,0.05F,
							   -0.062F,-0.122F,1.483F,0,-0.02F,
				   			   0,0,0,1,0
				  };
		return colorMatrix;
	}
	
	/**
	 * 返回经过Matrix处理过的bitmap
	 */
	public static Bitmap getHandledBitmap(Bitmap originBitmap,float[] cm) {
		Bitmap bmp = Bitmap.createBitmap(originBitmap.getWidth(),originBitmap.getHeight(),
				Bitmap.Config.ARGB_8888);
		ColorMatrix colorMatrix = new ColorMatrix();
		colorMatrix.set(cm);
		
		Canvas canvas = new Canvas(bmp);
		Paint paint = new Paint();
		paint.setColorFilter(new ColorMatrixColorFilter(colorMatrix));
		canvas.drawBitmap(originBitmap, 0, 0, paint);
		
		return bmp;
	}
	
}
