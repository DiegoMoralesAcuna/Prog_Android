package com.example.tareacalificada02;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.CheckBox;
import android.widget.EditText;
import android.widget.RadioButton;
import android.widget.RadioGroup;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView txtNombres;
    private TextView txtApellidos;
    private RadioGroup radioGroup1;
    private RadioButton selectedRadiobtn;
    private CheckBox checkBox1;
    private CheckBox javacheck;
    private CheckBox phpcheck;
    private CheckBox phytoncheck;
    private CheckBox ccharpcheck;
    private CheckBox cmasmascheck;
    private String Lenguajes;
    private String Lenguajefav;
    private Button btnaceptar;
    private String nombre;
    private String apellido;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
        EditText txt_name = (EditText) findViewById(R.id.txt_name);
        EditText txt_lastname = (EditText) findViewById(R.id.txt_lastname);

        radioGroup1=(RadioGroup) findViewById(R.id.radio);
        javacheck=(CheckBox)findViewById(R.id.checkBox);
        phpcheck=(CheckBox)findViewById(R.id.checkBox2);
        phytoncheck=(CheckBox)findViewById(R.id.checkBox3);
        ccharpcheck=(CheckBox)findViewById(R.id.checkBox4);
        cmasmascheck=(CheckBox)findViewById(R.id.checkBox5);

        btnaceptar=(Button) findViewById(R.id.mibtn);
        btnaceptar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent i = new Intent (getApplicationContext(),pantalla2.class);
                i.putExtra("nombre",txt_name.getText().toString());
                i.putExtra("apellido",txt_lastname.getText().toString());
                Lenguajes="";
                Lenguajeagregado(javacheck);
                Lenguajeagregado(phpcheck);
                Lenguajeagregado(phytoncheck);
                Lenguajeagregado(ccharpcheck);
                Lenguajeagregado(cmasmascheck);
                i.putExtra("Lenguajes",Lenguajes);

                selectedRadiobtn=(RadioButton) findViewById(radioGroup1.getCheckedRadioButtonId());
                i.putExtra("Lenguajefav", selectedRadiobtn.getText().toString());
                startActivity(i);
            }
        });
    }
    private void Lenguajeagregado (CheckBox checkBox1 ){
        if(checkBox1.isChecked()){
            if(Lenguajes !=""){
                Lenguajes += " , " + checkBox1.getText().toString();
            }else{
                Lenguajes=checkBox1.getText().toString();
            }
        }
    }
}