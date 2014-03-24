package fr.univartois.iutlens.zombfox.view;

import fr.univartois.iutlens.zombfox.utils.SpriteSheet;

import android.content.Context;
import android.graphics.Canvas;
import android.graphics.Paint;
import android.graphics.PointF;
import android.graphics.RectF;

public class Item {
/***********************
 * CETTE CLASSE GERE LES DIFFERENTS OBJETS (gamelle, os,...)
 * VERSION 1 - DEFENIN Maxime
 */
	/**
	 * @param args
	 */
	private boolean dispo; //Disponnibilit� en fonction des jauges
	private  int etat; //Etat visuel de l'objet (ex : plein/vide...)
	private int position_sprite; //Numero du sprite
	
	
	private float SPEED = 0.01f;
	private float G = 0.005f;
	private float PROPORTION_YC = 0.75f; //entre 0 et 1
//	private Bitmap bmp;
	private SpriteSheet sprite;
	private Paint paintBlack = new Paint();

	private PointF posF,posS;
//	private PointF targetS;
	
	private float z,vz;
	
	
	
	
	
	public Item(boolean dispo, int etat, int position_sprite, SpriteSheet sprite) {
		super();
		this.dispo = dispo;
		this.etat = etat;
		this.position_sprite = position_sprite;
		paintBlack.setColor(0x77000000);
		this.posS = new PointF(0.5f,0.5f);
		this.sprite = sprite;
	}



	//Methodes
	public  boolean setDispo(){
		return dispo;
	}
	public  void getDispo(boolean b){
		dispo = b;
	}
	
	public  int setEtat(){
		return etat;
	}
	public  void getEtat(int e){
		etat = e;
	}	
	
	public int setPositionSprite(){
		return position_sprite;
	}
	
	public void getPositionSprite(int p){
		position_sprite = p;
	}
	
	
	
	
	
	
	
	
	
	


	public void paint(Canvas canvas) {
	// TODO Auto-generated method stub
		float s = canvas.getHeight()*0.1f;
		float xc = posS.x*canvas.getWidth(); //*floorToScreen(x,yp);
		float yc = posS.y*canvas.getHeight()+70; //*yp;
		//PARAMETRE : SPRITE(ID) rectF(-x,-y,+x,+y)
		canvas.drawBitmap(sprite.getBitmap(0),null, new RectF(
				xc-s, yc-2*PROPORTION_YC*s,xc+s,yc+(2*s-2*s*PROPORTION_YC))
		, null);
		
		
			canvas.drawCircle(xc,
					yc, 50, paintBlack );
	}

}
