package com.example.zombear.logic;

import android.os.Bundle;

public class IA {
	
	public Etat etat;
	public int/*Action*/ action;
	
	//constructeur 
	public IA(){
		etat= new Etat();
		/*IA ia=new IA();*/
	}
	
	public IA(Bundle b){
		etat= new Etat(b);
		/*IA ia=new IA();*/
	}
	
	public int/*Action*/ getAction(){
		if(action==0){
			//envoyer une nouvelle action
			
			//test l'état de sommeil
			dormir();
			
			//test l'état de faim
			faim();
			
			//test l'état de bonheur/jouer
			jouer();
			
		}
		else{
			//continuer l'action en cours
			
		}
		return action;
	}
	
	
	//methode pour dormir
	public void dormir(){
		if(etat.getNiveauJauge(etat.sommeil)<=30){
			//action = "va dormir"
		}
	}	
	
		
	//methode pour manger
		public void faim(){
			if(etat.getNiveauJauge(etat.faim)<=30){
				//action = "va manger"
			}
		}	

	//methode pour jouer
		public void jouer(){
			if(etat.getNiveauJauge(etat.bonheur)<=30){
				//action = "va jouer"
			}
		}	
	
		public Bundle getSaveBundle(){
			return etat.getSaveBundle();
		}
		
	}

