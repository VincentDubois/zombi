package fr.univartois.iutlens.zombfox.logic;

import android.graphics.PointF;
import android.os.Bundle;
import android.util.Log;
import fr.univartois.iutlens.zombfox.view.Action;
import fr.univartois.iutlens.zombfox.view.Bear;
import fr.univartois.iutlens.zombfox.view.Deplacer;
import fr.univartois.iutlens.zombfox.view.Dormir;
import fr.univartois.iutlens.zombfox.view.Jouer;
import fr.univartois.iutlens.zombfox.view.Manger;
import fr.univartois.iutlens.zombfox.view.Wait;

public class IA {
	
	public Etat etat;
	public Action deplacement;
	public Bear zombie;
	public PointF target;
	public Wait attente;
	
	private PointF coordonneeDormir = new PointF(0,1);
	private PointF coordonneeFaim = new PointF(0,0);
	
	//constructeur 
	public IA(Bear bear){
		etat= new Etat();
		deplacement=null;
		zombie = bear;
		attente = null;
	}
	
	public IA(Bear bear, Bundle bundle){
		etat= new Etat(bundle);
		deplacement=null;
		zombie = bear;
		/*IA ia=new IA();*/
	}
	
	public Action getAction(PointF cible){
//		Log.d("IA.getAction()", "début");

		if(etat.faim.getNiveau() < 20){
			Log.d("jauge", "JE VAIS MANGER !");
			deplacement = new Manger(zombie);
		}
		if(etat.sommeil.getNiveau() < 20){
			Log.d("jauge", "JE VAIS DORMIR !");
			deplacement = new Dormir(zombie);
		}
		if(etat.ennui.getNiveau() > 80){
			Log.d("jauge", "JE VAIS JOUER !");
			deplacement = new Jouer(zombie);
		}
		
		
		if(deplacement==null || deplacement.isFini()){
			etat.update();
			Log.d("IA.getAction()",""+etat.getNiveauJauge(etat.sommeil));
			//envoyer une nouvelle action
			
			target=cible;
			
			if(etat.getNiveauJauge(etat.sommeil)<=48){
				if(Math.random()<=0.01){
					return deplacement = new Deplacer(zombie,0.15f,true,coordonneeFaim);
				}
				else
					deplacement = new Deplacer(zombie,0.1f,false,target);
			}
			
			
			deplacement = new Deplacer(zombie,0.1f,false,target);
			
		}
		
		return deplacement;
	}
	
	/*
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
			if(etat.getNiveauJauge(etat.ennui)<=60){
				deplacement = new Deplacer(zombie,10f,true,target);
				return true;
			}
			else{
				return false;
			}
		}	
	*/
		public Bundle getSaveBundle(){
			return etat.getSaveBundle();
		}
		
	}

