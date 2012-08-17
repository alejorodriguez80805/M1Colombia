package com.mini_colombia.comunidad;

import java.util.ArrayList;

import org.json.JSONArray;
import org.json.JSONException;
import org.json.JSONObject;

import android.app.Activity;
import android.app.ProgressDialog;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.Typeface;
import android.os.AsyncTask;
import android.os.Bundle;
import android.view.View;
import android.view.View.OnClickListener;
import android.widget.ImageButton;
import android.widget.TextView;

import com.mini_colombia.R;
import com.mini_colombia.auxiliares.Evento;
import com.mini_colombia.parser.Parser;
import com.mini_colombia.servicios.AsyncTaskListener;
import com.mini_colombia.servicios.DescargarImagenOnline;
import com.mini_colombia.servicios.Resize;

public class ComunidadNuevosEventos extends Activity implements AsyncTaskListener<ArrayList<Evento>>, OnClickListener
{
	@Override
	protected void onCreate(Bundle savedInstanceState) 
	{
		super.onCreate(savedInstanceState);
		setContentView(R.layout.activity_comunidad_nuevos_eventos);

		Typeface tipoMini = Typeface.createFromAsset(getAssets(), "fonts/mibd.ttf");
		TextView titulo = (TextView) findViewById(R.id.tituloInicioEventos);
		titulo.setTypeface(tipoMini);
		DescargarInfo tarea = new DescargarInfo(darContexto(), this);
		tarea.execute();
	}

	private class DescargarInfo extends AsyncTask<Void, Void, ArrayList<Evento>>
	{

		private ArrayList<Evento> eventos;

		private Context context;

		private AsyncTaskListener<ArrayList<Evento>> callback;

		private ProgressDialog progress;

		public DescargarInfo(Context context, AsyncTaskListener<ArrayList<Evento>> callback)
		{
			this.context = context;
			this.callback = callback;
		}

		@Override
		protected void onPreExecute() 
		{
			progress =ProgressDialog.show(darContexto(),"","Cargando eventos...",false);
		}

		@Override
		protected ArrayList<Evento> doInBackground(Void... params) 
		{
			eventos = new ArrayList<Evento>();
			Parser jparser = new Parser();
			JSONObject jsonObject =jparser.getJSONFromUrl(getString(R.string.CONSTANTE_COMUNIDAD_DAR_EVENTOS));
			try 
			{
				JSONArray eventos = jsonObject.getJSONArray(getString(R.string.TAG_COMUNIDAD_EVENTOS));
				for(int i=0;i<eventos.length();i++)
				{
					JSONObject evento = eventos.getJSONObject(i);

					String subtitulo = evento.getString(getString(R.string.TAG_COMUNIDAD_EVENTOS_SUBTITULO));
					String contenido = evento.getString(getString(R.string.TAG_COMUNIDAD_EVENTOS_CONTENIDO));
					String fecha = evento.getString(getString(R.string.TAG_COMUNIDAD_EVENTOS_FECHA));
					String template = evento.getString(getString(R.string.TAG_COMUNIDAD_EVENTOS_TEMPLATE));
					String p = evento.getString(getString(R.string.TAG_COMUNIDAD_EVENTOS_POSICION));
					String titulo = evento.getString(getString(R.string.TAG_COMUNIDAD_EVENTOS_TITULO));
					String thumbnailURL = evento.getString(getString(R.string.TAG_COMUNIDAD_EVENTOS_THUMBNAIL_URL));
					String templateColor = evento.getString(getString(R.string.TAG_COMUNIDAD_EVENTOS_TEMPLATE_COLOR));
					Bitmap thumbnailPreliminar = DescargarImagenOnline.descargarImagen(thumbnailURL);

					String posicion = p.split("-")[1];
					Bitmap thumbnail = null;
					if(posicion.equals("1") || posicion.equals("2") || posicion.equals("3"))
						thumbnail = Resize.resizeBitmap(thumbnailPreliminar, 178, 150);
					else if(posicion.equals("4"))
						thumbnail = Resize.resizeBitmap(thumbnailPreliminar, 223, 451);
					else if(posicion.equals("5"))
						thumbnail =	Resize.resizeBitmap(thumbnailPreliminar, 185, 270);
					else if(posicion.equals("6"))
						thumbnail =	Resize.resizeBitmap(thumbnailPreliminar, 229, 180);
					else if(posicion.equals("7") || posicion.equals("8"))
						thumbnail =	Resize.resizeBitmap(thumbnailPreliminar, 154, 225);

					JSONArray imagenes = evento.getJSONArray(getString(R.string.TAG_COMUNIDAD_EVENTOS_IMAGENES));

					ArrayList<String> imagenes1 = new ArrayList<String>();
					for(int j=0;j<imagenes.length();j++)
					{
						String imagen = imagenes.getString(j);
						imagenes1.add(imagen);
					}

					Evento e = new Evento(subtitulo, contenido, fecha, template, posicion, titulo, thumbnail,imagenes1, templateColor);
					this.eventos.add(e);
				}
			} 
			catch (JSONException e) 
			{
				// TODO Auto-generated catch block
				e.printStackTrace();
			}
			return this.eventos;
		}

