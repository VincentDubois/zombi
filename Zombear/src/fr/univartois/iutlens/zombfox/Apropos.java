package fr.univartois.iutlens.zombfox;




import android.app.Activity;
import android.os.Bundle;
import android.text.Html;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.Button;
import android.widget.TextView;
public class Apropos extends Activity 
{ 
	@Override
	public void onCreate(Bundle savedInstanceState) {
		super.onCreate(savedInstanceState); setContentView(R.layout.apropos2); 
		

		final Button proposButton = (Button) findViewById(R.id.buttonpropos);
		proposButton.setOnClickListener(new OnClickListener() 
		{
			@Override 
			public void onClick(View v) 
			{ 
				Apropos.this.finish();
				
			}}); 
	}
	
	

}
