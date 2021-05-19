package com.example.pen.service;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.EditText;
import android.widget.ImageButton;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pen.R;
import com.example.pen.model.KeyValue;

import java.util.List;

public class KeyValueAdapter extends RecyclerView.Adapter<KeyValueAdapter.ViewHolder> {
    public KeyValueAdapterListener listener;
    private List<KeyValue> list;

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista;
        vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.key_value_element, parent, false);
        KeyValueAdapter.ViewHolder viewholder = new KeyValueAdapter.ViewHolder(vista);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull KeyValueAdapter.ViewHolder holder, int position) {
        holder.key.setText(list.get(position).getKey());
        holder.value.setText(list.get(position).getValue());
        holder.eliminar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                listener.deleteOnClick(v,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return 0;
    }

    public class ViewHolder extends RecyclerView.ViewHolder{
        private final EditText key;
        private final EditText value;
        private final ImageButton eliminar;
        public ViewHolder(@NonNull View v) {
            super(v);
            key=v.findViewById(R.id.pregunta3453);
            value=v.findViewById(R.id.respuesta3453);
            eliminar=v.findViewById(R.id.delete3453);
        }
    }

    public interface KeyValueAdapterListener{
        void deleteOnClick(View v, int position);
    }
}
