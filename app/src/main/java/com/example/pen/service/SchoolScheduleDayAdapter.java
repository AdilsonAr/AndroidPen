package com.example.pen.service;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pen.R;
import com.example.pen.utility.SchoolSubjectDay;

import java.util.ArrayList;
import java.util.List;

public class SchoolScheduleDayAdapter extends RecyclerView.Adapter<SchoolScheduleDayAdapter.ViewHolder>{
    //region VARIABLES
    private RecyclerView.RecycledViewPool rcvpViewPool
            = new RecyclerView.RecycledViewPool();
    private final String [] dayList = {
            "Lunes",
            "Martes",
            "Miercoles",
            "Jueves",
            "Viernes",
            "Sabado",
            "Domingo"};
    private List<SchoolSubjectDay> daysAndSubjects;
    //endregion

    //region CONSTRUCTOR

    public SchoolScheduleDayAdapter(List<SchoolSubjectDay> daysAndSubjects) {
        this.daysAndSubjects = daysAndSubjects;
    }

    //endregion

    //region INNER_CLASS
    public class ViewHolder extends RecyclerView.ViewHolder{
        //variables
        private TextView txvDay;
        private Button btnAdd;
        private RecyclerView rcvSubjectList;

        public ViewHolder(View itemView){
            super(itemView);
            txvDay = itemView.findViewById(R.id.txvDay);
            btnAdd = itemView.findViewById(R.id.btnAdd);
            rcvSubjectList = itemView.findViewById(R.id.rcvSubjectList);
        }//Fin ViewHolder
    }
    //endregion INNER_CLASS

    //region PROPIEDADES
    public List<SchoolSubjectDay> getDaysAndSubjects() {
        return daysAndSubjects;
    }

    public void setDaysAndSubjects(List<SchoolSubjectDay> daysAndSubjects) {
        this.daysAndSubjects = daysAndSubjects;
    }

    //endregion

    @NonNull
    @Override
    public SchoolScheduleDayAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //variables
        View view;
        SchoolScheduleDayAdapter.ViewHolder viewHolder;

        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.crvly_school_schedule_day, parent, false);
        viewHolder = new SchoolScheduleDayAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SchoolScheduleDayAdapter.ViewHolder holder, int position) {
        holder.txvDay.setText(dayList[position]);

        //si la posicion actual no excede el tamanno del arreglo
        if(getDaysAndSubjects().size() > position){
            SchoolSubjectDay dayAndSubjects =
                    getDaysAndSubjects().get(position);

            //crear el layout manager con los item iniciales precargados
            LinearLayoutManager llm = new LinearLayoutManager(
                    holder.rcvSubjectList.getContext(),
                    LinearLayoutManager.VERTICAL,
                    false
            );
            //definir el tamanno de  los elementos precargados
            llm.setInitialPrefetchItemCount(dayAndSubjects.getSubjectsInDay().size());

            //crear el adaptador para las asignaturas del dia
            SchoolSubjectAdapter ssa =
                    new SchoolSubjectAdapter(dayAndSubjects.getSubjectsInDay());

            //establecer el adaptador en el reciclerview interno
            holder.rcvSubjectList.setLayoutManager(llm);
            holder.rcvSubjectList.setAdapter(ssa);
            holder.rcvSubjectList.setRecycledViewPool(rcvpViewPool);
            holder.rcvSubjectList.setNestedScrollingEnabled(false);
        }
    }

    @Override
    public int getItemCount() {
        return dayList.length;
    }
}
