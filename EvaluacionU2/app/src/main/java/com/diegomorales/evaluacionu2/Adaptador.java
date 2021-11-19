package com.diegomorales.evaluacionu2;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;

public class Adaptador extends RecyclerView.Adapter<Adaptador.SensorViewHolder> {

    ArrayList<Sensores> listaSensores;

    public Adaptador(ArrayList<Sensores> listaSensores){
        this.listaSensores=listaSensores;
    }
    @NonNull
    @Override

    public SensorViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.lista_detalle,null,false);
        return new SensorViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull SensorViewHolder holder, int position) {
        holder.txt_nombre.setText(listaSensores.get(position).getNom_sensor());
        holder.txt_valor.setText(listaSensores.get(position).getValor_sensor());
        holder.txt_fecha.setText(listaSensores.get(position).getFecha_sensor());
        holder.txt_hora.setText(listaSensores.get(position).getHora_sensor());
        holder.txt_observacion.setText(listaSensores.get(position).getNom_sensor());
    }

    @Override
    public int getItemCount() {
        return listaSensores.size();
    }

    public class SensorViewHolder extends RecyclerView.ViewHolder {

        TextView txt_nombre,txt_valor,txt_fecha,txt_hora,txt_observacion;

        public SensorViewHolder(@NonNull View itemView) {
            super(itemView);

            txt_nombre=itemView.findViewById(R.id.txt_nombre_detalle);
            txt_valor=itemView.findViewById(R.id.txt_valor_detalle);
            txt_fecha=itemView.findViewById(R.id.txt_fecha_detalle);
            txt_hora=itemView.findViewById(R.id.txt_hora_detalle);
            txt_observacion=itemView.findViewById(R.id.txt_observacion_detalle);
        }
    }
}
