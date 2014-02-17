package com.example.zombear.view;

import android.graphics.PointF;


public class Deplacer {


	public float SPEED = 0.1f;

	
 // champ bear
	public Bear bear;
	public float speed;
	
	public Deplacer(Bear bear,float speed) {
		super();
		this.bear = bear;
		this.speed = speed*SPEED;

	}


	public float dist(PointF from, PointF to){
		return PointF.length(to.x-from.x, to.y-from.y);
	}

	
	public void move() { //mettre en champ float speed
		if (bear.hasTarget){  //--------------------------------
			float d = dist(bear.targetF,bear.posF);
			if (dist(bear.targetF,bear.posF) < speed) {
				bear.posF.set(bear.targetF);
				bear.hasTarget = false;
			} else {
				bear.posF.offset((bear.targetF.x-bear.posF.x)/d*speed,
						    (bear.targetF.y-bear.posF.y)/d*speed);
			}
			Background.toScreen(bear.posF, bear.posS);
		}
	}





}
