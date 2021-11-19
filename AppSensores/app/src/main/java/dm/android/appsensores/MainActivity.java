package dm.android.appsensores;

import androidx.appcompat.app.AppCompatActivity;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.hardware.Sensor;
import android.hardware.SensorEvent;
import android.hardware.SensorEventListener;
import android.hardware.SensorManager;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import java.util.Iterator;
import java.util.List;

public class MainActivity extends AppCompatActivity {
    EditText etxt1,etxt2;
    Button btn;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        etxt1=(EditText) findViewById(R.id.txt_name);
        etxt2=(EditText) findViewById(R.id.txt_lastname);
        btn=(Button) findViewById(R.id.mibtn);
        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String nombre= etxt1.getText().toString();
                String apellido= etxt2.getText().toString();

                if(nombre.length()==0){
                    Toast.makeText(getApplicationContext(), "Debe ingresar su nombre", Toast.LENGTH_SHORT).show();
                }
                if(apellido.length()==0){
                    Toast.makeText(getApplicationContext(), "Debe ingresar su apellido", Toast.LENGTH_SHORT).show();
                }
                if(nombre.length() !=0 && apellido.length() !=0){
                    Toast.makeText(getApplicationContext(), "Ingreso exitoso", Toast.LENGTH_SHORT).show();
                    Intent i =new Intent(getApplicationContext(),MainActivity2.class);
                    startActivity(i);
                }
            }
        });
    }
}
