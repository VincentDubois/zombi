package com.example.zombear.logic;

public class IA {
	
	public Etat etat;
	public int/*Action*/ action;
	
	//constructeur 
	public IA(){
		etat= new Etat();
		IA ia=new IA();
		//etat.initialiser();
		
	}
	
	public int/*Action*/ getAction(){
		return action;
	}
	
	/*
	//methode pour dormir
	public void dormir(){
		if(etat.sommeil.niveau<=30){
			//appeler l'action "vadormir"
		}
	}	
	*/
		
	
	
		
	}

