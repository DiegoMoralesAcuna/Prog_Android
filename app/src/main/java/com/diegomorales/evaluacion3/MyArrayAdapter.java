package com.diegomorales.evaluacion3;


import android.content.Context;
import android.os.Build;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.BaseAdapter;
import android.widget.TextView;

import androidx.annotation.RequiresApi;

import java.util.List;

public class MyArrayAdapter<U> extends BaseAdapter {
    List<Sensor> sensorList;
    Context context;

    public MyArrayAdapter(List<Sensor> sensorList, Context context, LayoutInflater layoutInflater) {
        this.sensorList = sensorList;
        this.context = context;
        this.layoutInflater = layoutInflater;
    }

    LayoutInflater layoutInflater;

    @Override
    public int getCount() {
        return sensorList.size();
    }

    @Override
    public Object getItem(int i) {
        return sensorList.get(i);
    }

    @Override
    public long getItemId(int i) {
        return 0;
    }

    @RequiresApi(api = Build.VERSION_CODES.O)
    @Override
    public View getView(int i, View view, ViewGroup viewGroup) {
        ViewHolder vHolder;
        if (view == null) {
            view = layoutInflater.inflate(R.layout.sensor_layout,null);

            vHolder = new ViewHolder();
            vHolder.lblNombre = view.findViewById(R.id.lbl_Nombre);
            vHolder.lblValor = view.findViewById(R.id.lbl_Valor);

            view.setTag(vHolder);
        } else {
            vHolder = (ViewHolder) view.getTag();
        }
        Sensor u = sensorList.get(i);
        vHolder.lblNombre.setText(u.getNombreSensor());
        vHolder.lblValor.setText(u.getValorSensor());
        return view;
    }
    static class ViewHolder{
        TextView lblId;
        TextView lblNombre;
        TextView lblTipo;
        TextView lblValor;
        TextView lblUbicacion;
        TextView lblFecha;
    }
}
