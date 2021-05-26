package com.example.pen.dao;

import android.app.Activity;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pen.R;
import com.example.pen.model.ActividadesAgenda;
import com.squareup.picasso.Picasso;

import java.util.List;

public class AgendaAdaptador extends RecyclerView.Adapter<AgendaAdaptador.AgendaHolder> {

    List<ActividadesAgenda> lista;
    int layout;
    Activity actividad;

    public AgendaAdaptador(List<ActividadesAgenda> lista, int layout, Activity actividad) {
        this.lista = lista;
        this.layout = layout;
        this.actividad = actividad;
    }

    @NonNull
    @Override
    public AgendaHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(layout,parent, false);
        return new AgendaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull  AgendaAdaptador.AgendaHolder holder, int position) {
        ActividadesAgenda actividad =lista.get(position);
        holder.txtid.setText(actividad.getId());
        holder.txtfecha.setText(actividad.getFecha());
        holder.txtactividad_cardview.setText(actividad.getActividad());
        holder.txtdescripcion_cardview.setText(actividad.getDescripcion());
        holder.txthorainicio.setText(actividad.getHorainicio());
        holder.txthorafin.setText(actividad.getHorafin());
        Picasso.get().load(actividad.getImagen()).into(holder.imgpicturecard);
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class AgendaHolder extends RecyclerView.ViewHolder{

        TextView txtid, txtfecha, txtactividad_cardview, txtdescripcion_cardview,txthorainicio, txthorafin;
        ImageView imgpicturecard;

        public AgendaHolder(@NonNull View itemView) {
            super(itemView);
            txtid = itemView.findViewById(R.id.txtid);
            txtfecha=itemView.findViewById(R.id.txtfecha);
            txtactividad_cardview=itemView.findViewById(R.id.txtactividad_cardview);
            txtdescripcion_cardview=itemView.findViewById(R.id.txtdescripcion_cardview);
            txthorainicio=itemView.findViewById(R.id.txthorainicio);
            txthorafin=itemView.findViewById(R.id.txthorafin);
            imgpicturecard=itemView.findViewById(R.id.imgpicturecard
            );
        }
    }
}
