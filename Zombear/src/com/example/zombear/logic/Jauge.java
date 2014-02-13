package com.example.zombear.logic;
public class Jauge {

	private double niveau;

	Jauge(){
		niveau = 50.0;
	}

	Jauge(double ini){
		niveau = ini;
	}


	public void augmenterJauge(double valeur){
		niveau += valeur;
		if(niveau > 100) niveau = 100;
	}

	public void diminuerJauge(double valeur){
		niveau -= valeur;
		if(niveau < 0) niveau = 0;
	}

	public int getNiveau(){
		return (int) niveau;
	}

	public String toString(){
		return "Le niveau est de "+(int) niveau+"/100.";
	}



}
