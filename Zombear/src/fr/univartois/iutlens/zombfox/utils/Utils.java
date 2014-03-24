package fr.univartois.iutlens.zombfox.utils;

import android.annotation.SuppressLint;
import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences.Editor;
import android.graphics.Bitmap;
import android.graphics.Canvas;
import android.graphics.drawable.BitmapDrawable;
import android.graphics.drawable.Drawable;
import android.os.Build;
import android.view.MenuItem;

public class Utils {
	
	public static String display(int time,boolean running) {
		int sec = (time %6000)/100;
		int min = time /6000;
		String s = (min >0 ? min +(sec < 10  ? " ' 0":" ' ")  : "") 
				+sec + " \" " + ((time %100)/10) +
				(running ? "" : time%10);
		return s;
	}
	
	
	public static String getStringResourceByName(Context context,String aString) {
	      String packageName = context.getPackageName();
	      int resId = context.getResources().getIdentifier(aString, "string", packageName);
	      return context.getString(resId);
	    }
	
	public static Bitmap loadImage(Context context, int id) {
		Drawable blankDrawable = context.getResources().getDrawable(id);
		Bitmap b =((BitmapDrawable)blankDrawable).getBitmap();
		return b;
	}
	
	public static Bitmap loadImages(Context context, int id1,int id2) {
		Drawable blankDrawable = context.getResources().getDrawable(id1);
		Bitmap b =((BitmapDrawable)blankDrawable).getBitmap().copy(Bitmap.Config.ARGB_8888, true);
		Canvas c = new Canvas(b);
		c.drawBitmap(loadImage(context,id2),0,0,null);
		return b;
	}
	
	@SuppressLint("NewApi")
	public static void commit(Editor editor){
		if (Build.VERSION.SDK_INT>=9){
			editor.apply();
		} else {
			editor.commit();
		}
	}
}
