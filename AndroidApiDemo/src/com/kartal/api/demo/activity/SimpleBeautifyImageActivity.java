package com.kartal.api.demo.activity;

import android.app.Activity;
import android.graphics.Bitmap;
import android.graphics.ColorMatrix;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.SeekBar;
import android.widget.SeekBar.OnSeekBarChangeListener;

import com.kartal.api.demo.R;
import com.kartal.api.demo.utils.ImageBeautifyHelper;
import com.kartal.api.demo.utils.Util;

public class SimpleBeautifyImageActivity extends Activity implements OnClickListener {

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
		
		Button button0 = (Button) findViewById(R.id.button0);
		Button button1 = (Button) findViewById(R.id.button1);
		Button button2 = (Button) findViewById(R.id.button2);
		Button button3 = (Button) findViewById(R.id.button3);
		Button button4 = (Button) findViewById(R.id.button4);
		
		button0.setOnClickListener(this);
		button1.setOnClickListener(this);
		button2.setOnClickListener(this);
		button3.setOnClickListener(this);
		button4.setOnClickListener(this);
		
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
	}

	@Override
	public void onClick(View v) {
		float[] floatMatrixs = null;
		switch (v.getId()) {
		case R.id.button0:
			floatMatrixs = ImageBeautifyHelper.getGrayMatrix();
			break;
		case R.id.button1:
			floatMatrixs = ImageBeautifyHelper.getReverseMatrix();
			break;
		case R.id.button2:
			floatMatrixs = ImageBeautifyHelper.getReminiscenceMatrix();
			break;
		case R.id.button3:
			floatMatrixs = ImageBeautifyHelper.getRemoveColorMatrix();
			break;
		case R.id.button4:
			floatMatrixs = ImageBeautifyHelper.getHighSaturationMatrix();
			break;

		default:
			break;
		}
		
		if(floatMatrixs != null) {
			mImageView.setImageBitmap(ImageBeautifyHelper.getHandledBitmap(bitmap, floatMatrixs));
		}
	};
}
