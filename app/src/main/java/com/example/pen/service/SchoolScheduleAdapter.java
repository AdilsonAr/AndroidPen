package com.example.pen.service;

import android.view.LayoutInflater;
import android.view.MotionEvent;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.core.view.MotionEventCompat;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;
import com.example.pen.R;
import com.example.pen.utility.IActionOnViewAtPossition;
import com.example.pen.utility.SchoolSubjectDay;

import java.util.List;

public class SchoolScheduleAdapter extends RecyclerView.Adapter<SchoolScheduleAdapter.ViewHolder>{
    //region VARIABLES
    private RecyclerView.RecycledViewPool rcvpViewPool
            = new RecyclerView.RecycledViewPool();
    private List<SchoolSubjectDay> daysAndSubjects;
    private IActionOnViewAtPossition btnShowMoreOnClickListener;
    //endregion

    //region CONSTRUCTOR

    public SchoolScheduleAdapter(List<SchoolSubjectDay> daysAndSubjects) {
        this.daysAndSubjects = daysAndSubjects;
    }

    //endregion

    //region INNER TYPES
    public class ViewHolder extends RecyclerView.ViewHolder{
        //variables
        private TextView txvDay;
        private Button btnShowMore;
        private RecyclerView rcvSubjectList;

        public ViewHolder(View itemView){
            super(itemView);
            txvDay = itemView.findViewById(R.id.txvDay);
            btnShowMore = itemView.findViewById(R.id.btnShowMore);
            rcvSubjectList = itemView.findViewById(R.id.rcvSubjectList);
        }//Fin ViewHolder
    }

    //endregion INNER TYPES

    //region PROPIEDADES
    public List<SchoolSubjectDay> getDaysAndSubjects() {
        return daysAndSubjects;
    }

    public void setDaysAndSubjects(List<SchoolSubjectDay> daysAndSubjects) {
        this.daysAndSubjects = daysAndSubjects;
    }

    public IActionOnViewAtPossition getBtnShowMoreOnClickListener() {
        return btnShowMoreOnClickListener;
    }

    public void setBtnShowMoreOnClickListener(IActionOnViewAtPossition btnShowMoreOnClickListener) {
        this.btnShowMoreOnClickListener = btnShowMoreOnClickListener;
    }
//endregion

    @NonNull
    @Override
    public SchoolScheduleAdapter.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        //variables
        View view;
        SchoolScheduleAdapter.ViewHolder viewHolder;

        view = LayoutInflater.from(parent.getContext())
                .inflate(R.layout.crvly_school_schedule_day, parent, false);
        viewHolder = new SchoolScheduleAdapter.ViewHolder(view);

        return viewHolder;
    }

    @Override
    public void onBindViewHolder(@NonNull SchoolScheduleAdapter.ViewHolder holder, int position) {
        holder.txvDay.setText(getDaysAndSubjects().get(position).getWeekDay().name());
        holder.btnShowMore.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                btnShowMoreOnClickListener.action(v, position);
            }
        });

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
            //holder.rcvSubjectList.setNestedScrollingEnabled(true);
            holder.rcvSubjectList.setAdapter(ssa);
            //esto permite que los recyclerview internos puedan existir (((
            holder.rcvSubjectList.setRecycledViewPool(rcvpViewPool);

            //para que los rcv internos puedan ser scrolleados
            holder.rcvSubjectList.setOnTouchListener(new View.OnTouchListener() {
                @Override
                public boolean onTouch(View view, MotionEvent motionEvent) {
                    view.getParent().requestDisallowInterceptTouchEvent(true);
                    switch (motionEvent.getAction() & MotionEvent.ACTION_MASK) {
                        case MotionEvent.ACTION_UP:
                            view.getParent().requestDisallowInterceptTouchEvent(false);
                            break;
                    }
                    return false;
                }
            });

        }
    }

    @Override
    public int getItemCount() {
        return getDaysAndSubjects().size();
    }
}
