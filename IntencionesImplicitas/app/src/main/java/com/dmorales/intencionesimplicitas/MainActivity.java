package com.dmorales.intencionesimplicitas;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.ShareCompat;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;

public class MainActivity extends AppCompatActivity {

    private EditText txt_url;
    private EditText txt_lugar;
    private EditText txt_compartir;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        txt_url=(EditText) findViewById(R.id.txt_url);
        txt_lugar=(EditText) findViewById(R.id.txt_lugar);
        txt_compartir=(EditText) findViewById(R.id.txt_compartir);

    }
    public void openUrl(View view){
        String url= txt_url.getText().toString();
        Uri uriWeb = Uri.parse(url);
        Intent intent = new Intent(Intent.ACTION_VIEW, uriWeb);

        if (intent.resolveActivity(getPackageManager()) != null){
            startActivity(intent);
        }else {
            Log.d("Intencion implicita","No se puede manejar la intencion");
        }

    }
    public void mostrarMapa(View view){
        String loc= txt_lugar.getText().toString();
        Uri uriMapa= Uri.parse("geo:0,0?q=" + loc);
        Intent intent = new Intent(Intent.ACTION_VIEW, uriMapa);
        intent.setPackage("com.google.android.apps.maps");

        if(intent.resolveActivity(getPackageManager()) !=null){
            startActivity(intent);
        }else {
            Log.d("Intencion implicita","No se puede manejar la intencion");

        }

    }
    public void compartirTexto(View view){
        String texto= txt_compartir.getText().toString();
        String tM = "text/plain";

        ShareCompat.IntentBuilder intentBuilder= new ShareCompat.IntentBuilder(this);
        intentBuilder.setType(tM);
        intentBuilder.setText(texto);

        if (intentBuilder.getIntent().resolveActivity(getPackageManager())!=null){
            startActivity(intentBuilder.getIntent());
        }
    }
}