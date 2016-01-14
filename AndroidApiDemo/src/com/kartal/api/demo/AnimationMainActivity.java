package com.kartal.api.demo;

import android.app.Activity;
import android.graphics.drawable.Animatable;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.view.Window;
import android.view.WindowManager;
import android.widget.ImageView;

public class AnimationMainActivity extends Activity {

	private ImageView trickImageView;
	@Override
	protected void onCreate(Bundle savedInstanceState) {
		requestWindowFeature(Window.FEATURE_NO_TITLE);
		getWindow().setFlags(WindowManager.LayoutParams.FLAG_FULLSCREEN, WindowManager.LayoutParams.FLAG_FULLSCREEN);
		super.onCreate(savedInstanceState);
		setContentView(R.layout.animation_main_layout);
		//svg animator
		ImageView imageview = (ImageView)findViewById(R.id.imageview);
		((Animatable) imageview.getDrawable()).start();
		
		trickImageView = (ImageView)findViewById(R.id.imageview1);
		trickImageView.setOnClickListener(new OnClickListener(){

			@Override
			public void onClick(View v) {
				animateTrick();
			}
			
		});
		
		ImageView searchView = (ImageView)findViewById(R.id.imageview_search);
		searchView.setOnClickListener(new OnClickListener() {
			
			@Override
			public void onClick(View v) {
				Drawable drawable = ((ImageView)v).getDrawable();
				if(drawable instanceof Animatable) {
					((Animatable)drawable).start();
				}
				
			}
		});
	}
	
	private void animateTrick() {
		Drawable drawable = trickImageView.getDrawable();
		if(drawable instanceof Animatable) {
			((Animatable)drawable).start();
		}
	}
}
