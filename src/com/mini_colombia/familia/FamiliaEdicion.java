package com.mini_colombia.familia;

import java.io.BufferedInputStream;
import java.io.FileInputStream;
import java.io.FileNotFoundException;
import java.io.IOException;
import java.sql.SQLException;
import java.util.List;

import android.app.Activity;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.Gravity;
import android.view.View;
import android.view.View.OnClickListener;
import android.webkit.WebView;
import android.widget.Button;
import android.widget.FrameLayout;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.LinearLayout.LayoutParams;
import android.widget.TextView;

import com.j256.ormlite.android.apptools.OpenHelperManager;
import com.j256.ormlite.dao.Dao;
import com.mini_colombia.R;
import com.mini_colombia.db.DataBaseHelper;
import com.mini_colombia.servicios.ObtenerImagen;
import com.mini_colombia.values.Edicion;

public class FamiliaEdicion extends Activity
{
	private static final String EDICION = "edicion";
	
	private static final String MODELO = "MODELO";
	
	
	private String nombreModelo;
	
	
	private Edicion e;
	
	@Override
	protected void onCreate(Bundle savedInstanceState)
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_familia_edicion);
		
		nombreModelo = getIntent().getStringExtra(MODELO);
		e = (Edicion)getIntent().getSerializableExtra("objeto");
		
		final String nombreEdicion = e.getNombre();
		
		//Creacion de las fuentes
		Typeface tipoMini = Typeface.createFromAsset(getAssets(), "fonts/mibd.ttf");
		
		//Creacion del titulo
		TextView textoTitulo = (TextView) findViewById(R.id.TituloEdicion);
		textoTitulo.setText(nombreModelo);
		textoTitulo.setTypeface(tipoMini);
		
		FrameLayout frameLayout = (FrameLayout) findViewById(R.id.frameLayoutFamiliaModelo);
		
		//Agregar la imagen de la edicion
		String nombreImagen = e.getImagen();
		ImageView iv = new ImageView(this);
		LayoutParams parametrosImagen = new LayoutParams(LayoutParams.MATCH_PARENT, LayoutParams.WRAP_CONTENT);
		iv.setBackgroundResource(getResources().getIdentifier(nombreImagen, "drawable", getApplicationContext().getPackageName()));
		iv.setLayoutParams(parametrosImagen);
		frameLayout.addView(iv);
		
		String[] colores = e.getTemplateColor().split(",");
		
		//Verificacion de test drive
		if(e.isTest_drive())
		{
			
			
			//Agregar el boton de test drive
			LinearLayout.LayoutParams parametros = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
			Button b = new Button(this);
			b.setBackgroundColor(Color.rgb(Integer.parseInt(colores[0]),Integer.parseInt(colores[1]),Integer.parseInt(colores[2])));
			b.setText(R.string.solicitar_test_drive);
			b.setGravity(Gravity.TOP|Gravity.LEFT);
			b.setPadding(7, 5, 5,5);
			b.setLayoutParams(parametros);
			b.setTypeface(tipoMini);
			
			//Verificacion del nivel de rojo para escoger el color de la letra
			double rojo =(double)(Integer.parseInt(colores[0]));
			double constante = 256*0.79;
			if(rojo>constante)
			{
				b.setTextColor(Color.WHITE);
			}
			else
				b.setTextColor(Color.BLACK);
			frameLayout.addView(b);
			b.setOnClickListener(new OnClickListener() 
			{
				
				@Override
				public void onClick(View v) 
				{
					Intent i = new Intent(FamiliaEdicion.this, FamiliaTestDrive.class);
					i.putExtra(EDICION, nombreEdicion);
					View v1 = FamiliaInicio.grupoFamilia.getLocalActivityManager().startActivity("", i).getDecorView();
					FamiliaInicio actividadPadre =(FamiliaInicio) getParent(); 
					actividadPadre.reemplazarView(v1);
				}
			});
		}
		
		//Agregar el layout para poner el nombre de la edicion
		LinearLayout layoutNombreEdicion = (LinearLayout) findViewById(R.id.layoutFamiliaNombreEdicion);
		layoutNombreEdicion.setBackgroundColor(Color.rgb(Integer.parseInt(colores[0]),Integer.parseInt(colores[1]),Integer.parseInt(colores[2])));
		
		TextView tNombreEdicion = (TextView) findViewById(R.id.textEdicionNombreEdicion);
		tNombreEdicion.setText(e.getNombre());
		tNombreEdicion.setTypeface(tipoMini);
		tNombreEdicion.setTextColor(Color.rgb(Integer.parseInt(colores[0]),Integer.parseInt(colores[1]),Integer.parseInt(colores[2])));
		tNombreEdicion.setPadding(7, 4, 0, 0);
		
		
		//Manejo del texto
		WebView webView = (WebView)findViewById(R.id.webFamiliaModelo);
		String html = e.getDescripcion();
		webView.loadData(html, "text/html", "charset=UTF-8");
		
		
		
