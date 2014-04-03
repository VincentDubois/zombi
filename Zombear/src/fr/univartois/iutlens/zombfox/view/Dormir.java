package fr.univartois.iutlens.zombfox.view;

import android.util.Log;


public class Dormir implements Action {
	private int time = 1000; //en ms
	private Bear bear;
	private boolean fini;

	private int index = 16;

	private int anim = 0;

	public Dormir(Bear bear){
		this.bear = bear;
		Log.d("Dormir C",""+index);
	}

	public void move(){
		anim = (anim+1) %20;
		if (Math.random()< 0.1f){
			if(bear.ia.etat.getNiveauJauge(bear.ia.etat.sommeil) < 90 ){
				bear.ia.etat.augmenterNiveauJauge(bear.ia.etat.sommeil, 1);
			}
			else{
				fini = true;
			}
		}
	}

	@Override
	public int getIndex(){
//		Log.d("Dormir",""+index+ anim/10);
		return index + anim/10;
	}

	@Override
	public boolean isFini(){
		return fini;
	}

}
