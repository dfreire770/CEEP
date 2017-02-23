package com.makeramen.example;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.Intent;
import android.graphics.Bitmap;
import android.graphics.BitmapFactory;
import android.os.Bundle;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.ImageView.ScaleType;
import android.widget.ListView;
import android.widget.TextView;
import com.makeramen.RoundedImageView;


public class MainFragment extends Fragment {
//estos son metodos de la libreria roundedimage que utilizamos para darle ese aspecto a las fotos
  static final String ARG_IS_OVAL = "is_oval";

  private boolean isOval = false;
//este metodo crea el fragmento de acuerdo a los parametros indicados
  public static MainFragment getInstance(boolean isOval) {
    MainFragment f = new MainFragment();
    Bundle args = new Bundle();
    args.putBoolean(ARG_IS_OVAL, isOval);
    f.setArguments(args);

    return f;
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);
//el metodo on create es el metodo parecido al main, es escencial
    if (getArguments() != null) {
      isOval = getArguments().getBoolean(ARG_IS_OVAL);
    }
      AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(this.getActivity());
      alertDialogBuilder
              .setMessage("Que quieres hacer hoy?");
      alertDialogBuilder.setNegativeButton("Continuar?",null);

      AlertDialog alertDialog = alertDialogBuilder.create();
      alertDialog.show();


  }
//este metodo permite crear un adaptador para el contenedor y que se vea en forma de lista
  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
    View view = inflater.inflate(R.layout.fragment_rounded, container, false);// fragment rounded es el xml tipo lista
//aqui se agregan las secciones al adaptador, para poder verlo enforma de lista
    StreamAdapter adapter = new StreamAdapter(getActivity());

      adapter.add(new StreamItem(getActivity(), R.drawable.farra, "Quieres farrear?", "Discotecas",
              ScaleType.CENTER_CROP));
      adapter.add(new StreamItem(getActivity(), R.drawable.coctel, "Negocios?", "Bares - Restaurantes",
              ScaleType.CENTER));
      adapter.add(new StreamItem(getActivity(), R.drawable.poli, "Tienes hambre?", "Restaurantes",
              ScaleType.CENTER));
      adapter.add(new StreamItem(getActivity(), R.drawable.cita, "Cita Romantica?", "Sitios Romanticos",
              ScaleType.CENTER));
      adapter.add(new StreamItem(getActivity(), R.drawable.karaoke, "Quieres cantar?", "Bar - Karaoke",
              ScaleType.CENTER));
      adapter.add(new StreamItem(getActivity(), R.drawable.cinema, "Plan Sano?", "Varios",
              ScaleType.CENTER_CROP));


//agregamos la accion para cada toque de los elementos de la lista
      ListView lv = (ListView)view;
      lv.setAdapter(adapter);
      lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {
              switch (i) {
                  default:
                  case 0:
                      startActivity(new Intent(getActivity(), DiscoActivity.class));//lanzara la actividad de discotecas, etc
                      break;
                  case 1:
                      startActivity(new Intent(getActivity(), NegociosActivity.class));
                      break;
                  case 2:

                      startActivity(new Intent(getActivity(), ComidaActivity.class));
                      break;
                  case 3:

                      startActivity(new Intent(getActivity(), CitaActivity.class));
                      break;
                  case 4:

                      startActivity(new Intent(getActivity(), KaraokeActivity.class));
                      break;
                  case 5:

                      startActivity(new Intent(getActivity(), VariosActivity.class));
                      break;

              }


              /*getFragmentManager().beginTransaction()
                      .replace(android.R.id.content, newFragment)
                      .commit();*/
          }
      });

    ((ListView) view.findViewById(R.id.main_list)).setAdapter(adapter);
    return view;
  }
//esta es una clase que maneja los elementos que se agregan a la lista, es propia de la libreria
    //define elementos como la foto y texto
  class StreamItem {
    final Bitmap mBitmap;
    final String mLine1;
    final String mLine2;
    final ScaleType mScaleType;

    StreamItem(Context c, int resid, String line1, String line2, ScaleType scaleType) {
      mBitmap = BitmapFactory.decodeResource(c.getResources(), resid);
      mLine1 = line1;
      mLine2 = line2;
      mScaleType = scaleType;
    }
  }

  class StreamAdapter extends ArrayAdapter<StreamItem> {
    private final LayoutInflater mInflater;

    public StreamAdapter(Context context) {
      super(context, 0);
      mInflater = LayoutInflater.from(getContext());
    }

    @Override
    public View getView(int position, View convertView, ViewGroup parent) {
      ViewGroup view;
      if (convertView == null) {
        view = (ViewGroup) mInflater.inflate(R.layout.rounded_item, parent, false);
      } else {
        view = (ViewGroup) convertView;
      }

      StreamItem item = getItem(position);

      RoundedImageView iv = ((RoundedImageView) view.findViewById(R.id.imageView1));
      iv.setOval(isOval);
      iv.setImageBitmap(item.mBitmap);
      iv.setScaleType(item.mScaleType);
      ((TextView) view.findViewById(R.id.textView1)).setText(item.mLine1);
      ((TextView) view.findViewById(R.id.textView2)).setText(item.mLine2);
      return view;
    }
  }
}
