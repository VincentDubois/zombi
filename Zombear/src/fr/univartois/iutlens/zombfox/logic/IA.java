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
	public Bear zombie;

	public Action deplacement;
	public Action attente;
	
	public PointF target;
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
/*
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
*/		
		
		if(deplacement==null || deplacement.isFini()){
			etat.update();
			Log.d("IA.getAction()",""+etat.getNiveauJauge(etat.sommeil));
			//envoyer une nouvelle action
			target=cible;
			
			
			//Le sommeil est supérieur à 60, 
			//il ne va pas dormir au click
			if(etat.getNiveauJauge(etat.sommeil)>60){
				//TODO empecher de dormir
				deplacement = new Deplacer(zombie,0.1f,false,target);
			}
			
			
			//Le sommeil est entre 40 et 20 
			//=> environ une chance sur 1000 d'aller vers son coussin
			//=> environ une chance sur 1000 de bailler
			//=> environ une chance sur 1000 de fermer les yeux
			else if(etat.getNiveauJauge(etat.sommeil)<=40 && etat.getNiveauJauge(etat.sommeil)>20){
				
				//=> environ une chance sur 1000 d'aller vers son coussin
				if(Math.random()<=0.001/(etat.getNiveauJauge(etat.sommeil)+1)){
					attente = new Wait(zombie,false,100);
					return deplacement = new Deplacer(zombie,0.1f,false,coordonneeDormir,attente);
				}
				//=> environ une chance sur 1000 de bailler
				if(Math.random()<=0.001/(etat.getNiveauJauge(etat.sommeil)+1)){
					//TODO Bailler
					return deplacement = new Wait(zombie,false,100);
				}
				
				//=> environ une chance sur 1000 de fermer les yeux
				if(Math.random()<=0.001/(etat.getNiveauJauge(etat.sommeil)+1)){
					//TODO fermer les yeux
					return deplacement = new Wait(zombie,true,100);
				}
				else
					deplacement = new Deplacer(zombie,0.03f,false,target);
			}
			
			
			//Le sommeil est inférieur à 20 
			//=> environ une chance sur 5000 d'aller dormir tout seul
			//=> ne mange plus
			//=> ne joue plus
			else if(etat.getNiveauJauge(etat.sommeil)<=20){
				if(Math.random()<=0.0002/(etat.getNiveauJauge(etat.sommeil)+1)){
					// Semblant de fonction dormir
					attente = new Wait(zombie,false,1000);
					etat.augmenterNiveauJauge(etat.sommeil,40);
					return deplacement = new Deplacer(zombie,0.1f,false,coordonneeDormir,attente);
				}
				//TODO empecher de dormir
				//TODO empecher de jouer
				else
					deplacement = new Deplacer(zombie,0.015f,false,target);
			}
			
			
			//Aucune des conditions précédentes n'a été rempli
			else{
				deplacement = new Deplacer(zombie,0.1f,false,target);
			}
			
		}
		
		return deplacement;
	}

		public Bundle getSaveBundle(){
			return etat.getSaveBundle();
		}
		
	}

