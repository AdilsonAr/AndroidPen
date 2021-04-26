package com.example.pen.service;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pen.R;
import com.example.pen.model.Url;

import java.util.List;

public class UrlAdapter extends RecyclerView.Adapter<UrlAdapter.ViewHolder> {
    public List<Url> lista;

    public UrlAdapter(List<Url> lista) {
        this.lista = lista;
    }

    @NonNull
    @Override
    public UrlAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View vista;
        vista = LayoutInflater.from(parent.getContext()).inflate(R.layout.busqueda_element, parent, false);

        ViewHolder viewholder = new ViewHolder(vista);
        return viewholder;
    }

    @Override
    public void onBindViewHolder(@NonNull UrlAdapter.ViewHolder holder, int position) {
        holder.url.setText(lista.get(position).getUrl());
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView url;

        public ViewHolder(View itemView) {
            super(itemView);
            url = itemView.findViewById(R.id.url);
        }
    }
}
