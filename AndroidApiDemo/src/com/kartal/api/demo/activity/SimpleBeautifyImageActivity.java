package com.kartal.api.demo.activity;

import android.R.integer;
import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.kartal.api.demo.R;
import com.kartal.api.demo.utils.ImageBeautifyHelper;
import com.kartal.api.demo.utils.Util;

public class SimpleBeautifyImageActivity extends Activity {

	private float mHue,mSaturation,mLum;
	private ImageView mImageView;
	private Bitmap bitmap;
	
	private static final int MID_VALUE = 50;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.simple_beautify_image_layout);
		
		SeekBar skHue = (SeekBar)findViewById(R.id.seekbarHue);
		SeekBar skSaturation = (SeekBar)findViewById(R.id.seekbarSaturation);
		SeekBar skLum = (SeekBar)findViewById(R.id.seekbarLum);
		skHue.setOnSeekBarChangeListener(seekListener);
		skSaturation.setOnSeekBarChangeListener(seekListener);
		skLum.setOnSeekBarChangeListener(seekListener);
		mImageView = (ImageView) findViewById(R.id.image);
		bitmap = Util.drawable2Bitmap(mImageView.getDrawable());
	}
	
	private OnSeekBarChangeListener seekListener = new OnSeekBarChangeListener() {
		
		@Override
		public void onStopTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onStartTrackingTouch(SeekBar seekBar) {
			// TODO Auto-generated method stub
			
		}
		
		@Override
		public void onProgressChanged(SeekBar seekBar, int progress,
				boolean fromUser) {
			switch(seekBar.getId()) {
				case R.id.seekbarHue:
					mHue = (progress - MID_VALUE) * 1.0F / MID_VALUE*180;
					break;
				case R.id.seekbarSaturation:
					mSaturation = progress * 1.0F / MID_VALUE;
					break;
				case R.id.seekbarLum:
					mLum = progress * 1.0F / MID_VALUE;
					break;
			}
			mImageView.setImageBitmap(ImageBeautifyHelper.handleImageEffect(bitmap, mHue, mSaturation, mLum));
		}
	}; 
	
	@Override
	protected void onDestroy() {
		super.onDestroy();
		if(bitmap != null) {
			bitmap.recycle();
			bitmap = null;
		}
	};
}
