package com.example.zombear.logic;

import java.util.Date;

import android.os.Bundle;
import android.util.Log;

public class Etat {

	public Jauge faim;

	public Jauge sommeil;

	public Jauge bonheur;

	public Jauge zombification;
	
	public Boolean malade = false;
	public Boolean endormi = false;
	
	public long lastUpdate;
	
	public Etat(){
		Log.d("Zomb", "Initialisation");
		faim = new Jauge();
		sommeil = new Jauge();
		bonheur = new Jauge();
		zombification = new Jauge();
		lastUpdate = new Date().getTime();
		Log.d("Zomb", "Update");
		update();
	}
	
	public Etat(Bundle b){
		Log.d("Zomb", "Initialisation via un Bundle");
		faim = new Jauge(b.getDouble("faim"));
		sommeil= new Jauge(b.getDouble("sommeil"));
		bonheur = new Jauge();
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
		b.putLong("lastUpdate", lastUpdate);
		return b;
	}
	
	public void update(){
		long time = new Date().getTime();
		long tempsEcoule = time - lastUpdate;
		diminuerNiveauJauge(faim, (double)tempsEcoule/6000);
		if(endormi) augmenterNiveauJauge(sommeil, (double)tempsEcoule/8000);
		else diminuerNiveauJauge(sommeil, (double)tempsEcoule/12000);
		Log.d("update", "Faim : "+faim+" | Sommeil : "+sommeil);
		lastUpdate = time;
	}

}
