package com.example.pen.service;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pen.model.SchoolSubject;
import com.example.pen.R;
import com.example.pen.utility.VariousUtilities;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class SchoolSubjectAdapter extends RecyclerView.Adapter<SchoolSubjectAdapter.ViewHolder>{
    //region VARIABLES
    private List<SchoolSubject> subjectList;
    //endregion

    //region CONSTRUCTOR
    public SchoolSubjectAdapter(List<SchoolSubject> subjectList) {
        this.subjectList = subjectList;
    }
    //endregion

    //region INNER_CLASS
    public class ViewHolder extends RecyclerView.ViewHolder{
        //variables
        private TextView txvSubject;
        private TextView txvFrom;
        private TextView txvUntil;

        public ViewHolder(View itemView){
            super(itemView);
            txvSubject = itemView.findViewById(R.id.txvSsdaSubject);
            txvFrom = itemView.findViewById(R.id.txvSsdaFrom);
            txvUntil = itemView.findViewById(R.id.txvSsdaUntil);
        }//Fin ViewHolder
    }
    //endregion INNER_CLASS

    //region PROPIEDADES

    public List<SchoolSubject> getSubjectList() {
        return subjectList;
    }

    public void setSubjectList(List<SchoolSubject> subjectList) {
        this.subjectList = subjectList;
    }

    //endregion

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //variables
        View view;
        ViewHolder viewHolder;

        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.crvly_school_schedule_day_appointment, parent, false);
        viewHolder = new ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, int position) {
        DateFormat df = VariousUtilities.getHourFormat();

        holder.txvSubject.setText(getSubjectList().get(position).getName());
        holder.txvFrom.setText("Desde: "+df.format(getSubjectList().get(position).getFromTime()));
        holder.txvUntil.setText("Hasta: "+df.format(getSubjectList().get(position).getUntilTime()));
    }

    @Override
    public int getItemCount() {
        return getSubjectList().size();
    }
}
