package fr.univartois.iutlens.zombfox;

import android.app.Activity;
import android.app.AlertDialog;
import android.view.Gravity;
import android.content.Intent;
import android.content.SharedPreferences;
import android.media.MediaPlayer;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import fr.univartois.iutlens.zombfox.view.Bear;
import fr.univartois.iutlens.zombfox.view.GameView;

public class MainActivity extends Activity {
	
	
	Bear bear;
	private SharedPreferences myPrefs;

	@Override
	protected void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState);
		myPrefs = this.getSharedPreferences("myPrefs", MODE_WORLD_READABLE);
		
		Float faim = myPrefs.getFloat("faim", 50f);
		Float sommeil = myPrefs.getFloat("sommeil", 50f);
		Float ennui = myPrefs.getFloat("ennui", 50f);
		Float zombification = myPrefs.getFloat("zombification", 80f);
		Long lastUpdate = myPrefs.getLong("lastUpdate", 0);
		
		Bundle b = new Bundle();
		b.putDouble("faim", (double) faim);
		b.putDouble("sommeil", (double) sommeil);
		b.putDouble("ennui", (double) ennui);
		b.putDouble("zombification", (double) zombification);
		b.putLong("lastUpdate", lastUpdate);
		
		setContentView(R.layout.activity_main); 

		new AlertDialog.Builder(this).setTitle("Zombfox").setMessage(" Bienvenue dans l'univers Zombfox, prenez bien soin de lui ! ").setNeutralButton("Entrez",null).show(); 
		
		if(savedInstanceState == null){
			if(lastUpdate != 0){
				bear = new Bear(this, b);
			}
			else{
				bear = new Bear(this);
			}
		}
		else{
			bear = new Bear(this, savedInstanceState.getBundle("bear"));
		}
		((GameView) findViewById(R.id.gameView1)).setBear(bear);
		
		activity =this;
		
		if(bear.ia.etat.getNiveauJauge(bear.ia.etat.faim) == 0 && bear.ia.etat.getNiveauJauge(bear.ia.etat.sommeil) == 0 && bear.ia.etat.getNiveauJauge(bear.ia.etat.zombification) == 100){
			new AlertDialog.Builder(this).setTitle("Zombear").setMessage("T'AS TUÉ TON ZOMBI").setNeutralButton("Le résurectionner",null).show(); 
		}
		
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
			Intent intent=new Intent(MainActivity.this, Apropos.class);
			startActivity(intent);
			default:
				return super.onOptionsItemSelected(item);
				
		
		}
		
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
		ed.putFloat("ennui", (float) b.getDouble("ennui"));
		ed.putFloat("zombification", (float) b.getDouble("zombification"));
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
	
