package dm.android.calculadora;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private Button suma;
    private Button resta;
    private Button mul;
    private Button div;
    private Button limpiar;
    private Button salir;

    private TextView resultado;

    private EditText num1;
    private EditText num2;
    private static int pantalla=0;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        resultado= (TextView) findViewById(R.id.txt_respuesta);
        num1= (EditText) findViewById(R.id.etxt_1);
        num2= (EditText) findViewById(R.id.etxt2);
        limpiar=(Button) findViewById(R.id.btn_limpiar);
        salir=(Button)findViewById(R.id.btn_salir);

        suma = (Button) findViewById(R.id.btn_suma);
        suma.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultado.setText( suma(Integer.parseInt(num1.getText().toString()), Integer.parseInt(num2.getText().toString()) )+"");
            }
        });
        resta = (Button) findViewById(R.id.btn_resta);
        resta.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultado.setText( resta(Integer.parseInt(num1.getText().toString()), Integer.parseInt(num2.getText().toString()) )+"");
            }
        });
        mul = (Button) findViewById(R.id.btn_mul);
        mul.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultado.setText( mul(Integer.parseInt(num1.getText().toString()), Integer.parseInt(num2.getText().toString()) )+"");
            }
        });
        div = (Button) findViewById(R.id.btn_div);
        div.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                resultado.setText( div(Integer.parseInt(num1.getText().toString()), Integer.parseInt(num2.getText().toString()) )+"");
            }
        });

    }
    public double suma (int a, int b){
        return a+b;
    }

    public double resta (int a, int b){
        return a-b;
    }

    public double mul (int a, int b){
        return a*b;
    }

    public double div (int a, int b){
        int respuesta = 0;
        if (b!=0){
            respuesta=a/b;
        }
        return respuesta;
    }
    public void limpiarPantalla (View view){
        pantalla=0;
        resultado.setText(Integer.toString(pantalla));
        num1.setText(Integer.toString(pantalla));
        num2.setText(Integer.toString(pantalla));
    }
    public void cerrarCalculadora(View view){
        System.exit(0);
    }
}