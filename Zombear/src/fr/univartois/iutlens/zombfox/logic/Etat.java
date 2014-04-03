package fr.univartois.iutlens.zombfox.logic;

import java.util.Date;

import android.os.Bundle;
import android.util.Log;

public class Etat {

	private static final double TIME_FACTOR = 0.001;
	// 1 = Normal | 0.01 = Rapide | 0.001 = Ultra rapide (niveau diminution des jauges)
	
	public Jauge faim;
	public Jauge sommeil;
	public Jauge ennui;
	public Jauge zombification;
	
	public Boolean malade = false;
	public Boolean endormi = false;
	
	public long lastUpdate;
	
	public Etat(){
		faim = new Jauge();
		sommeil = new Jauge();
		ennui = new Jauge();
		zombification = new Jauge(80);
		lastUpdate = new Date().getTime();
		update();
	}
	
	public Etat(Bundle b){
		faim = new Jauge(b.getDouble("faim"));
		sommeil= new Jauge(b.getDouble("sommeil"));
		ennui = new Jauge(b.getDouble("ennui"));
		zombification = new Jauge(b.getDouble("zombification"));
		lastUpdate = b.getLong("lastUpdate");
		update();
	}
	
	public int getNiveauJauge(Jauge j){
		return j.getNiveau();
	}
	
	public void augmenterNiveauJauge(Jauge j, double v){
		j.augmenterJauge(v);
	}
	
	public void diminuerNiveauJauge(Jauge j, double v){
		j.diminuerJauge(v);
	}
	
	public Bundle getSaveBundle(){
		Bundle b = new Bundle();
		b.putDouble("faim", faim.getNiveau());
		b.putDouble("sommeil", sommeil.getNiveau());
		b.putDouble("ennui", ennui.getNiveau());
		b.putDouble("zombification", zombification.getNiveau());
		b.putLong("lastUpdate", lastUpdate);
		return b;
	}
	
	public void update(){
		long time = new Date().getTime();
		long tempsEcoule = time - lastUpdate;
		diminuerNiveauJauge(faim, (double)tempsEcoule/(600000*TIME_FACTOR));
		diminuerNiveauJauge(sommeil, (double)tempsEcoule/(1200000*TIME_FACTOR));
		augmenterNiveauJauge(ennui, (double)tempsEcoule/(800000*TIME_FACTOR));
		
		if(Math.random() <= 0.02){
			int moyenne = (int) ((2*getNiveauJauge(faim)+1.5*getNiveauJauge(ennui)+getNiveauJauge(sommeil))/4.5);
			if(moyenne <= 20){
				augmenterNiveauJauge(zombification, 0.3);
			}
			else if(moyenne <= 40){
				augmenterNiveauJauge(zombification, 0.2);
			}
			else if(moyenne <= 50){
				augmenterNiveauJauge(zombification, 0.1);
			}
			else if(moyenne <= 60){
				augmenterNiveauJauge(zombification, 0.05);
			}
			else if(moyenne <= 80){ 
				diminuerNiveauJauge(zombification, 0.05);
			}
			else if(moyenne <= 90){
				diminuerNiveauJauge(zombification, 0.1);
			}
			else{
				diminuerNiveauJauge(zombification, 0.2);
			}
			
			Log.d("zombification", "Niveau de la zombification : "+getNiveauJauge(zombification));
		}
		
		Log.d("jauge", getNiveauJauge(faim) + " - " + getNiveauJauge(sommeil) + " - " + getNiveauJauge(ennui));
		lastUpdate = time;
	}

}
