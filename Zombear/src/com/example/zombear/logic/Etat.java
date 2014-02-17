package com.example.zombear.logic;

import java.util.Date;

public class Etat {

	private Jauge faim, sommeil, bonheur, zombification;
	
	private Boolean malade;
	private Boolean endormi;
	
	private long lastUpdate;
	
	public Etat(){
		initialiser();
	}
	
	public void initialiser(){
		faim = new Jauge();
		sommeil = new Jauge();
		bonheur = new Jauge();
		zombification = new Jauge();
		lastUpdate = new Date().getTime();
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
	
	public void update(){
		long time = new Date().getTime();
		long tempsEcoule = time - lastUpdate;
		diminuerNiveauJauge(faim, lastUpdate/60000);
		diminuerNiveauJauge(sommeil, lastUpdate/120000);
		
		lastUpdate = time;
	}

}
