package com.mini_colombia.comunidad;

import java.util.ArrayList;

import com.mini_colombia.R;
import com.mini_colombia.auxiliares.Evento;
import com.mini_colombia.servicios.AsyncTaskListener;
import com.mini_colombia.servicios.DescargarImagenOnline;
import com.mini_colombia.servicios.Resize;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.provider.ContactsContract.CommonDataKinds.Event;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.TextView;

public class ComunidadEventosT1 extends Activity implements AsyncTaskListener<Bitmap>
{
	private String contenido;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comunidad_eventos_t1);
		
		Typeface tipoMini = Typeface.createFromAsset(getAssets(), "fonts/mibd.ttf");
		TextView titulo = (TextView) findViewById(R.id.tituloT1);
		titulo.setTypeface(tipoMini);
		
		Evento e = (Evento)getIntent().getSerializableExtra("objeto");
		
		String tituloImagen = e.getTitulo();
		TextView tTituloImagen = (TextView)findViewById(R.id.tituloImagenT1);
		tTituloImagen.setText(tituloImagen);
		
		String urlImagen = e.getUrlImagenes().get(0);
		DescargarImagen tarea = new DescargarImagen(darContexto(), this);
		tarea.execute(urlImagen);
		
		contenido = e.getContenido();
	}
	
	private class DescargarImagen extends AsyncTask<String, Void, Bitmap>
	{

		private Context context;

		private AsyncTaskListener<Bitmap> callback;
		
		private ProgressDialog progress;
		
		public DescargarImagen(Context context, AsyncTaskListener<Bitmap> callback)
		{
			this.context = context;
			this.callback = callback;
		}
		
		@Override
		protected void onPreExecute() 
		{
			progress = ProgressDialog.show(context,"","Cargando imagen...",false);
		}
		
		@Override
		protected Bitmap doInBackground(String... params) 
		{
			// TODO Auto-generated method stub
			Bitmap imagenPreliminar = DescargarImagenOnline.descargarImagen(params[0]);
			Bitmap imagenFinal = Resize.resizeBitmap(imagenPreliminar, 345, 450);
			return imagenFinal;
		}
		
		@Override
		protected void onPostExecute(Bitmap result) 
		{
			progress.dismiss();
			callback.onTaskComplete(result);
		}
		
	}

	@Override
	public void onTaskComplete(Bitmap result) 
	{
		ImageView imagen = (ImageView) findViewById(R.id.imagenT1);
		imagen.setImageBitmap(result);
		
		WebView wv = (WebView) findViewById(R.id.webViewT1);
		wv.loadData(contenido, "text/html", "charset=UTF-8");
	}
	
	public Context darContexto()
	{
		Context context = null;
		if (getParent() != null) 
			context = getParent();
		return context;
	}

}
