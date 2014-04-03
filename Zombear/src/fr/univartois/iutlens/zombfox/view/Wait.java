package fr.univartois.iutlens.zombfox.view;

import fr.univartois.iutlens.zombfox.view.GameView.RefreshHandler;

import android.graphics.PointF;
import android.os.Handler;

public class Wait implements Action {
	
	// champ bear
	public Bear bear;
	// jump
	public boolean jump;
	//boolean fini
	protected boolean fini;
	//mouvement suivant
	public Action mouvementSuivant;
		
	private int cpt;
	private int index;

	public Wait(Bear bear, boolean jump, int cpt, boolean ferme, boolean baille) {
		super();
		this.bear = bear;
		this.jump = jump;
		this.cpt= cpt;
		index = 24;
		if (ferme==true){
			index=28;
		}else {
			index=24;
		}
		if (baille==true){
			index=27;
		}else {
			index=24;
		}
	}
	
	public void move()  { //mettre en champ float speed
		if (cpt !=0){
			cpt--;
		}
		else{
			fini = true;
		}
		if (jump==true && bear.canJump()){
			bear.jump();
		}
	}
	
	public int getIndex(){
		return index;
	}

	@Override
	public boolean isFini() {
		return fini && (mouvementSuivant == null || mouvementSuivant.isFini() );
	}

}
