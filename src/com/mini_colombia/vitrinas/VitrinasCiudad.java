package com.mini_colombia.vitrinas;

import com.mini_colombia.R;

import android.app.Activity;
import android.os.Bundle;
import android.widget.TextView;

public class VitrinasCiudad extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vitrinas_ciudad);
		
		int ciudad = getIntent().getIntExtra("ciudad", 0);
		TextView tituloCiudad = (TextView) findViewById(R.id.textVitrinasCiudad);
		switch (ciudad) 
		{
		case 1:
			tituloCiudad.setText("BARRANQUILLA.");
			break;
		case 2:
			tituloCiudad.setText("MEDELLIN.");
			break;
		case 3:
			tituloCiudad.setText("PEREIRA.");
			break;
		case 4:
			tituloCiudad.setText("BOGOTA.");
			break;
		case 5:
			tituloCiudad.setText("CALI.");
			break;
		}
	}
}
