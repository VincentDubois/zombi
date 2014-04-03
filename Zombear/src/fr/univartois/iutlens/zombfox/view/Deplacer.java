package fr.univartois.iutlens.zombfox.view;


import android.graphics.PointF;
import fr.univartois.iutlens.zombfox.MainActivity;
import fr.univartois.iutlens.zombfox.R;


public class Deplacer implements Action {


	public float SPEED = 1f;

	
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


	private int index = 20;
	
	private int anim = 0;
	
	public Deplacer(Bear bear,float speed, boolean jump, PointF target, int son ) {
		super();
		this.bear = bear;
		this.speed = speed*SPEED;
		this.jump = jump;
		this.target = target;
		fini =false;
		if (son > -1){
		MainActivity.playSound(son);
		}
	}
	public Deplacer(Bear bear,float speed, boolean jump, PointF target, Action mouvementSuivant) {
		this(bear, speed, jump, target, -1);
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
		return index + anim/2;
	}
	

	
	/* (non-Javadoc)
	 * @see com.example.zombear.view.Action#move()
	 */
	@Override
	public void move() { //mettre en champ float speed

		
		anim = (anim+1) %6;
		
		if (!fini){  //--------------------------------
			float d = dist(target,bear.posF);
			if (dist(target,bear.posF) < speed) {
				bear.posF.set(target);
				fini = true; //arrivé a dst...
				index = 20;
			} else {
				if ((target.x-bear.posF.x)<0){
					index = 0;
				}else {
					index = 4;
				}
				if ((target.y-bear.posF.y)>0){
					index += 8;
				}
				bear.posF.offset((target.x-bear.posF.x)/d*speed,
						    (target.y-bear.posF.y)/d*speed);  //bear.targetF remplacer par target
			}
			Background.toScreen(bear.posF, bear.posS);
			if (jump==true && bear.canJump()){
				bear.jump();
			}
		} else {
			mouvementSuivant.move();
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
