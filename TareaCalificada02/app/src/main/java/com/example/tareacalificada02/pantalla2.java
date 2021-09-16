package com.example.tareacalificada02;


import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import org.w3c.dom.Text;

public class pantalla2 extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_pantalla2);
        TextView txtnombre=(TextView) findViewById(R.id.txtnombre);
        TextView txtapell=(TextView) findViewById(R.id.txtapell);
        TextView txtlenguaje=(TextView) findViewById(R.id.txtlenguaje);
        TextView txtlenguajefav=(TextView) findViewById(R.id.txtlenguajefav);

        Intent i=getIntent();
        txtnombre.setText(i.getStringExtra("nombre"));
        txtapell.setText(i.getStringExtra("apellido"));
        txtlenguaje.setText(i.getStringExtra("Lenguajes"));
        txtlenguajefav.setText(i.getStringExtra("Lenguajefav"));

        Button btnvolver = (Button) findViewById(R.id.btnvolver);
        btnvolver.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i2= new Intent(pantalla2.this,MainActivity.class);
                startActivity(i2);
            }
        });

    }


}