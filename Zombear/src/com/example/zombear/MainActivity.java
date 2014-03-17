package com.example.zombear;

import android.app.Activity;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.util.Log;
import android.view.Menu;

import com.example.zombear.view.Bear;
import com.example.zombear.view.GameView;

public class MainActivity extends Activity {
	
	Bear bear;
	private SharedPreferences myPrefs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		myPrefs = this.getSharedPreferences("myPrefs", MODE_WORLD_READABLE);
		
		Float faim = myPrefs.getFloat("faim", 50f);
		Float sommeil = myPrefs.getFloat("sommeil", 50f);
		Long lastUpdate = myPrefs.getLong("lastUpdate", 0);
		
		Bundle b = new Bundle();
		b.putDouble("faim", (double) faim);
		b.putDouble("sommeil", (double) sommeil);
		b.putLong("lastUpdate", lastUpdate);
		
		setContentView(R.layout.activity_main);
		
		if(savedInstanceState == null){
			if(lastUpdate != 0){
				Log.d("lancement", "1");
				bear = new Bear(this, b);
			}
			else{
				Log.d("lancement", "2");
				bear = new Bear(this);
			}
		}
		else{
			Log.d("lancement", "3");
			bear = new Bear(this, savedInstanceState.getBundle("bear"));
		}
		((GameView) findViewById(R.id.gameView1)).setBear(bear);
		
		activity =this;
		
	}

	@Override
	public boolean onCreateOptionsMenu(Menu menu) {
		// Inflate the menu; this adds items to the action bar if it is present.
		getMenuInflater().inflate(R.menu.main, menu);
		return true;
	}

	@Override
	protected void onRestoreInstanceState(Bundle savedInstanceState) {
		super.onRestoreInstanceState(savedInstanceState); 
	}

	@Override
	protected void onSaveInstanceState(Bundle outState) {
		outState.putBundle("bear", bear.getSaveBundle());
		super.onSaveInstanceState(outState);
	}
	
	@Override
	protected void onPause(){
		super.onPause();
		
		Bundle b = bear.getSaveBundle();
		
		SharedPreferences.Editor ed = myPrefs.edit();
		ed.putFloat("faim", (float) b.getDouble("faim"));
		ed.putFloat("sommeil", (float) b.getDouble("sommeil"));
		ed.putLong("lastUpdate", b.getLong("lastUpdate"));
		ed.commit();
	}
	
    private MediaPlayer mPlayer = null;
    private static MainActivity activity;
    
    public void stopSound() {
        super.onPause();
        if(mPlayer != null) {
            mPlayer.stop();
            mPlayer.release();
        }
    }
    
    public static void playSound(int resId) {
    	if (activity == null) return;
    	
    	
        if(activity.mPlayer != null) {
        	activity.mPlayer.stop();
        	activity.mPlayer.release();
        }
        activity.mPlayer = MediaPlayer.create(activity, resId);
        activity.mPlayer.start();
    }
}
	
