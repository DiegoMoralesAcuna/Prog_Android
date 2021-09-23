package com.dmorales.evaluacionu1;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {

    private Button boton;
    private EditText txtsalida;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        boton = (Button) findViewById(R.id.btn);
        txtsalida = (EditText) findViewById(R.id.edtxtnombre);
    }

            public void mostrarT(View view) {
                Toast.makeText(this, "Pedido confirmado!", Toast.LENGTH_LONG).show();
    }
}
