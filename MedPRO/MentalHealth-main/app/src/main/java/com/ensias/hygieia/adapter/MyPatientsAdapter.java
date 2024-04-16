package com.ensias.hygieia.adapter;

import android.content.Context;
import android.content.Intent;
import android.net.Uri;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ensias.hygieia.R;
import com.ensias.hygieia.model.Patient;
import com.squareup.picasso.Picasso;


import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.List;

public class MyPatientsAdapter extends RecyclerView.Adapter<MyPatientsAdapter.MyPatientsHolder> {
    private List<Patient> patients;
    private static final int DEFAULT_IMAGE_RESOURCE = R.drawable.userprofile;

    private Context context;

    public MyPatientsAdapter(List<Patient> patients) {
        this.patients = patients;
    }

    @NonNull
    @Override
    public MyPatientsHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.my_patient_item, parent, false);
        return new MyPatientsHolder(v);
    }

    @Override
    public void onBindViewHolder(@NonNull final MyPatientsHolder holder, int position) {
        Patient patient = patients.get(position);
        holder.textViewTitle.setText(patient.getName());
       holder.textViewTelephone.setText(patient.getTel());

      

        holder.callBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                openPage(holder.contactButton.getContext(), patient.getTel());
            }
        });


    }

    private void openPage(Context wf, String phoneNumber) {
        Intent intent = new Intent(Intent.ACTION_DIAL, Uri.parse("tel:" + phoneNumber));
        wf.startActivity(intent);
    }



    @Override
    public int getItemCount() {
        return patients.size();
    }

    class MyPatientsHolder extends RecyclerView.ViewHolder {
        //Here we hold the MyDoctorItems
        Button callBtn;
        TextView textViewTitle;
        TextView textViewTelephone;
        ImageView imageViewPatient;
        Button contactButton;
        RelativeLayout parentLayout;

        public MyPatientsHolder(@NonNull View itemView) {
            super(itemView);
            callBtn = itemView.findViewById(R.id.callBtn);
            textViewTitle = itemView.findViewById(R.id.patient_view_title);
            textViewTelephone = itemView.findViewById(R.id.text_view_telephone);
            imageViewPatient = itemView.findViewById(R.id.patient_item_image);
            contactButton = itemView.findViewById(R.id.contact);
            parentLayout = itemView.findViewById(R.id.parent_layout);
        }
    }
}