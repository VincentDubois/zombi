package com.example.zombear.logic;
public class Jauge {
	
	int niveau;
	
	Jauge(){
		niveau = 50;
	}
	
	Jauge(int ini){
		niveau = ini;
	}
	
	
	
	
	public void augmenterJauge(int valeur){
		niveau += valeur;
		if(niveau > 100) niveau = 100;
	}
	
	public void diminuerJauge(int valeur){
		niveau -= valeur;
		if(niveau < 0) niveau = 0;
	}
	
	public int getNiveau(){
		return niveau;
	}
	
	
	
	public String toString(){
		return "Le niveau est de "+niveau+"/100.";
	}
	
}
