package com.example.zombear.logic;

import java.util.Date;

import android.os.Bundle;
import android.util.Log;

public class Etat {

	private static final double TIME_FACTOR = 0.01;
	// 1 = Normal | 0.01 = Rapide
	
	public Jauge faim;
	public Jauge sommeil;
	public Jauge ennui;
	public Jauge zombification;
	
	public Boolean malade = false;
	public Boolean endormi = false;
	
	public long lastUpdate;
	
	public Etat(){
		faim = new Jauge();
		sommeil = new Jauge();
		ennui = new Jauge();
		zombification = new Jauge();
		lastUpdate = new Date().getTime();
		update();
	}
	
	public Etat(Bundle b){
		faim = new Jauge(b.getDouble("faim"));
		sommeil= new Jauge(b.getDouble("sommeil"));
		ennui = new Jauge(b.getDouble("ennui"));
		zombification = new Jauge();
		lastUpdate = b.getLong("lastUpdate");
		update();
	}
	
	public int getNiveauJauge(Jauge j){
		return j.getNiveau();
	}
	
	public void augmenterNiveauJauge(Jauge j, double v){
		j.augmenterJauge(v);
	}
	
	public void diminuerNiveauJauge(Jauge j, double v){
		j.diminuerJauge(v);
	}
	
	public Bundle getSaveBundle(){
		Bundle b = new Bundle();
		b.putDouble("faim", faim.getNiveau());
		b.putDouble("sommeil", sommeil.getNiveau());
		b.putDouble("ennui", ennui.getNiveau());
		b.putLong("lastUpdate", lastUpdate);
		return b;
	}
	
	public void update(){
		long time = new Date().getTime();
		long tempsEcoule = time - lastUpdate;
		diminuerNiveauJauge(faim, (double)tempsEcoule/(600000*TIME_FACTOR));
		if(endormi) augmenterNiveauJauge(sommeil, (double)tempsEcoule/(800000*TIME_FACTOR));
		else diminuerNiveauJauge(sommeil, (double)tempsEcoule/(1200000*TIME_FACTOR));
		Log.d("jauge", getNiveauJauge(faim) + " - " + getNiveauJauge(sommeil));
		lastUpdate = time;
	}

}
