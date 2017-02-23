package com.makeramen.example;

import android.app.AlertDialog;
import android.app.Fragment;
import android.content.Context;
import android.content.DialogInterface;
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
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ImageView.ScaleType;
import android.widget.ListView;
import android.widget.TextView;

import com.makeramen.RoundedImageView;
import com.makeramen.example.sqlite.SQLiteHelper;

import java.util.ArrayList;

public class CitaFragment extends Fragment {

  static final String ARG_IS_OVAL = "is_oval";
    final Context context = this.getActivity();
  private boolean isOval = false;
    ImageView image;

  public static CitaFragment getInstance(boolean isOval) {
    CitaFragment f = new CitaFragment();
    Bundle args = new Bundle();
    args.putBoolean(ARG_IS_OVAL, isOval);
    f.setArguments(args);
    return f;
  }

  @Override public void onCreate(Bundle savedInstanceState) {
    super.onCreate(savedInstanceState);

    if (getArguments() != null) {
      isOval = getArguments().getBoolean(ARG_IS_OVAL);
    }
  }

  @Override public View onCreateView(LayoutInflater inflater, ViewGroup container,
      Bundle savedInstanceState) {
      final SQLiteHelper db = new SQLiteHelper(this.getActivity());
    View view = inflater.inflate(R.layout.fragment_rounded, container, false);

      final Intent intent = new Intent(this.getActivity(), MapsActivity.class);
      StreamAdapter adapter = new StreamAdapter(getActivity());
      final ArrayList<Object[]> lugares = db.getLugares("cita");
      final ListView lv = (ListView)view;
      Log.i("Lista",""+lugares.size());
      String nombre="";
      String telefono="";
      int imagen;
      int i=0;
      do {
          nombre=lugares.get(i)[1].toString();
          telefono=lugares.get(i)[3].toString();
          imagen = (int)lugares.get(i)[5];
      //adapter.add(new StreamItem(getActivity(), imagen, nombre, telefono, ScaleType.CENTER));
      adapter.addAll(new StreamItem(getActivity(), imagen, nombre, telefono, ScaleType.CENTER));
          i++;
      }while(i<lugares.size());
      lv.setAdapter(adapter);

      //lv.setAdapter(adapter);
      final String finalNombre = nombre;
      lv.setOnItemClickListener(new AdapterView.OnItemClickListener() {
          @Override
          public void onItemClick(AdapterView<?> adapterView, View view, int i, long l) {

              final String direccion = lugares.get(i)[2].toString();
              String telefono2 = lugares.get(i)[3].toString();
              String informacion = lugares.get(i)[4].toString();
              final String nombre2 = lugares.get(i)[1].toString();
              AlertDialog.Builder alertDialogBuilder = new AlertDialog.Builder(getActivity());

              int imagen = ((int) lugares.get(i)[5]);// en realidad es el 6, solo para probar



              alertDialogBuilder.setMessage(informacion+"\n"+direccion+"\n"+telefono2);


              alertDialogBuilder.setPositiveButton("Ver Mapa", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialogInterface, int i) {
                      intent.putExtra("Lugar",direccion);
                      intent.putExtra("Nombre", nombre2);
                      startActivity(intent);
                  }
              });
              alertDialogBuilder.setNegativeButton("              ", new DialogInterface.OnClickListener() {
                  @Override
                  public void onClick(DialogInterface dialogInterface, int i) {
                      Intent intent2 = new Intent();

                      intent2.setAction(Intent.ACTION_SEND);
                      intent2.setPackage("com.facebook.katana");
                      intent2.setType("text/plain");
                      intent2.putExtra(Intent.EXTRA_TEXT, "Farreando gracias a 'Cual es el plan?' app");
                      startActivity(Intent.createChooser(intent2, "Farreando gracias a 'Cual es el plan?'"));
                  }
              });
              AlertDialog alertDialog = alertDialogBuilder.create();
              alertDialog.setTitle("Vamos?");
              alertDialog.setIcon(R.drawable.party_hat);


              alertDialog.show();

              Button share = alertDialog.getButton(AlertDialog.BUTTON_NEGATIVE);
              share.setBackgroundResource(R.drawable.compartir);
              /*Button maps = alertDialog.getButton(AlertDialog.BUTTON_POSITIVE);
              maps.setBackgroundColor(Color.BLUE);*/




              /*dialogMaps.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {
                      intent.putExtra("Lugar",direccion);
                      startActivity(intent);
                  }
              });*/
              //ImageButton dialogShare = (ImageButton) dialog.findViewById(R.id.dialogButtonShare);
              /*dialogShare.setOnClickListener(new View.OnClickListener() {
                  @Override
                  public void onClick(View view) {

                      Intent intent2 = new Intent();

                      intent2.setAction(Intent.ACTION_SEND);
                      intent2.setPackage("com.facebook.katana");
                      intent2.setType("text/plain");
                      intent2.putExtra(Intent.EXTRA_TEXT, "Farreando gracias a 'Cual es el plan?' app");
                      startActivity(Intent.createChooser(intent2, "Farreando gracias a 'Cual es el plan?'"));

                  }
              });*/


          }
      });

    ((ListView) view.findViewById(R.id.main_list)).setAdapter(adapter);
    return view;
  }

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
