package com.example.zombear.view;

import com.example.zombear.view.GameView.RefreshHandler;

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

	public Wait(Bear bear, boolean jump, int cpt) {
		super();
		this.bear = bear;
		this.jump = jump;
		this.cpt= cpt;
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
		return 20;
	}

	@Override
	public boolean isFini() {
		return fini && (mouvementSuivant == null || mouvementSuivant.isFini() );
	}

}
