package com.mini_colombia.comunidad;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.graphics.Bitmap;
import android.graphics.Color;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.webkit.WebView;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;

import com.mini_colombia.R;
import com.mini_colombia.auxiliares.Evento;
import com.mini_colombia.servicios.AsyncTaskListener;
import com.mini_colombia.servicios.DescargarImagenOnline;
import com.mini_colombia.servicios.Resize;

public class ComunidadEventosT4 extends Activity implements AsyncTaskListener<Bitmap>
{

	private String contenido;
	
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		// TODO Auto-generated method stub
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comunidad_eventos_t4);
		
		Typeface tipoMini = Typeface.createFromAsset(getAssets(), "fonts/mibd.ttf");
		TextView titulo = (TextView) findViewById(R.id.tituloT2);
		titulo.setTypeface(tipoMini);
		
		Evento e = (Evento)getIntent().getSerializableExtra("objeto");
		
		String tituloImagen = e.getTitulo();
		TextView tTituloImagen = (TextView)findViewById(R.id.tituloImagenT4);
		tTituloImagen.setText(tituloImagen);
		
		LinearLayout layoutFondoTitulo =(LinearLayout) findViewById(R.id.linearFondoTituloT4);
		String[] rgbFondo =e.getTemplateColor().split(",");
		layoutFondoTitulo.setBackgroundColor(Color.rgb(Integer.parseInt(rgbFondo[0]), Integer.parseInt(rgbFondo[1]), Integer.parseInt(rgbFondo[2])));
		

		contenido = e.getContenido();
		String urlImagen = e.getUrlImagenes().get(0);
		DescargarImagen tarea = new DescargarImagen(darContexto(), this);
		tarea.execute(urlImagen);
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
			Bitmap imagenFinal = Resize.resizeBitmap(imagenPreliminar, 768, 315);
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
		ImageView imagen = (ImageView) findViewById(R.id.imagenT4);
		imagen.setImageBitmap(result);
		
		WebView wv = (WebView) findViewById(R.id.webViewT4);
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