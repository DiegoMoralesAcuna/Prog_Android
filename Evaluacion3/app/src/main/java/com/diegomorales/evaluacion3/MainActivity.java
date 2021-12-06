package com.diegomorales.evaluacion3;


import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.EditText;
import android.widget.ListView;
import android.widget.Toast;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.FirebaseApp;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.functions.FirebaseFunctions;

import org.eclipse.paho.android.service.MqttAndroidClient;
import org.eclipse.paho.client.mqttv3.DisconnectedBufferOptions;
import org.eclipse.paho.client.mqttv3.IMqttActionListener;
import org.eclipse.paho.client.mqttv3.IMqttDeliveryToken;
import org.eclipse.paho.client.mqttv3.IMqttToken;
import org.eclipse.paho.client.mqttv3.MqttCallbackExtended;
import org.eclipse.paho.client.mqttv3.MqttConnectOptions;
import org.eclipse.paho.client.mqttv3.MqttException;
import org.eclipse.paho.client.mqttv3.MqttMessage;
import org.json.JSONObject;

import java.util.ArrayList;
import java.util.List;
import java.util.UUID;

public class MainActivity extends AppCompatActivity {

    private static final String serverUri = "tcp://192.168.190.138:1883";
    private static final String userName = "diego";
    private static final String password = "1601";
    private static final String appName = "app1";
    private static final String clientId = "marcelo";

    private static final String TAG = "MQTT Client 01";

    private MqttAndroidClient mqttAndroidClient;

    MyArrayAdapter arrayAdapter;
    List<Sensor> sensorList;
    ListView SensorListView;
    private FirebaseFunctions mFunctions;
    private FirebaseDatabase firebaseDatabase;
    private DatabaseReference dbReference;
    private EditText etNomUsurio, etEmail, etContrasena;

    private Sensor sensoractual;

    private ListView sensor_listView;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);


        sensorList = new ArrayList<>();
        arrayAdapter = new MyArrayAdapter(sensorList, getBaseContext(), getLayoutInflater());
        sensor_listView = (ListView) findViewById(R.id.lv_sensor);
        sensor_listView.setAdapter(arrayAdapter);
        sensor_listView.setOnItemClickListener(new AdapterView.OnItemClickListener() {
            @Override
            public void onItemClick(AdapterView<?> parent, View view, int position, long id) {
                Intent intent = new Intent(MainActivity.this,Vista.class);
                intent.putExtra("objetoDato",sensorList.get(position));
                startActivity(intent);
            }
        });

        mFunctions = FirebaseFunctions.getInstance();

        initFirebaseDB();
        loadDataFromFirebase();

        mqttAndroidClient = new MqttAndroidClient(getApplicationContext(), serverUri, clientId);
        mqttAndroidClient.setCallback(new MqttCallbackExtended() {
            @Override
            public void connectComplete(boolean reconnect, String serverURI) {
                if (reconnect) {
                    Log.d(TAG, "Reconectado a : " + serverURI);
                } else {
                    Log.d(TAG, "Conectado a: " + serverURI);
                }
            }

            @Override
            public void connectionLost(Throwable cause) {
                Log.d(TAG, "Se ha perdido la conexón!.");
            }

            @Override
            public void messageArrived(String topic, MqttMessage message) throws Exception {
                Log.d(TAG, "Mensaje recibido:" + message.toString());

                JSONObject jsonObject = new JSONObject(message.toString());

                Datos datos = new Datos(
                        UUID.randomUUID().toString(),
                        jsonObject.getString("nombre_sensor"),
                        jsonObject.getString("tipo"),
                        jsonObject.getString("valor"),
                        jsonObject.getString("ubicacion"),
                        jsonObject.getString("fecha-hora"),
                        jsonObject.getString("observ")
                );

                dbReference.child("sensor").child(datos.getIdSensor()).setValue(datos);

                Toast.makeText(getApplicationContext(), "Sensor guardado!", Toast.LENGTH_LONG).show();

            }

            @Override
            public void deliveryComplete(IMqttDeliveryToken token) {

            }
        });

        MqttConnectOptions mqttConnectOptions = new MqttConnectOptions();
        mqttConnectOptions.setAutomaticReconnect(true);
        mqttConnectOptions.setCleanSession(false);


        try {
            mqttAndroidClient.connect(mqttConnectOptions, null, new IMqttActionListener() {
                @Override
                public void onSuccess(IMqttToken asyncActionToken) {
                    Log.d(TAG, "Conectado a: " + serverUri);

                    try {
                        asyncActionToken.getSessionPresent();
                        Log.d(TAG, "Topicos: " + asyncActionToken.getTopics().toString());
                    } catch (Exception e) {
                        String message = e.getMessage();
                        Log.d(TAG, "Error el mensaje es nulo! " + String.valueOf(message == null));
                    }

                    Toast.makeText(MainActivity.this, "Conectado a mosquitto!", Toast.LENGTH_SHORT).show();

                    DisconnectedBufferOptions disconnectedBufferOptions = new DisconnectedBufferOptions();
                    disconnectedBufferOptions.setBufferEnabled(true);
                    disconnectedBufferOptions.setBufferSize(100);
                    disconnectedBufferOptions.setPersistBuffer(false);
                    disconnectedBufferOptions.setDeleteOldestMessages(false);
                    mqttAndroidClient.setBufferOpts(disconnectedBufferOptions);

                    try {
                        mqttAndroidClient.subscribe("data", 2);
                        Toast.makeText(MainActivity.this, "Subscrito a test01", Toast.LENGTH_SHORT).show();
                    } catch (MqttException e) {
                        e.printStackTrace();
                    }
                }

                @Override
                public void onFailure(IMqttToken asyncActionToken, Throwable exception) {

                    Log.d(TAG, "Falla al conectar a: " + serverUri);

                }
            });
        } catch (MqttException ex) {
            ex.printStackTrace();
        }


    }

    private void loadDataFromFirebase() {
        dbReference.child("sensor").addValueEventListener(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                sensorList.clear();
                for (DataSnapshot ds : snapshot.getChildren()) {
                    sensorList.add(ds.getValue(Sensor.class));
                }
                arrayAdapter.notifyDataSetChanged();
            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {
                Toast.makeText(getBaseContext(), "Error, " + error.toString(), Toast.LENGTH_SHORT).show();
            }
        });

    }

    private void initFirebaseDB() {
        FirebaseApp.initializeApp(this);
        firebaseDatabase = FirebaseDatabase.getInstance();
        dbReference = firebaseDatabase.getReference();
    }
}