package com.ensias.hygieia.adapter;

import android.annotation.SuppressLint;
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

public class DoctorAppointementAdapter extends RecyclerView.Adapter<DoctorAppointementAdapter.MyDoctorAppointementHolder> {
    private List<ApointementInformation> appointments;
    private Context context;
    private static final int DEFAULT_IMAGE_RESOURCE = R.drawable.userprofile;
    public DoctorAppointementAdapter(List<ApointementInformation> appointments) {
        this.appointments = appointments;
        this.context = context;
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
                apointementInformation.setType("Accepted");
                // Update appointment status in the database

                // ... Your code to update the appointment status in SQLite

                appointments.remove(position);
                notifyDataSetChanged();
            }
        });

        myDoctorAppointementHolder.cancelBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                apointementInformation.setType("Refused");
                // Update appointment status in the database

                // ... Your code to update the appointment status in SQLite

                appointments.remove(position);
                notifyDataSetChanged();
            }
        });

        // Load patient image from storage
//        String imageId = apointementInformation.getPatientId() + ".jpg";
//        pathReference = FirebaseStorage.getInstance().getReference().child("DoctorProfile/" + imageId);
//        pathReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//            @Override
//            public void onSuccess(Uri uri) {
//
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception exception) {
//                // Handle any errors
//            }
//        });
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




//public class DoctorAppointementAdapter extends FirestoreRecyclerAdapter<ApointementInformation, DoctorAppointementAdapter.MyDoctorAppointementHolder> {
//    StorageReference pathReference ;
//
//
//    /**
//     * Create a new RecyclerView adapter that listens to a Firestore Query.
//     * @param options
//     */
//    public DoctorAppointementAdapter(@NonNull FirestoreRecyclerOptions<ApointementInformation> options) {
//        super(options);
//    }
//
//    @Override
//    protected void onBindViewHolder(@NonNull MyDoctorAppointementHolder myDoctorAppointementHolder, @SuppressLint("RecyclerView") int position, @NonNull final ApointementInformation apointementInformation) {
//        myDoctorAppointementHolder.dateAppointement.setText(apointementInformation.getTime());
//        myDoctorAppointementHolder.patientName.setText(apointementInformation.getPatientName());
//        myDoctorAppointementHolder.appointementType.setText(apointementInformation.getApointementType());
//        myDoctorAppointementHolder.approveBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                apointementInformation.setType("Accepted");
//                FirebaseFirestore.getInstance().collection("Patient").document(apointementInformation.getPatientId()).collection("calendar")
//                        .document(apointementInformation.getTime().replace("/","_")).set(apointementInformation);
//                FirebaseFirestore.getInstance().collection("Doctor").document(apointementInformation.getDoctorId()).collection("calendar")
//                        .document(apointementInformation.getTime().replace("/","_")).set(apointementInformation);
//
////////////// here add patient friend to doctor
//
//                FirebaseFirestore.getInstance().document("Patient/"+apointementInformation.getPatientId()).get()
//                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                            @Override
//                            public void onSuccess(DocumentSnapshot documentSnapshot) {
//                                FirebaseFirestore.getInstance().collection("Doctor").document(apointementInformation.getDoctorId()+"")
//                                        .collection("MyPatients").document(apointementInformation.getPatientId()).set(documentSnapshot.toObject(Patient.class));
//                            }
//                        });
//                FirebaseFirestore.getInstance().document("Doctor/"+apointementInformation.getDoctorId()).get()
//                        .addOnSuccessListener(new OnSuccessListener<DocumentSnapshot>() {
//                            @Override
//                            public void onSuccess(DocumentSnapshot documentSnapshot) {
//                                FirebaseFirestore.getInstance().collection("Patient").document(apointementInformation.getPatientId()+"")
//                                        .collection("MyDoctors").document(apointementInformation.getPatientId()).set(documentSnapshot.toObject(Doctor.class));
//                            }
//                        });
//
//
//                getSnapshots().getSnapshot(position).getReference().delete();
//            }
//        });
//        myDoctorAppointementHolder.cancelBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                apointementInformation.setType("Refused");
//                FirebaseFirestore.getInstance().collection("Patient").document(apointementInformation.getPatientId()).collection("calendar")
//                        .document(apointementInformation.getTime().replace("/","_")).set(apointementInformation);
//                getSnapshots().getSnapshot(position).getReference().delete();
//            }
//        });
//
//        String imageId = apointementInformation.getPatientId()+".jpg"; //add a title image
//        pathReference = FirebaseStorage.getInstance().getReference().child("DoctorProfile/"+ imageId); //storage the image
//        pathReference.getDownloadUrl().addOnSuccessListener(new OnSuccessListener<Uri>() {
//            @Override
//            public void onSuccess(Uri uri) {
//                Picasso.with(myDoctorAppointementHolder.patient_image.getContext())
//                        .load(uri)
//                        .placeholder(R.mipmap.ic_launcher)
//                        .fit()
//                        .centerCrop()
//                        .into(myDoctorAppointementHolder.patient_image);//Image location
//
//                // profileImage.setImageURI(uri);
//            }
//        }).addOnFailureListener(new OnFailureListener() {
//            @Override
//            public void onFailure(@NonNull Exception exception) {
//                // Handle any errors
//            }
//        });
//
//    }
//
//
//
//    @NonNull
//    @Override
//    public MyDoctorAppointementHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_appointement_item, parent, false);
//        return new MyDoctorAppointementHolder(v);
//    }
//
//    class MyDoctorAppointementHolder extends RecyclerView.ViewHolder{
//        //Here we hold the MyDoctorAppointementItems
//        TextView dateAppointement;
//        TextView patientName;
//        Button approveBtn;
//        Button cancelBtn;
//        TextView appointementType;
//        ImageView patient_image;
//        public MyDoctorAppointementHolder(@NonNull View itemView) {
//            super(itemView);
//            dateAppointement = itemView.findViewById(R.id.appointement_date);
//            patientName = itemView.findViewById(R.id.patient_name);
//            approveBtn = itemView.findViewById(R.id.btn_accept);
//            cancelBtn = itemView.findViewById(R.id.btn_decline);
//            appointementType = itemView.findViewById(R.id.appointement_type);
//            patient_image = itemView.findViewById(R.id.patient_image);
//        }
//    }
//
//
//
//
//}
