package com.kartal.api.demo;

import android.content.Intent;
import android.graphics.drawable.Animatable;
import android.os.Bundle;
import android.support.v7.app.ActionBarActivity;
import android.view.Menu;
import android.view.MenuItem;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.ImageView;

import com.kartal.api.demo.activity.Simple2DCanvasViewActivity;
import com.kartal.api.demo.activity.SimpleBeautifyImageActivity;
import com.kartal.api.demo.activity.SimpleSurfaceViewActivity;
import com.kartal.api.demo.activity.XfermodeViewActivity;

public class MainActivity extends ActionBarActivity implements OnClickListener {

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		Button surfaceView = (Button) findViewById(R.id.surfaceview);
		surfaceView.setOnClickListener(this);
		
		Button canvasView = (Button) findViewById(R.id.canvs_2d);
		canvasView.setOnClickListener(this);
		
		Button beautifyImageView = (Button) findViewById(R.id.beautify_image);
		beautifyImageView.setOnClickListener(this);
		
		Button xferModeViewButton = (Button) findViewById(R.id.xfermode_view);
		xferModeViewButton.setOnClickListener(this);
		
		Button animationButton = (Button)findViewById(R.id.animation);
		animationButton.setOnClickListener(this);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	public boolean onOptionsItemSelected(MenuItem item) {
		// Handle action bar item clicks here. The action bar will
		// automatically handle clicks on the Home/Up button, so long
		// as you specify a parent activity in AndroidManifest.xml.
		int id = item.getItemId();
		if (id == R.id.action_settings) {
			return true;
		}
		return super.onOptionsItemSelected(item);
	}

	@Override
	public void onClick(View v) {
		Intent intent = null;
		switch (v.getId()) {
		case R.id.surfaceview:
			intent = new Intent(MainActivity.this,SimpleSurfaceViewActivity.class);
			break;
		case R.id.canvs_2d:
			intent = new Intent(MainActivity.this,Simple2DCanvasViewActivity.class);
			break;
		case R.id.beautify_image:
			intent = new Intent(MainActivity.this,SimpleBeautifyImageActivity.class);
			break;
		case R.id.xfermode_view:
			intent = new Intent(MainActivity.this,XfermodeViewActivity.class);
			break;
		case R.id.animation:
			intent = new Intent(MainActivity.this,AnimationMainActivity.class);
			break;

		default:
			break;
		}
		startActivity(intent);
	}
}
