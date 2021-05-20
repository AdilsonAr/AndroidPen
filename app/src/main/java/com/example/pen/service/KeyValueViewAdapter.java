package com.example.pen.service;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pen.R;
import com.example.pen.model.KeyValue;

import java.util.List;

public class KeyValueViewAdapter extends RecyclerView.Adapter<KeyValueViewAdapter.ViewHolder> {
    private List<KeyValue> list;

    public List<KeyValue> getList() {
        return list;
    }

    public void setList(List<KeyValue> list) {
        this.list = list;
    }

    @NonNull
    @Override
    public KeyValueViewAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista;
        vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.key_value_view_element, parent, false);
        KeyValueViewAdapter.ViewHolder viewholder = new KeyValueViewAdapter.ViewHolder(vista);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull KeyValueViewAdapter.ViewHolder holder, int position) {
        holder.pregunta.setText(list.get(position).getKey());
        holder.respuesta.setText(list.get(position).getValue());
    }

    @Override
    public int getItemCount() {
        return list.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private final TextView pregunta;
        private final TextView respuesta;
        public ViewHolder(@NonNull View v) {
            super(v);
            pregunta=v.findViewById(R.id.pregunta1212);
            respuesta=v.findViewById(R.id.respuesta1212);
        }
    }
}
