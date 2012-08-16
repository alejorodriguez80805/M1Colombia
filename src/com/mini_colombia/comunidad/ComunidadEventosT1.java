package com.mini_colombia.comunidad;

import com.mini_colombia.R;

import android.app.Activity;
import android.graphics.Typeface;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.TextView;

public class ComunidadEventosT1 extends Activity
{
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comunidad_eventos_t1);
		
		Typeface tipoMini = Typeface.createFromAsset(getAssets(), "fonts/mibd.ttf");
		TextView titulo = (TextView) findViewById(R.id.tituloT1);
		titulo.setTypeface(tipoMini);
		
		String tituloImagen = getIntent().getStringExtra("tituloEvento");
		TextView tTituloImagen = (TextView)findViewById(R.id.tituloImagenT1);
		tTituloImagen.setText(tituloImagen);
		String contenido = getIntent().getStringExtra("contenido");
		
		WebView wv = (WebView) findViewById(R.id.webViewT1);
		wv.loadData(contenido, "text/html", "charset=UTF-8");
		
		
	}
}
