package com.diegomorales.evaluacion3;

import androidx.annotation.NonNull;
import androidx.annotation.RequiresApi;
import androidx.appcompat.app.AppCompatActivity;

import android.bluetooth.BluetoothGatt;
import android.os.Build;
import android.os.Bundle;
import android.text.Editable;
import android.text.TextUtils;
import android.text.TextWatcher;
import android.util.Log;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.functions.FirebaseFunctions;

public class Vista extends AppCompatActivity {
    private Sensor Items;
    private TextView nombresensor,tiposensor,valorsensor,ubicacionsensor,fechahora;
    private Button BGUARDAR;
    private EditText observacion;
    private Sensor sensoractual;
    private FirebaseFunctions mFunctions;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference dbReference;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_vista);
        Items=(Sensor) getIntent().getSerializableExtra("objetoDato");
        nombresensor=(TextView) findViewById(R.id.nombre);
        tiposensor=(TextView) findViewById(R.id.tipo);
        valorsensor=(TextView) findViewById(R.id.valor);
        ubicacionsensor=(TextView) findViewById(R.id.ubicacion);
        fechahora=(TextView) findViewById(R.id.fechahora);

        observacion=(EditText) findViewById(R.id.observacion);

        nombresensor.setText(Items.getNombreSensor());
        tiposensor.setText(Items.getTipoSensor());
        valorsensor.setText(Items.getValorSensor());
        ubicacionsensor.setText(Items.getUbicacionSensor());
        fechahora.setText(Items.getFechaRegistro());
        observacion.setText(Items.getObservacionSensor());
        observacion.setFocusable(false);
        observacion.addTextChangedListener(new TextWatcher() {
            @Override
            public void beforeTextChanged(CharSequence s, int start, int count, int after) {
                Log.i("INFORMACION:","Antes de cambiar, " + observacion.getText().toString());
            }

            @Override
            public void onTextChanged(CharSequence s, int start, int before, int count) {
                Log.i("INFORMACION:","Cambiar, " + observacion.getText().toString());
                BGUARDAR.setVisibility(View.VISIBLE);
            }

            @Override
            public void afterTextChanged(Editable s) {
                Log.i("INFORMACION:","Cambió, " + observacion.getText().toString());
            }
        });
        BGUARDAR=(Button)findViewById(R.id.bGuardar);
        BGUARDAR.setVisibility(View.GONE);
//        Bundle id =getIntent().getExtras();
//        String sensorid = id.getString("id");
//        firebaseDatabase=FirebaseDatabase.getInstance();
//        dbReference=firebaseDatabase.getReference();
//        dbReference.addValueEventListener(new ValueEventListener() {
//            @Override
//            public void onDataChange(@NonNull DataSnapshot dataSnapshot) {
//                if(dataSnapshot.exists()){
//                    nombresensor.setText(dataSnapshot.child("sensor").child(sensorid).child("nombreSensor").getValue().toString());
//                    tiposensor.setText(dataSnapshot.child("sensor").child(sensorid).child("tipoSensor").getValue().toString());
//                    valorsensor.setText(dataSnapshot.child("sensor").child(sensorid).child("valorSensor").getValue().toString());
//                    ubicacionsensor.setText(dataSnapshot.child("sensor").child(sensorid).child("ubicacionSensor").getValue().toString());
//                    fechahora.setText(dataSnapshot.child("sensor").child(sensorid).child("fechaRegistro").getValue().toString());
//                    observacion.setText(dataSnapshot.child("sensor").child(sensorid).child("observacionSensor").getValue().toString());
//                }
//            }
//
//            @Override
//            public void onCancelled(@NonNull DatabaseError error) {
//
//            }
//        });
//
//        BGUARDAR.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                switch (v.getId()){
//                    case R.id.bGuardar:
//                        Bundle id =getIntent().getExtras();
//                        String sensorid = id.getString("id");
//                        String obs=observacion.getText().toString();
//                        dbReference.child("sensor").child(sensorid).child("observacionSensor").setValue(obs);
//                        Toast.makeText(getApplicationContext(), "Campo actualizado", Toast.LENGTH_SHORT).show();
//                        onBackPressed();
//                        break;
//                }
//
//            }
//        });
    }
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.menu_botones, menu);
        return true;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public boolean onOptionsItemSelected(@NonNull MenuItem item) {
        switch (item.getItemId()) {
            case R.id.menu_guardar:
                actualizar();
                break;
        }
        return true;
    }
    private void actualizar(){
        observacion.setFocusable(true);
        observacion.setFocusableInTouchMode(true);
    }
}