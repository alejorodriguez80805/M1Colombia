package com.mini_colombia.vitrinas;

import com.mini_colombia.R;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;

public class VitrinasCiudad extends Activity
{
	private static final String ESPACIO =  " ";
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_vitrinas_ciudad);
		
		Typeface tipoMini = Typeface.createFromAsset(getAssets(), "fonts/mibd.ttf");
		TextView tituloVitrinas = (TextView) findViewById(R.id.tituloVitrinasCiudad);
		tituloVitrinas.setTypeface(tipoMini);
		
		int ciudad = getIntent().getIntExtra("ciudad", 0);
		ImageView imagenConsecionario = (ImageView) findViewById(R.id.vitrinaCiudadImagen);
		TextView tituloCiudad = (TextView) findViewById(R.id.textVitrinasCiudad);
		TextView direccionCiudad = (TextView) findViewById(R.id.vitrinaCiudadDireccion);
		TextView telefonoCiudad = (TextView) findViewById(R.id.vitrinaCiudadTelefono);
		ImageView imagenMecanico = (ImageView) findViewById(R.id.vitrinaCiudadImagenMecanico);
		TextView  nombreMecanico = (TextView) findViewById(R.id.vitrinasCiudadNombreMecanico);
		TextView mailMecanico = (TextView) findViewById(R.id.vitrinasCiudadMailMecanico);
		TextView celularMecanico = (TextView) findViewById(R.id.vitrinasCiudadCelularMecanico);
		switch (ciudad) 
		{
		case 1:
			tituloCiudad.setText("BARRANQUILLA.");
			imagenConsecionario.setBackgroundResource(R.drawable.imagen_vitrina_ciudad_bquilla);
			direccionCiudad.setText(R.string.TAG_VITRINAS_DIRECCION_BARRANQUILLA);
			telefonoCiudad.setText(getString(R.string.TAG_VITRINAS_TELEFONO_CONSTANTE) + ESPACIO + getString(R.string.TAG_VITRINAS_TELEFONO_BARRANQUILLA));
			imagenMecanico.setBackgroundResource(R.drawable.vitrinas_imagen_mecanico_bquilla);
			nombreMecanico.setText(getString(R.string.TAG_VITRINAS_NOMBRE_MECANICO_CONSTANTE) +ESPACIO + getString(R.string.TAG_VITRINAS_NOMBRE_MECANICO_BQUILLA));
			mailMecanico.setText(getString(R.string.TAG_VITRINAS_MAIL_MECANICO_BQUILLA));
			celularMecanico.setText(getString(R.string.TAG_VITRINAS_CELULAR_MECANICO_CONSTANTE) + ESPACIO + getString(R.string.TAG_VITRINAS_CELULAR_MECANICO_BQUILLA));
			break;
		case 2:
			tituloCiudad.setText("MEDELLIN.");
			imagenConsecionario.setBackgroundResource(R.drawable.imagen_vitrina_ciudad_medellin);
			direccionCiudad.setText(R.string.TAG_VITRINAS_DIRECCION_MEDELLIN);
			telefonoCiudad.setText(getString(R.string.TAG_VITRINAS_TELEFONO_CONSTANTE) + ESPACIO+ getString(R.string.TAG_VITRINAS_TELEFONO_MEDELLIN));
			imagenMecanico.setBackgroundResource(R.drawable.vitrinas_imagen_mecanico_medellin);
			nombreMecanico.setText(getString(R.string.TAG_VITRINAS_NOMBRE_MECANICO_CONSTANTE) +ESPACIO + getString(R.string.TAG_VITRINAS_NOMBRE_MECANICO_MEDELLIN));
			mailMecanico.setText(getString(R.string.TAG_VITRINAS_MAIL_MECANICO_MEDELLIN));
			celularMecanico.setText(getString(R.string.TAG_VITRINAS_CELULAR_MECANICO_CONSTANTE) + ESPACIO + getString(R.string.TAG_VITRINAS_CELULAR_MECANICO_MEDELLIN));
			break;
		case 3:
			tituloCiudad.setText("PEREIRA.");
			imagenConsecionario.setBackgroundResource(R.drawable.imagen_vitrina_ciudad_pereria);
			direccionCiudad.setText(R.string.TAG_VITRINAS_DIRECCION_PEREIRA);
			telefonoCiudad.setText(getString(R.string.TAG_VITRINAS_TELEFONO_CONSTANTE) +ESPACIO + getString(R.string.TAG_VITRINAS_TELEFONO_PEREIRA));
			imagenMecanico.setBackgroundResource(R.drawable.vitrinas_imagen_mecanico_pereira);
			nombreMecanico.setText(getString(R.string.TAG_VITRINAS_NOMBRE_MECANICO_CONSTANTE) +ESPACIO + getString(R.string.TAG_VITRINAS_NOMBRE_MECANICO_PEREIRA));
			mailMecanico.setText(getString(R.string.TAG_VITRINAS_MAIL_MECANICO_PEREIRA));
			celularMecanico.setText(getString(R.string.TAG_VITRINAS_CELULAR_MECANICO_CONSTANTE) + ESPACIO + getString(R.string.TAG_VITRINAS_CELULAR_MECANICO_PEREIRA));
			break;
		case 4:
			tituloCiudad.setText("BOGOTA.");
			imagenConsecionario.setBackgroundResource(R.drawable.imagen_vitrina_ciudad_bogota);
			direccionCiudad.setText(R.string.TAG_VITRINAS_DIRECCION_BOGOTA);
			telefonoCiudad.setText(getString(R.string.TAG_VITRINAS_TELEFONO_CONSTANTE) +ESPACIO + getString(R.string.TAG_VITRINAS_TELEFONO_BOGOTA));
			imagenMecanico.setBackgroundResource(R.drawable.vitrinas_imagen_mecanico_bogota);
			nombreMecanico.setText(getString(R.string.TAG_VITRINAS_NOMBRE_MECANICO_CONSTANTE) +ESPACIO + getString(R.string.TAG_VITRINAS_NOMBRE_MECANICO_BOGOTA));
			mailMecanico.setText(getString(R.string.TAG_VITRINAS_MAIL_MECANICO_BOGOTA));
			celularMecanico.setText(getString(R.string.TAG_VITRINAS_CELULAR_MECANICO_CONSTANTE) + ESPACIO + getString(R.string.TAG_VITRINAS_CELULAR_MECANICO_BOGOTA));
			break;
		case 5:
			tituloCiudad.setText("CALI.");
			imagenConsecionario.setBackgroundResource(R.drawable.imagen_vitrina_ciudad_cali);
			direccionCiudad.setText(R.string.TAG_VITRINAS_DIRECCION_CALI);
			telefonoCiudad.setText(getString(R.string.TAG_VITRINAS_TELEFONO_CONSTANTE) +ESPACIO+ getString(R.string.TAG_VITRINAS_TELEFONO_CALI));
			imagenMecanico.setBackgroundResource(R.drawable.vitrinas_imagen_mecanico_cali);
			nombreMecanico.setText(getString(R.string.TAG_VITRINAS_NOMBRE_MECANICO_CONSTANTE) +ESPACIO + getString(R.string.TAG_VITRINAS_NOMBRE_MECANICO_CALI));
			mailMecanico.setText(getString(R.string.TAG_VITRINAS_MAIL_MECANICO_CALI));
			celularMecanico.setText(getString(R.string.TAG_VITRINAS_CELULAR_MECANICO_CONSTANTE) + ESPACIO + getString(R.string.TAG_VITRINAS_CELULAR_MECANICO_CALI));
			break;
		}
		tituloCiudad.setTypeface(tipoMini);
	}
	
	@Override
	public void onBackPressed() 
	{
		getParent().onBackPressed();
	}
}
 