package com.example.pen.service;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageButton;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pen.R;
import com.example.pen.model.Url;

import java.util.List;

public class UrlAdapter extends RecyclerView.Adapter<UrlAdapter.ViewHolder> {
    private List<Url> lista;
    public UrlAdapterListener onClickListener;

    public List<Url> getLista() {
        return lista;
    }

    public void setLista(List<Url> lista) {
        this.lista = lista;
    }

    public UrlAdapterListener getOnClickListener() {
        return onClickListener;
    }

    public void setOnClickListener(UrlAdapterListener onClickListener) {
        this.onClickListener = onClickListener;
    }

    public UrlAdapter() {
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
        holder.delete.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.deleteOnClick(v, position);
            }
        });

        holder.searchAgain.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                onClickListener.searchAgainOnClick(v,position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class ViewHolder extends RecyclerView.ViewHolder {
        private final TextView url;
        private final ImageButton delete;
        private final ImageButton searchAgain;

        public ViewHolder(View itemView) {
            super(itemView);
            url = itemView.findViewById(R.id.url);
            delete = itemView.findViewById(R.id.opciones023);
            searchAgain=itemView.findViewById(R.id.searchAgain);
        }
    }

    public interface UrlAdapterListener{
        void deleteOnClick(View v, int position);
        void searchAgainOnClick(View v, int position);
    }
}
