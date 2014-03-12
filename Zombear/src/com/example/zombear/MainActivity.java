package com.example.zombear;

import com.example.zombear.view.Bear;
import com.example.zombear.view.GameView;

import android.os.Bundle;
import android.app.Activity;
import android.view.Menu;

public class MainActivity extends Activity {
	
	Bear bear;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		bear = new Bear(this);
		((GameView) findViewById(R.id.gameView1)).setBear(bear);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		bear = new Bear(this, savedInstanceState.getBundle("bear"));
		((GameView) findViewById(R.id.gameView1)).setBear(bear);
		super.onRestoreInstanceState(savedInstanceState);
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putBundle("bear", bear.getSaveBundle());
		super.onSaveInstanceState(outState);
	}
	
	

}
