package com.ensias.hygieia.adapter;

import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.recyclerview.widget.RecyclerView;

import com.ensias.hygieia.R;

public class ProgramareFizicaAdapter {


    class FicheHolder2 extends RecyclerView.ViewHolder {
        TextView doctor_name;
        TextView type;
        Button btn;
        TextView appointement_month;
        TextView appointement_day;
        TextView appointement_day_name;
        TextView doctor_view_title;

        public FicheHolder2(View itemView) {
            super(itemView);
            type = itemView.findViewById(R.id.text_view_description2);
            appointement_month = itemView.findViewById(R.id.appointement_month);
            appointement_day = itemView.findViewById(R.id.appointement_day);
            appointement_day_name = itemView.findViewById(R.id.appointement_day_name);
            doctor_view_title = itemView.findViewById(R.id.doctor_view_title);
        }
    }
}
