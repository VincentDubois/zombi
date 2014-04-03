package fr.univartois.iutlens.zombfox.view;


public class Manger implements Action {
	private int time = 1000; //en ms
	private Bear bear;
	private boolean fini;

	public Manger(Bear bear){
		this.bear = bear;
	}
	
	public void move(){
		if(bear.ia.etat.getNiveauJauge(bear.ia.etat.faim) < 90){
			bear.ia.etat.augmenterNiveauJauge(bear.ia.etat.faim, 1);
		}
		else{
			fini = true;
		}
	}
	
	public int getIndex(){
		
		return 1;
	}

	@Override
	public boolean isFini(){
		return fini;
	}

}