		@Override
		protected void onPostExecute(ArrayList<Evento> result) 
		{
			progress.dismiss();
			callback.onTaskComplete(result);
		}

	}

	@Override
	public void onTaskComplete(ArrayList<Evento> result) 
	{
		for(int i=0;i<result.size();i++)
		{
			final Evento evento = result.get(i);
			String posicion = evento.getPosicion();
			final int template = Integer.parseInt(""+evento.getTemplate().charAt(1));

			if(posicion.equals("1"))
			{
				ImageButton b1 = (ImageButton) findViewById(R.id.eventosButton1);
				b1.setImageBitmap(evento.getThumbnailURL());
				b1.setOnClickListener(new OnClickListener() {

					@Override
					public void onClick(View arg0) 
					{
						Intent i1 = null;

						switch (template) 
						{
						case 1:
							i1 = new Intent(ComunidadNuevosEventos.this, ComunidadEventosT1.class);

							break;

						case 2:
							i1 = new Intent(ComunidadNuevosEventos.this, ComunidadEventosT2.class);
							break;

						case 3:
							i1 = new Intent(ComunidadNuevosEventos.this, ComunidadEventosT3.class);
							break;

						case 4:
							i1 = new Intent(ComunidadNuevosEventos.this, ComunidadEventosT4.class);
							break;

						case 5:
							i1 = new Intent(ComunidadNuevosEventos.this, ComunidadEventosT5.class);
							break;
						}


						Bundle bundle = new Bundle();
						bundle.putSerializable("objeto", evento);
						i1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						i1.putExtras(bundle);
						View v1 = ComunidadInicio.grupoComunidad.getLocalActivityManager().startActivity("", i1).getDecorView();
						ComunidadInicio actividadPadre = (ComunidadInicio) getParent();
						actividadPadre.reemplazarView(v1);

					}
				});

			}
			else if(posicion.equals("2"))
			{
				ImageButton b2 = (ImageButton) findViewById(R.id.eventosButton2);
				b2.setImageBitmap(evento.getThumbnailURL());
			}

			else if(posicion.equals("3"))
			{
				ImageButton b3 = (ImageButton) findViewById(R.id.eventosButton3);
				b3.setImageBitmap(evento.getThumbnailURL());
				b3.setOnClickListener(new OnClickListener() {
					
					@Override
					public void onClick(View arg0) 
					{
						Intent i1 = null;

						switch (template) 
						{
						case 1:
							i1 = new Intent(ComunidadNuevosEventos.this, ComunidadEventosT1.class);

							break;

						case 2:
							i1 = new Intent(ComunidadNuevosEventos.this, ComunidadEventosT2.class);
							break;

						case 3:
							i1 = new Intent(ComunidadNuevosEventos.this, ComunidadEventosT3.class);
							break;

						case 4:
							i1 = new Intent(ComunidadNuevosEventos.this, ComunidadEventosT4.class);
							break;

						case 5:
							i1 = new Intent(ComunidadNuevosEventos.this, ComunidadEventosT5.class);
							break;
						}


						Bundle bundle = new Bundle();
						bundle.putSerializable("objeto", evento);
						i1.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
						i1.putExtras(bundle);
						View v1 = ComunidadInicio.grupoComunidad.getLocalActivityManager().startActivity("", i1).getDecorView();
						ComunidadInicio actividadPadre = (ComunidadInicio) getParent();
						actividadPadre.reemplazarView(v1);
						
					}
				});
			}

			else if(posicion.equals("4"))
			{
				ImageButton b4 = (ImageButton) findViewById(R.id.eventosButton4);
				b4.setImageBitmap(evento.getThumbnailURL());
			}

			else if(posicion.equals("5"))
			{
				ImageButton b5 = (ImageButton) findViewById(R.id.eventosButton5);
				b5.setImageBitmap(evento.getThumbnailURL());
			}

			else if (posicion.equals("6")) 
			{
				ImageButton b6 = (ImageButton) findViewById(R.id.eventosButton6);
				b6.setImageBitmap(evento.getThumbnailURL());
			}

			else if(posicion.equals("7"))
			{
				ImageButton b7 = (ImageButton) findViewById(R.id.eventosButton7);
				b7.setImageBitmap(evento.getThumbnailURL());
			}

			else
			{
				ImageButton b8 = (ImageButton) findViewById(R.id.eventosButton8);
				b8.setImageBitmap(evento.getThumbnailURL());
			}








		}
	}


	public Context darContexto()
	{
		Context context = null;
		if (getParent() != null) 
			context = getParent();
		return context;
	}


	@Override
	public void onClick(View v) {
		// TODO Auto-generated method stub

	}
}
