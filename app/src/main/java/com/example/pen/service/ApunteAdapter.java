package com.example.pen.service;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pen.R;
import com.example.pen.model.ApunteFace;
import static com.example.pen.service.DateFormats.*;
import java.util.List;

public class ApunteAdapter extends RecyclerView.Adapter<ApunteAdapter.ViewHolder>{
    private List<ApunteFace> lista;
    public ApunteAdapter.ApunteAdapterListener onClickListener;

    public List<ApunteFace> getLista() {
        return lista;
    }

    public void setLista(List<ApunteFace> lista) {
        this.lista = lista;
    }

    public ApunteAdapter.ApunteAdapterListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(ApunteAdapter.ApunteAdapterListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public ApunteAdapter() {
    }
    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista;
        vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.apunte_element, parent, false);
        ViewHolder viewholder = new ViewHolder(vista);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull ApunteAdapter.ViewHolder holder, int position) {
        holder.nombre.setText(lista.get(position).getNombre());
        holder.tipo.setText(lista.get(position).getTipo());
        holder.fecha.setText(SIMPLE_DATE.getFormat().format(lista.get(position).getFecha()));
        holder.opciones.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.opcionesOnClick(v, position);
            }
        });

        View.OnClickListener listener=new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.itemOnClick(v, position);
            }
        };

        holder.nombre.setOnClickListener(listener);
        holder.tipo.setOnClickListener(listener);
        holder.fecha.setOnClickListener(listener);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView nombre;
        private final TextView fecha;
        private final TextView tipo;
        private final ImageButton opciones;

        public ViewHolder(View itemView) {
            super(itemView);
            opciones= itemView.findViewById(R.id.opciones023);;
            nombre = itemView.findViewById(R.id.nombre023);
            fecha = itemView.findViewById(R.id.fecha023);
            tipo=itemView.findViewById(R.id.tipo023);
        }
    }

    public interface ApunteAdapterListener{
        void opcionesOnClick(View v, int position);
        void itemOnClick(View v, int position);
    }
}
