package com.example.pen.dao;

import android.app.Activity;
import android.content.Context;
import android.content.Intent;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.fragment.app.FragmentActivity;
import androidx.fragment.app.FragmentManager;
import androidx.fragment.app.FragmentTransaction;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pen.R;
import com.example.pen.activity.ContenedorAgendas;
import com.example.pen.activity.fragments.AgendaFragment;
import com.example.pen.activity.fragments.AgregarActividadesFragment;
import com.example.pen.model.ActividadesAgenda;
import com.google.android.material.dialog.MaterialAlertDialogBuilder;
import com.google.android.material.snackbar.Snackbar;
import com.google.android.material.textfield.TextInputEditText;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.squareup.picasso.Picasso;

import java.util.List;
import java.util.Objects;

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
        View view = LayoutInflater.from(parent.getContext()).inflate(layout, parent, false);
        return new AgendaHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull AgendaHolder holder, int position) {
        ActividadesAgenda actividad = lista.get(position);
        holder.txtid.setText(actividad.getId());
        holder.txtfecha.setText(actividad.getFecha());
        holder.txtactividad_cardview.setText(actividad.getActividad());
        holder.txtdescripcion_cardview.setText(actividad.getDescripcion());
        holder.txthorainicio.setText(actividad.getHorainicio());
        holder.txthorafin.setText(actividad.getHorafin());
        Picasso.get().load(actividad.getImagen()).into(holder.imgpicturecard);

        holder.imgeliminar.setOnClickListener(v -> new MaterialAlertDialogBuilder(this.actividad, R.style.RoundShapeTheme)
                .setMessage("¿Esta seguro de eliminar la actividad?")
                .setTitle("Confirmación")
                .setIcon(R.drawable.ic_warning)
                .setNegativeButton("Cancelar", (dialog, which) -> {
                })
                .setPositiveButton("Sí,aceptar.", (dialog, which) -> {
                    FirebaseDatabase database = FirebaseDatabase.getInstance();
                    DatabaseReference reference = database.getReference("ACTIVIDADES");
                    reference.child(actividad.getId()).removeValue();

                    Snackbar snackbar = Snackbar.make(v, "Actividad eliminada.", Snackbar.LENGTH_LONG);
                    snackbar.show();
                })
                .show());

        holder.imgeditar.setOnClickListener(v -> {
            AppCompatActivity activity = (AppCompatActivity) v.getContext();
            Fragment agregarActividad = new AgregarActividadesFragment();
            Bundle bundle = new Bundle();
            bundle.putString("btnActualizar","Actualizar");
            bundle.putString("txtid_recibido", actividad.getId());
            bundle.putString("txtactividad_agregar", actividad.getActividad());
            bundle.putString("txtfecha_agregar", actividad.getFecha());
            bundle.putString("txthorainicio_agregar", actividad.getHorainicio());
            bundle.putString("txthorafin_agregar", actividad.getHorafin());
            bundle.putString("txtdescripcion_agregar", actividad.getDescripcion());
            bundle.putString("imgpicturecard", actividad.getImagen());
            agregarActividad.setArguments(bundle);

            activity.getSupportFragmentManager().beginTransaction()
                    .setCustomAnimations(R.anim.slide_in, R.anim.fade_out, R.anim.fade_in, R.anim.slide_out)
                    .replace(R.id.container, agregarActividad)
                    .addToBackStack(null)
                    .commit();

        });
    }

    @Override
    public int getItemCount() {
        return lista.size();
    }

    public class AgendaHolder extends RecyclerView.ViewHolder {

        TextView txtid, txtfecha, txtactividad_cardview, txtdescripcion_cardview, txthorainicio, txthorafin,
                txtfecha_agregar, txthorainicio_agregar, txthorafin_agregar, txtid_recibido;

        ImageView imgpicturecard;
        ImageView imgeliminar, imgeditar;
        TextInputEditText txtactividad_agregar, txtdescripcion_agregar;

        public AgendaHolder(@NonNull View itemView) {
            super(itemView);
            txtid = itemView.findViewById(R.id.txtid);
            txtfecha = itemView.findViewById(R.id.txtfecha);
            txtactividad_cardview = itemView.findViewById(R.id.txtactividad_cardview);
            txtdescripcion_cardview = itemView.findViewById(R.id.txtdescripcion_cardview);
            txthorainicio = itemView.findViewById(R.id.txthorainicio);
            txthorafin = itemView.findViewById(R.id.txthorafin);
            imgpicturecard = itemView.findViewById(R.id.imgpicturecard);
            imgeliminar = itemView.findViewById(R.id.imgeliminar);
            imgeditar = itemView.findViewById(R.id.imgeditar);

            txtid_recibido = itemView.findViewById(R.id.txtid_recibido);
            txtactividad_agregar = itemView.findViewById(R.id.txtactividad_agregar);
            txtdescripcion_agregar = itemView.findViewById(R.id.txtdescripcion_agregar);
            txtfecha_agregar = itemView.findViewById(R.id.txtfecha_agregar);
            txthorainicio_agregar = itemView.findViewById(R.id.txthorainicio_agregar);
            txthorafin_agregar = itemView.findViewById(R.id.txthorafin_agregar);
        }
    }
}
