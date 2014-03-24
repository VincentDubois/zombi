package fr.univartois.iutlens.zombfox.view;


public class Dormir implements Action {
	private int time = 1000; //en ms
	private Bear bear;
	private boolean fini;

	public Dormir(Bear bear){
		this.bear = bear;
	}
	
	public void move(){
		if(bear.ia.etat.getNiveauJauge(bear.ia.etat.sommeil) < 90){
			bear.ia.etat.augmenterNiveauJauge(bear.ia.etat.sommeil, 1);
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
