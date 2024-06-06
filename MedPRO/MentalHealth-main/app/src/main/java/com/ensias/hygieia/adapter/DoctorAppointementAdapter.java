package com.ensias.hygieia.adapter;

import android.annotation.SuppressLint;
import android.content.Context;
import android.database.sqlite.SQLiteDatabase;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ensias.hygieia.R;
import com.ensias.hygieia.fireStoreApi.ApointementInformationHelper;
import com.ensias.hygieia.fireStoreApi.DatabaseHelper;
import com.ensias.hygieia.model.ApointementInformation;

import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class DoctorAppointementAdapter extends RecyclerView.Adapter<DoctorAppointementAdapter.MyDoctorAppointementHolder> {
    private List<ApointementInformation> appointments;
    private Context context;
    private SQLiteDatabase database;

    private static final int DEFAULT_IMAGE_RESOURCE = R.drawable.userprofile;

    public DoctorAppointementAdapter(List<ApointementInformation> appointments, Context context, SQLiteDatabase database) {
        this.appointments = appointments;
        this.context = context;
        this.database = database;
    }

    // Constructor to accept and assign the context
    public DoctorAppointementAdapter(Context context, List<ApointementInformation> appointments) {
        this.context = context;
        this.appointments = appointments;
    }

    @NonNull
    @Override
    public MyDoctorAppointementHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_appointement_item, parent, false);
        return new MyDoctorAppointementHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull MyDoctorAppointementHolder myDoctorAppointementHolder, @SuppressLint("RecyclerView") int position) {
        ApointementInformation apointementInformation = appointments.get(position);

        myDoctorAppointementHolder.dateAppointement.setText(apointementInformation.getTime());
        myDoctorAppointementHolder.patientName.setText(apointementInformation.getPatientName());
        myDoctorAppointementHolder.appointementType.setText(apointementInformation.getApointementType());
        Picasso.with(context)
                .load(DEFAULT_IMAGE_RESOURCE)
                .placeholder(DEFAULT_IMAGE_RESOURCE)
                .fit()
                .centerCrop()
                .into(myDoctorAppointementHolder.patient_image);
        myDoctorAppointementHolder.approveBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apointementInformation.setType("Acceptat");
                ApointementInformationHelper.updateAppType("Acceptat", apointementInformation.getId() );
                appointments.remove(position);
                notifyDataSetChanged();
            }
        });

        myDoctorAppointementHolder.cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apointementInformation.setType("Refuzat");
                ApointementInformationHelper.updateAppType("Refuzat", apointementInformation.getId() );
                appointments.remove(position);
                notifyDataSetChanged();
            }
        });

    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }



    class MyDoctorAppointementHolder extends RecyclerView.ViewHolder {
        TextView dateAppointement;
        TextView patientName;
        Button approveBtn;
        Button cancelBtn;
        TextView appointementType;
        ImageView patient_image;

        public MyDoctorAppointementHolder(@NonNull View itemView) {
            super(itemView);
            dateAppointement = itemView.findViewById(R.id.appointement_date);
            patientName = itemView.findViewById(R.id.patient_name);
            approveBtn = itemView.findViewById(R.id.btn_accept);
            cancelBtn = itemView.findViewById(R.id.btn_decline);
            appointementType = itemView.findViewById(R.id.appointement_type);
            patient_image = itemView.findViewById(R.id.patient_image);
        }
    }
}

