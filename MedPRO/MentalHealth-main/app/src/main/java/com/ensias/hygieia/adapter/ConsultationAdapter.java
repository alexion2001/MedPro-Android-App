package com.ensias.hygieia.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ensias.hygieia.R;


public class ConsultationAdapter  {

    public ConsultationAdapter() {
        super();
    }


    class Fisa_Medicala extends RecyclerView.ViewHolder {
        TextView doctor_name;
        TextView type;
        Button btn;
        TextView appointement_month;
        TextView appointement_day;
        TextView appointement_day_name;
        TextView doctor_view_title;

        public Fisa_Medicala(View itemView) {
            super(itemView);
            doctor_name = itemView.findViewById(R.id.doctor_name);
            type = itemView.findViewById(R.id.text_view_description);
            btn = itemView.findViewById(R.id.voir_fiche_btn);
            appointement_month = itemView.findViewById(R.id.appointement_month);
            appointement_day = itemView.findViewById(R.id.appointement_day);
            appointement_day_name = itemView.findViewById(R.id.appointement_day_name);
            doctor_view_title = itemView.findViewById(R.id.doctor_view_title);
        }
    }
}
