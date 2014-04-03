package fr.univartois.iutlens.zombfox.view;


public class Jouer implements Action {
	private int time = 1000; //en ms
	private Bear bear;
	private boolean fini;

	public Jouer(Bear bear){
		this.bear = bear;
	}
	
	public void move(){
		if(bear.ia.etat.getNiveauJauge(bear.ia.etat.ennui) > 10){
			bear.ia.etat.diminuerNiveauJauge(bear.ia.etat.ennui, 1);
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