//		daoEdicion = darDaoEdicion();
//		try 
//		{
//			List<Edicion> ediciones = daoEdicion.queryForAll();
//			Edicion e = ediciones.get(edicion);
//			
//			//Creacion del titulo
//			TextView textoTitulo = (TextView) findViewById(R.id.TituloEdicion);
//			textoTitulo.setText(nombreModelo);
//			textoTitulo.setTypeface(tipoMini);
//			
//			FrameLayout frameLayout = (FrameLayout) findViewById(R.id.frameLayoutFamiliaModelo);
//			
//			String nombreImagen = e.getImagen();
//			
//			Bitmap bitmap = ObtenerImagen.darImagen(nombreImagen, getApplicationContext());
//			ImageView i = new ImageView(this);
//			i.setImageBitmap(bitmap);
//			frameLayout.addView(i);
//			
//			String[] colores = e.getTemplateColor().split(",");
//			
//			if(e.isTest_drive())
//			{
//				LinearLayout.LayoutParams parametros = new LayoutParams(LayoutParams.WRAP_CONTENT, LayoutParams.WRAP_CONTENT);
//				
//				Button b = new Button(this);
//				b.setBackgroundColor(Color.rgb(Integer.parseInt(colores[0]),Integer.parseInt(colores[1]),Integer.parseInt(colores[2])));
//				b.setText(R.string.solicitar_test_drive);
//				b.setGravity(Gravity.TOP|Gravity.LEFT);
//				b.setPadding(7, 5, 5,5);
//				b.setLayoutParams(parametros);
//				b.setTypeface(tipoMini);
//				
//				double rojo =(double)(Integer.parseInt(colores[0]));
//				double constante = 256*0.79;
//				if(rojo>constante)
//				{
//					b.setTextColor(Color.WHITE);
//				}
//				else
//					b.setTextColor(Color.BLACK);
//				frameLayout.addView(b);
//				b.setOnClickListener(this);
//				
//			}
//			
//			LinearLayout layoutNombreEdicion = (LinearLayout) findViewById(R.id.layoutFamiliaNombreEdicion);
//			layoutNombreEdicion.setBackgroundColor(Color.rgb(Integer.parseInt(colores[0]),Integer.parseInt(colores[1]),Integer.parseInt(colores[2])));
//			
//			
//			TextView tNombreEdicion = (TextView) findViewById(R.id.textEdicionNombreEdicion);
//			tNombreEdicion.setText(e.getNombre());
//			tNombreEdicion.setTypeface(tipoMini);
//			tNombreEdicion.setTextColor(Color.rgb(Integer.parseInt(colores[0]),Integer.parseInt(colores[1]),Integer.parseInt(colores[2])));
//			tNombreEdicion.setPadding(7, 4, 0, 0);
//			
//			nombreEdicion= e.getNombre();
//			
//			//Manejo del texto
//			WebView webView = (WebView)findViewById(R.id.webFamiliaModelo);
//			String html = e.getDescripcion();
//			webView.loadData(html, "text/html", "charset=UTF-8");
//			
//			
//			
//			
//
//		} 
//		catch (SQLException e) 
//		{
//			// TODO Auto-generated catch block
//			e.printStackTrace();
//		} 
		
	}
	
	
	
	@Override
	public void onBackPressed() 
	{
		getParent().onBackPressed();
	}
	
//	@Override
//	public void onClick(View v) 
//	{
//		Intent i = new Intent(FamiliaEdicion.this, FamiliaTestDrive.class);
//		i.putExtra(EDICION, nombreEdicion);
//		View v1 = FamiliaInicio.grupoFamilia.getLocalActivityManager().startActivity("", i).getDecorView();
//		FamiliaInicio actividadPadre =(FamiliaInicio) getParent(); 
//		actividadPadre.reemplazarView(v1);
//	}
	
//	public Dao<Edicion, String> darDaoEdicion()
//	{
//
//		Dao<Edicion, String> daoEdicion = null;
//		try 
//		{
//			daoEdicion = getHelper().darDaoEdicion();
//		} 
//		catch (SQLException e) 
//		{
//			e.printStackTrace();
//		}
//		return daoEdicion;
//	}
	
//	public DataBaseHelper getHelper()
//	{
//		if(databaseHelper == null)
//			databaseHelper = OpenHelperManager.getHelper(this,DataBaseHelper.class);
//		return databaseHelper;
//	}
	
//	protected void onDestroy() 
//	{
//		super.onDestroy();
//
//		//Manejo del objeto DataBaseHelper
//		if(databaseHelper != null)
//			OpenHelperManager.releaseHelper();
//		databaseHelper = null;
//	}
	
	
}
