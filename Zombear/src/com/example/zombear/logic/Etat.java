package com.example.zombear.logic;

import java.util.Date;

public class Etat {

	public Jauge faim;

	public Jauge sommeil;

	public Jauge bonheur;

	private Jauge zombification;
	
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
		long tempsEcoule = new Date().getTime() - lastUpdate;
		diminuerNiveauJauge(faim, lastUpdate/60000);
		diminuerNiveauJauge(sommeil, lastUpdate/120000);
		
		lastUpdate = new Date().getTime();
	}

}
