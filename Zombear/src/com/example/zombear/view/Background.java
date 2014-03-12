package com.example.zombear.view;

import com.example.zombear.R;
import com.example.zombear.R.drawable;

import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Canvas;
import android.graphics.PointF;
import android.graphics.Rect;

public class Background {
	private Bitmap background;
	
	
	//                          |
	//                          |
	//yf                        |
	//       /\           .     |
	//      /  \            .   |
	//y_m  /____\    1        . |
	//    /      \              |
	//1  /________\  0          | .

	// (1-y)/d = (y-yf)/d0
	// d0 -  y.d0 = y.d -yf.d
	// y  = (d0+yf.d)/(d+d0)
	// d = d0*(1-y)/(y-yf);
	
	public final static float 
		xf = 0.66f,
		yf = 0.5f,
		y_min = 0.6f,
		d0 = 0.2f;
	
	public static final int FOOD = 0;
	public static final int BED = 1;
	public static final int PLAYER = 2;
	
	static float[][] coord = {{0,0},{-1,1},{0.5f,0}};
	
	public static void setPoint(PointF p, int ndx){
		p.set(coord[ndx][0],coord[ndx][1]);
	}

	private static float off(float d){
		return (d0+yf*d)/(d+d0);
	}

	private static float proj(float y){
		return  d0*(1-y)/(y-yf);
	}

	public static float scale(float y){
		return (y-yf)/(1+d0-yf);
	}
	
	public static void toScreen(PointF floor, PointF screen){
		screen.y = off(floor.y);
		screen.x = (xf+(floor.x-xf)*scale(screen.y));
	}
	
	public static void toFloor(PointF floor, PointF screen){
		floor.y = proj(screen.y);
		floor.x = xf+(screen.x-xf)/scale(screen.y);
	}

	Background(Context context){	
		background =  BitmapFactory.decodeResource(context.getResources(), R.drawable.foxy_zombie_background);
	}
	
	public void paint(Canvas canvas, Rect rect){
		if (background == null) return;
		canvas.drawBitmap(background, null,rect, null);

	}

}
