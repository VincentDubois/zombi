package fr.univartois.iutlens.zombfox.view;


public class Manger implements Action {
	private int time = 1000; //en ms
	private Bear bear;
	private boolean fini;
	
	private int index = 18;
	
	private int anim = 0;

	public Manger(Bear bear){
		this.bear = bear;
	}
	
	public void move(){
		anim = (anim+1) %20;
		if(bear.ia.etat.getNiveauJauge(bear.ia.etat.faim) < 90){
			bear.ia.etat.augmenterNiveauJauge(bear.ia.etat.faim, 1);
		}
		else{
			fini = true;
		}
	}
	
	public int getIndex(){
		
		return index + anim/10;
	}

	@Override
	public boolean isFini(){
		return fini;
	}

}
