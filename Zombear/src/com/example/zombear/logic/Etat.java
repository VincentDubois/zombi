package com.example.zombear.logic;

public class Etat {

	public Jauge faim, sommeil, bonheur, zombification;
	
/*	public static void main(String[] args) {
		Etat zombie = new Etat();
		zombie.initialiser();
		
	}*/
	
	public void initialiser(){
		faim = new Jauge();
		sommeil = new Jauge();
		bonheur = new Jauge();
		zombification = new Jauge();
	}

}
