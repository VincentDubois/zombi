package com.example.zombear.logic;

import com.example.zombear.view.Bear;
import com.example.zombear.view.Deplacer;

public class IA {
	
	public Etat etat;
	public Deplacer deplacement;
	public Bear zombie;
	
	//constructeur 
	public IA(Bear bear){
		etat= new Etat();
		deplacement=null;
		zombie = bear;
	}
	
	public Deplacer getAction(){
		if(deplacement==null /*|| deplacement.finished()*/){
			//envoyer une nouvelle action
			
			//test l'état de sommeil
			if(dormir())
				return deplacement;
			
			//test l'état de faim
			if(faim())
				return deplacement;
			
			//test l'état de bonheur/jouer
			if(jouer())
				return deplacement;
			
			deplacement = new Deplacer(zombie,0.1f);
		}
		return deplacement;
	}
	
	
	//methode pour dormir
	public boolean dormir(){
		if(etat.getNiveauJauge(etat.sommeil)<=30){

			deplacement = new Deplacer(zombie,0.01f);
			return true;
		}
		else{
			return false;
		}
	}	
	
		
	//methode pour manger
		public boolean faim(){
			if(etat.getNiveauJauge(etat.faim)<=30){
				deplacement = new Deplacer(zombie,0.5f);
				return true;
			}
			else if(etat.getNiveauJauge(etat.faim)<=60){
				deplacement = new Deplacer(zombie,1f);
				return true;
			}
			else{
				return false;
			}
		}	

	//methode pour jouer
		public boolean jouer(){
			if(etat.getNiveauJauge(etat.bonheur)>=60){
				deplacement = new Deplacer(zombie,10f);
				return true;
			}
			else{
				return false;
			}
		}	
	
		
	}

