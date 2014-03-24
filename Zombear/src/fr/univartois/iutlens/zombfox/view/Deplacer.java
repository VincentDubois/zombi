package fr.univartois.iutlens.zombfox.view;

import android.graphics.PointF;


public class Deplacer implements Action {


	public float SPEED = 0.1f;

	
	// champ bear
	public Bear bear;
	public float speed;
	// jump
	public boolean jump;
	
	// target 
	public PointF target;
	
	//boolean fini
	protected boolean fini;
	
	//mouvement suivant
	public Action mouvementSuivant;
	
	
	
	public Deplacer(Bear bear,float speed, boolean jump, PointF target) {
		super();
		this.bear = bear;
		this.speed = speed*SPEED;
		this.jump = jump;
		this.target = target;
		fini =false;
	}
	
	public Deplacer(Bear bear,float speed, boolean jump, PointF target, Action mouvementSuivant) {
		this(bear, speed, jump, target);
		this.mouvementSuivant = mouvementSuivant;
	}


	public float dist(PointF from, PointF to){
		return PointF.length(to.x-from.x, to.y-from.y);
	}
	
	
	/* (non-Javadoc)
	 * @see com.example.zombear.view.Action#getIndex()
	 */
	@Override
	public int getIndex(){
		return 0;
	}
	

	
	/* (non-Javadoc)
	 * @see com.example.zombear.view.Action#move()
	 */
	@Override
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


	/* (non-Javadoc)
	 * @see com.example.zombear.view.Action#isFini()
	 */
	@Override
	public boolean isFini() {
		return fini && (mouvementSuivant == null || mouvementSuivant.isFini() );
	}



}
