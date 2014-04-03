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
	private String action;
	private float SPEED = 0.01f;
	private float G = 0.005f;
	private float PROPORTION_YC = 0.5f; //entre 0 et 1
//	private Bitmap bmp;
	private SpriteSheet sprite;
	private Paint paintBlack = new Paint();

	private PointF posF,posS;
//	private PointF targetS;
	
	private float z,vz;
	
	
	
	
	
	public Item(boolean dispo, int etat, int position_sprite, String action, SpriteSheet sprite,
			PointF posS) {
		super();
		this.dispo = dispo;
		this.etat = etat;
		this.action = action;
		this.position_sprite = position_sprite;
		paintBlack.setColor(0x77000000);
		
		this.sprite = sprite;
		this.posS=posS;
	}



	//Methodes
	public  boolean getDispo(){
		return dispo;
	}
	public  void setDispo(boolean b){
		dispo = b;
	}
	
	public  int getEtat(){
		return etat;
	}
	public  void setEtat(int e){
		etat = e;
	}	
	
	public int getPositionSprite(){
		return position_sprite;
	}
	
	public void setPositionSprite(int p){
		position_sprite = p;
	}
	
	public PointF getPosS(){
		return posS;
	}
	
	public void setPosS(PointF p){
		posS = p;
	}
	
	public String getAction() {
		return action;
	}



	public void setAction(String action) {
		this.action = action;
	}

	
	
	
	
	


	public void paint(Canvas canvas) {
	// TODO Auto-generated method stub
		float s = canvas.getHeight()*0.1f;
		float xc = posS.x*canvas.getWidth(); //*floorToScreen(x,yp);
		float yc = posS.y*canvas.getHeight(); //*yp;
		//PARAMETRE : SPRITE(ID) rectF(-x,-y,+x,+y)
		canvas.drawBitmap(sprite.getBitmap(position_sprite),null, new RectF(
				xc-s, yc-2*PROPORTION_YC*s,xc+s,yc+(2*s-2*s*PROPORTION_YC))
		, null);
		
		
		/*	canvas.drawCircle(xc,
					yc, 50, paintBlack );*/
	}

}
