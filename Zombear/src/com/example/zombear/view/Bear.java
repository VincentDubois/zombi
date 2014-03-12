package com.example.zombear.view;


import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;

import com.example.zombear.R;
import com.example.zombear.logic.IA;

public class Bear {

	//private float SPEED = 0.1f;
	private float G = 0.005f;
	
	private Bitmap bmp;
	private Paint paintBlack = new Paint();

	public PointF posF,posS;
	public PointF targetF,targetS;
	
	private float z,vz;
	
	// champ IA
	private IA ia;
	
	//ajout d'un champ de type Deplacer
	public Deplacer deplace;
	
	
	boolean hasTarget;

	public Bear(Context context){
		bmp = BitmapFactory.decodeResource(context.getResources(), R.drawable.zombear_sprite);
		paintBlack.setColor(0x77000000);
		
		posF = new PointF(0.5f,0.5f);
		posS = new PointF();
		
		Background.toScreen(posF, posS);
		
		targetF = new PointF();
		targetS = new PointF();
		
		hasTarget = false;

		// instance ia
		ia = new IA(this);

		//initialisation du champ (instancier deplacer)
		// -------------------------------------------
		deplace = null;//new Deplacer(this,1,true,targetF);
		setTarget(1f,0.5f);

	}


	public void paint(Canvas canvas) {
//		float yp = off(y);
		float s =(float) (Background.scale(posS.y)*canvas.getHeight()*0.4);
		float xc = posS.x*canvas.getWidth(); //*floorToScreen(x,yp);
		float yc = (posS.y-z*Background.scale(posS.y))*canvas.getHeight(); //*yp;
		canvas.drawBitmap(bmp,null, new RectF(
				xc-s, yc-2*s,xc+s,yc)
		, null);
		
		if (hasTarget)
			canvas.drawCircle(targetS.x*canvas.getWidth(),
					targetS.y*canvas.getHeight(), 50, paintBlack );
	}

	
	public float dist(PointF from, PointF to){
		return PointF.length(to.x-from.x, to.y-from.y);
	}
	

	public void act() {

		//récupérer l'action en cours
		 deplace =ia.getAction(targetF);
		deplace.move();// remplacer par l'appel à la méthode move du champ
		

		
		if (z>0 || vz != 0){
			vz -= G;
			z += vz;
		}
		if (z<0){
			z = 0; vz = 0;
		}
	}




	public void setTarget(float xt, float yt) {
		if (yt<Background.y_min) yt = Background.y_min;
		
		targetS.set(xt, yt);
		
		Background.toFloor(targetF, targetS);
		
		if (targetF.x<-2) targetF.x = 2;
		if (targetF.x>3) targetF.x = 3; 
		
		hasTarget = true;
		
		
	}


	public boolean canJump() {
		return vz == 0 && z ==0;
	}


	public void jump() {
		vz = 10*G;
	}

}
