package com.mini_colombia.comunidad;

import java.util.ArrayList;
import java.util.concurrent.ExecutionException;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.ActivityGroup;
import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.net.ConnectivityManager;
import android.net.NetworkInfo;
import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.Gravity;
import android.view.View;
import android.widget.TextView;

import com.mini_colombia.R;
import com.mini_colombia.auxiliares.ImagenGaleria;
import com.mini_colombia.descargas.DescargasInicio;
import com.mini_colombia.parser.Parser;
import com.mini_colombia.servicios.DescargarImagenOnline;

public class ComunidadInicio extends ActivityGroup
{
	public ArrayList<ImagenGaleria> g;
	
	public static ComunidadInicio grupoComunidad;
	
	public ArrayList<View> historialViews;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comunidad_inicio);
		this.grupoComunidad = this;
		
		historialViews = new ArrayList<View>();
		
		Typeface tipoMini = Typeface.createFromAsset(getAssets(), "fonts/mibd.ttf");
		TextView titulo = (TextView) findViewById(R.id.textoInicioComunidad);
		titulo.setText("COMUNIDAD.");
		titulo.setTypeface(tipoMini);
		titulo.setGravity(Gravity.LEFT|Gravity.CENTER_VERTICAL);
	}
	
	public void iniciarEventos(View v)
	{
		ConnectivityManager conMgr =  (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo red  = conMgr.getActiveNetworkInfo();
		boolean conexionInternet = red!=null && red.getState() == NetworkInfo.State.CONNECTED;
		if(conexionInternet)
		{
			Intent i = new Intent(ComunidadInicio.this, ComunidadNuevosEventos.class);
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			View v1 = getLocalActivityManager().startActivity("", i).getDecorView();
			reemplazarView(v1);
		}
		else
		{
			AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
			alertBuilder.setMessage("Debes tener accesso a internet para entrar a esta seccion");
			alertBuilder.setCancelable(false);
			alertBuilder.setNeutralButton("Aceptar", new DialogInterface.OnClickListener() 
			{

				@Override
				public void onClick(DialogInterface dialog, int which) 
				{
					finish();
					
					
				}
			});
			AlertDialog alerta = alertBuilder.create();
			alerta.show();
		}
		

	}
	
	public void iniciarGaleria(View v)
	{
		ConnectivityManager conMgr =  (ConnectivityManager)getSystemService(Context.CONNECTIVITY_SERVICE);
		NetworkInfo red  = conMgr.getActiveNetworkInfo();
		boolean re =red.getType() == ConnectivityManager.TYPE_WIFI;
		boolean conexionInternet = red!=null && red.getState() == NetworkInfo.State.CONNECTED;
		if(conexionInternet)
		{
			Intent i = new Intent(ComunidadInicio.this, ComunidadGaleria.class);
			i.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
			View v1 = getLocalActivityManager().startActivity("", i).getDecorView();
			reemplazarView(v1);
		}
		else
		{
			AlertDialog.Builder alertBuilder = new AlertDialog.Builder(this);
			alertBuilder.setMessage("Debes tener accesso a internet para entrar a esta seccion");
			alertBuilder.setCancelable(false);
			alertBuilder.setNeutralButton("Aceptar", new DialogInterface.OnClickListener() 
			{

				@Override
				public void onClick(DialogInterface dialog, int which) 
				{
					onCreate(null);
				}
			});
			AlertDialog alerta = alertBuilder.create();
			alerta.show();
		}
		

	}
	
	

	
	public ArrayList<ImagenGaleria> darArreglo()
	{
		ArrayList<ImagenGaleria> o = g;
		return o;
	}
	
	
	public void reemplazarView(View v)
	{
		historialViews.add(v);
		setContentView(v);
	}
	
	public void back()
	{
		if(historialViews.size()>0)
		{
			
			historialViews.remove(historialViews.size() -1);
			if(historialViews.size()>0)
			{
				setContentView(historialViews.get(historialViews.size()-1));
			}
			else
				onCreate(null);
		}

		else
		{

			finish();
		}

	}
	
	@Override
	public void onBackPressed() 
	{
		ComunidadInicio.grupoComunidad.back();
		return;
	}
}
