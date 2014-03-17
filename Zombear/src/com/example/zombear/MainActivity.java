package com.example.zombear;

import com.example.zombear.view.Bear;
import com.example.zombear.view.GameView;

import android.os.Bundle;
import android.app.Activity;
import android.app.AlertDialog;
import android.view.Gravity;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.Toast;

public class MainActivity extends Activity {
	
	Bear bear;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_main);
		new AlertDialog.Builder(this).setTitle("Zombear").setMessage(" Bienvenue dans l'univers Zombear, prenez bien soin de lui ! ").setNeutralButton("Entrez",null).show(); 
		
		
		
		bear = new Bear(this);
		((GameView) findViewById(R.id.gameView1)).setBear(bear);
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}
	
	public boolean onOptionsItemSelected(MenuItem item) {
		
		switch (item.getItemId()){
		
		case R.id.action_settings:
			new AlertDialog.Builder(this).setTitle("A propos").setMessage(" Application réalisée par le groupe Web 1  \n 2014").setNeutralButton("Fermer",null).show(); return true;
			
			default:
				return super.onOptionsItemSelected(item);
				
		
		}
		
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
