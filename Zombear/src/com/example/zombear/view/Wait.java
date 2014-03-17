package com.example.zombear.view;

import com.example.zombear.view.GameView.RefreshHandler;

import android.graphics.PointF;
import android.os.Handler;

public class Wait extends Deplacer {
	private int time = 1000; //en ms
	private int cpt;

	public Wait(Bear bear, float speed, boolean jump, PointF target, int cpt) {
		super(bear, speed, jump, target);
		this.cpt= cpt;
	}
	
	public void move()  { //mettre en champ float speed
		if (cpt !=0){
			cpt--;
		}
		else{
			fini =true;
		}
		if (jump==true && bear.canJump()){
			bear.jump();
		}
	}
	
	public int getIndex(){
		return 1;
	}

}
