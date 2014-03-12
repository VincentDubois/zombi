package com.example.zombear.logic;

import android.graphics.PointF;

import com.example.zombear.view.Bear;
import com.example.zombear.view.Deplacer;
import com.example.zombear.view.Wait;

import android.os.Bundle;
import android.util.Log;

public class IA {
	
	public Etat etat;
	public Deplacer deplacement;
	public Bear zombie;
	public PointF target;
	
	private PointF coordonneeDormir = new PointF(0,1);
	private PointF coordonneeFaim = new PointF(0,0);
	
	//constructeur 
	public IA(Bear bear){
		etat= new Etat();
		deplacement=null;
		zombie = bear;
		/*IA ia=new IA();*/
	}
	
	public IA(Bundle b){
		etat= new Etat(b);
		/*IA ia=new IA();*/
	}
	
	public Deplacer getAction(PointF cible){
//		Log.d("IA.getAction()", "début");

		if(deplacement==null || deplacement.fini){
			Log.d("IA.getAction()", "nouveau");
			//envoyer une nouvelle action
			
			target=cible;
			
//			//test l'état de sommeil
//			if(dormir())
//				return deplacement;
//			
//			//test l'état de faim
//			if(faim())
//				return deplacement;
//			
//			//test l'état de bonheur/jouer
//			if(jouer())
//				return deplacement;
			if(Math.random()<=0.3){
				deplacement = new Wait(zombie,0.05f,false,coordonneeDormir,100);

			}
			else if(Math.random()<=0.3){
				deplacement = new Wait(zombie,0.05f,true,coordonneeDormir,100);

			}
			
			else if(Math.random()>=0.5){
				deplacement = new Deplacer(zombie,0.40f,true,coordonneeFaim);

			}
			else{
				deplacement = new Deplacer(zombie,0.1f,false,target);
			}
		}
		
		return deplacement;
	}
	
	
	//methode pour dormir
	public boolean dormir(){
		if(etat.getNiveauJauge(etat.sommeil)<=60){

			deplacement = new Deplacer(zombie,0.05f,true,coordonneeDormir);
			
			return true;
		}
		
		else{
			return false;
		}
	}	
	
		
	//methode pour manger
		public boolean faim(){
			if(etat.getNiveauJauge(etat.faim)<=30){
				deplacement = new Deplacer(zombie,0.5f,true,target);
				return true;
			}
			else if(etat.getNiveauJauge(etat.faim)<=60){
				deplacement = new Deplacer(zombie,1f,true,coordonneeFaim);
				return true;
			}
			else{
				return false;
			}
		}	

	//methode pour jouer
		public boolean jouer(){
			if(etat.getNiveauJauge(etat.bonheur)>=60){
				deplacement = new Deplacer(zombie,10f,true,target);
				return true;
			}
			else{
				return false;
			}
		}	
	
		public Bundle getSaveBundle(){
			return etat.getSaveBundle();
		}
		
	}

