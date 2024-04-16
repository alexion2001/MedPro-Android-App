package com.ensias.hygieia.adapter;

import android.content.Context;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import com.ensias.hygieia.R;
import com.ensias.hygieia.model.ApointementInformation;
import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class ConfirmedAppointmentsAdapter extends RecyclerView.Adapter<ConfirmedAppointmentsAdapter.ConfirmedAppointmentsHolder> {

    private List<ApointementInformation> appointments;
    private Context context;
    private static final int DEFAULT_IMAGE_RESOURCE = R.drawable.userprofile;

    public ConfirmedAppointmentsAdapter(List<ApointementInformation> appointments) {
        this.appointments = appointments;
    }

    @NonNull
    @Override
    public ConfirmedAppointmentsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.confirmed_appointements_item, parent, false);
        return new ConfirmedAppointmentsHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull ConfirmedAppointmentsHolder holder, int position) {
        ApointementInformation appointment = appointments.get(position);
        holder.dateAppointement.setText(appointment.getTime());
        holder.patientName.setText(appointment.getPatientName());
        holder.appointementType.setText(appointment.getApointementType());
        Picasso.with(context)
                .load(DEFAULT_IMAGE_RESOURCE)
                .fit()
                .centerCrop()
                .into(holder.patientImage);

    }

    @Override
    public int getItemCount() {
        return appointments.size();
    }

    class ConfirmedAppointmentsHolder extends RecyclerView.ViewHolder {
        TextView dateAppointement;
        TextView patientName;
        TextView appointementType;
        ImageView patientImage;

        public ConfirmedAppointmentsHolder(@NonNull View itemView) {
            super(itemView);
            dateAppointement = itemView.findViewById(R.id.appointement_date);
            patientName = itemView.findViewById(R.id.patient_name);
            appointementType = itemView.findViewById(R.id.appointement_type);
            patientImage = itemView.findViewById(R.id.patient_image);
            context = itemView.getContext();
        }
    }
}
