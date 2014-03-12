package com.example.zombear.view;

import android.graphics.PointF;


public class Deplacer {


	public float SPEED = 0.1f;

	
 // champ bear
	public Bear bear;
	public float speed;
	// jump
	public boolean jump;
	
	// target 
	public PointF target;
	// 
	
	//boolean fini
	public boolean fini;
	
	
	
	public Deplacer(Bear bear,float speed, boolean jump, PointF target) {
		super();
		this.bear = bear;
		this.speed = speed*SPEED;
		this.jump = jump;
		this.target = target;
		this.fini = false;
	}


	public float dist(PointF from, PointF to){
		return PointF.length(to.x-from.x, to.y-from.y);
	}
	
	
	public int getIndex(){
		return 0;
	}
	

	
	public void move() { //mettre en champ float speed
		if (!fini){  //--------------------------------
			float d = dist(target,bear.posF);
			if (dist(target,bear.posF) < speed) {
				bear.posF.set(target);
				fini = true; //arrivé a dst...
			} else {
				bear.posF.offset((target.x-bear.posF.x)/d*speed,
						    (target.y-bear.posF.y)/d*speed);  //bear.targetF remplacer par target
			}
			Background.toScreen(bear.posF, bear.posS);
		}
		if (jump==true && bear.canJump()){
			bear.jump();
		}
	}
}
