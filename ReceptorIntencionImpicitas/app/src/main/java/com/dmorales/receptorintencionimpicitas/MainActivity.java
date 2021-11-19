package com.dmorales.receptorintencionimpicitas;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.widget.TextView;

public class MainActivity extends AppCompatActivity {
    private TextView txt_uri_msg;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        txt_uri_msg= (TextView) findViewById(R.id.txt_uri_msg);

        Intent intent = getIntent();
        Uri uri = intent.getData();
        if (uri != null){
            String uriStr = "Uri data:" + uri.toString();
            txt_uri_msg.setText(uriStr);
        }

    }
}