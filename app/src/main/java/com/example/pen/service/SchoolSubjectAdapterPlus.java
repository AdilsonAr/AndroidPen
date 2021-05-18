package com.example.pen.service;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.pen.R;
import com.example.pen.model.SchoolSubject;
import com.example.pen.utility.IActionOnViewAtPossition;

import java.text.DateFormat;
import java.text.SimpleDateFormat;
import java.util.List;

public class SchoolSubjectAdapterPlus extends RecyclerView.Adapter<SchoolSubjectAdapterPlus.ViewHolder>{
    //region VARIABLES
    private List<SchoolSubject> subjectList;
    private IActionOnViewAtPossition btnOptionsOnClickListener;
    //endregion

    //region CONSTRUCTOR
    public SchoolSubjectAdapterPlus(List<SchoolSubject> subjectList) {
        this.subjectList = subjectList;
    }
    //endregion

    //region INNER_CLASS
    public class ViewHolder extends RecyclerView.ViewHolder{
        //variables
        private TextView txvSubject;
        private TextView txvFrom;
        private TextView txvUntil;
        private Button btnOptions;

        public ViewHolder(View itemView){
            super(itemView);
            txvSubject = itemView.findViewById(R.id.txvSsdapSubject);
            txvFrom = itemView.findViewById(R.id.txvSsdapFrom);
            txvUntil = itemView.findViewById(R.id.txvSsdapUntil);
            btnOptions = itemView.findViewById(R.id.btnSsdapOptions);
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

    public IActionOnViewAtPossition getBtnOptionsOnClickListener() {
        return btnOptionsOnClickListener;
    }

    public void setBtnOptionsOnClickListener(IActionOnViewAtPossition btnOptionsOnClickListener) {
        this.btnOptionsOnClickListener = btnOptionsOnClickListener;
    }

    //endregion

    @NonNull
    @Override
    public SchoolSubjectAdapterPlus.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //variables
        View view;
        SchoolSubjectAdapterPlus.ViewHolder viewHolder;

        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.crvly_school_schedule_day_appointment_plus, parent, false);
        viewHolder = new SchoolSubjectAdapterPlus.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SchoolSubjectAdapterPlus.ViewHolder holder, int position) {
        DateFormat df = new SimpleDateFormat("hh:mm");

        holder.txvSubject.setText(getSubjectList().get(position).getName());
        holder.txvFrom.setText("Desde: "+df.format(getSubjectList().get(position).getFromTime()));
        holder.txvUntil.setText("Hasta: "+df.format(getSubjectList().get(position).getUntilTime()));
        holder.btnOptions.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnOptionsOnClickListener.action(v, position);
            }
        });
    }

    @Override
    public int getItemCount() {
        return getSubjectList().size();
    }
}
