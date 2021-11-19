package com.dmorales.hilostareasasincronicas02;

import androidx.appcompat.app.AppCompatActivity;

import android.os.AsyncTask;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ProgressBar;
import android.widget.Toast;

public class MainActivity extends AppCompatActivity {
    private ProgressBar progressBar;
    private TareaAsincronica tareaAsyncrona;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);
    }

    public void llamarDormir(View view) {
        Toast.makeText(this, "Iniciando Sleep", Toast.LENGTH_SHORT).show();
        for (int i = 0; i <= 10; i++) {
            adormir();

        }
        Toast.makeText(this, "Termino Sleep", Toast.LENGTH_SHORT).show();
    }

    public void adormir() {
        try {
            Thread.sleep(1000);
        }catch (InterruptedException e) {
            e.printStackTrace();
        }
    }

    public void primerHilo(View view){
        Toast.makeText(this,"Iniciando Primer Hilo", Toast.LENGTH_SHORT).show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 10; i++) {
                    adormir();

                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "Termino Primer Hilo", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        }).start();
        Toast.makeText(this, "Fin Primer Hilo?", Toast.LENGTH_SHORT).show();
    }


    public void segundoHilo(View view){
        Toast.makeText(this,"Iniciando Segundo Hilo", Toast.LENGTH_SHORT).show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 10; i++) {
                    adormir();

                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(),"Termino Segundo Hilo", Toast.LENGTH_SHORT).show();

                    }
                });

            }
        }).start();
        Toast.makeText(this, "Fin Segundo Hilo?", Toast.LENGTH_SHORT).show();

    }

    public void tercerHilo(View view){
        Toast.makeText(this,"Iniciando Ultimo Hilo", Toast.LENGTH_SHORT).show();
        new Thread(new Runnable() {
            @Override
            public void run() {
                for (int i = 0; i <= 10; i++) {
                    adormir();

                }
                runOnUiThread(new Runnable() {
                    @Override
                    public void run() {
                        Toast.makeText(getApplicationContext(), "Fin Ultimo Hilo", Toast.LENGTH_SHORT).show();

                    }
                });
            }
        }).start();
    }
    public void tareaAsincronica(View view){
        if(tareaAsyncrona==null) {
            Toast.makeText(this, "Iniciando tarea asincronica", Toast.LENGTH_SHORT).show();
            tareaAsyncrona = new TareaAsincronica(1, 10);
            tareaAsyncrona.execute();
            Toast.makeText(this, "Fin llamada tarea asincronica", Toast.LENGTH_SHORT).show();
        }else{
            Toast.makeText(this, "Estado: " + tareaAsyncrona.getStatus().name(), Toast.LENGTH_SHORT).show();
            tareaAsyncrona.cancel(true);
            tareaAsyncrona=null;
        }


    }
    private class TareaAsincronica extends AsyncTask<Void, Integer, Boolean>{
        private int desde,hasta;
        public TareaAsincronica(int desde, int hasta) {
            super();
            this.desde=desde;
            this.hasta=hasta;
        }

        @Override
        protected void onPreExecute() {
            super.onPreExecute();
            progressBar.setProgress(0);

        }

        @Override
        protected void onPostExecute(Boolean aBoolean) {
            super.onPostExecute(aBoolean);
            Toast.makeText(getApplicationContext(),"Fin de tarea Asincronica",Toast.LENGTH_SHORT).show();
        }

        @Override
        protected void onProgressUpdate(Integer... values) {
            super.onProgressUpdate(values);
            progressBar.incrementProgressBy(values[0].intValue());
        }

        @Override
        protected void onCancelled(Boolean aBoolean) {
            super.onCancelled(aBoolean);
            Log.i("Informacion","onCancelled(Boolean aBoolean)");

        }

        @Override
        protected void onCancelled() {
            super.onCancelled();
            Log.i("Informacion","onCancelled");

        }

        @Override
        protected Boolean doInBackground(Void... voids) {
            for (int i= desde; i<=hasta; i++ ){
                adormir();
                publishProgress(1);
            }
            return null;
        }
    }
}


