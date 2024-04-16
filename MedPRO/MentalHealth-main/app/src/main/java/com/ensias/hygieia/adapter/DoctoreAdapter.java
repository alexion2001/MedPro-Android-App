package com.ensias.hygieia.adapter;

import android.content.Context;
import android.content.Intent;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.ensias.hygieia.Common.Common;
import com.ensias.hygieia.R;
import com.ensias.hygieia.TestActivity;
import com.ensias.hygieia.model.Doctor;


import java.util.HashMap;
import java.util.Map;

public class DoctoreAdapter{} //extends RecyclerView.Adapter<Doctor, DoctoreAdapter.DoctoreHolder> {
//    static String doc;
//
//
//    public DoctoreAdapter(@NonNull FirestoreRecyclerOptions<Doctor> options) {
//        super(options);
//    }
//
//    @Override
//    protected void onBindViewHolder(@NonNull final DoctoreHolder doctoreHolder, int i, @NonNull final Doctor doctor) {
//        final TextView t = doctoreHolder.title ;
//        doctoreHolder.title.setText(doctor.getName());
//        doctoreHolder.specialitate.setText("Specialitate : "+doctor.getSpecialitate());
//        final String idPat = FirebaseAuth.getInstance().getCurrentUser().getEmail().toString();
//        final String idDoc = doctor.getEmail();
//        // doctoreHolder.image.setImageURI(Uri.parse("drawable-v24/ic_launcher_foreground.xml"));
//        doctoreHolder.addDoc.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                Map<String, Object> note = new HashMap<>();
//                note.put("id_patient", idPat);
//                note.put("id_doctor", idDoc);
//                doctoreHolder.addDoc.setVisibility(View.INVISIBLE);
//            }
//        });
//        doctoreHolder.appointemenBtn.setOnClickListener(new View.OnClickListener() {
//            @Override
//            public void onClick(View v) {
//                doc= doctor.getEmail();
//                Common.CurreentDoctor = doctor.getEmail();
//                openPage(v.getContext());
//
//            }
//        });
//
//    }
//
//
//    @NonNull
//    @Override
//    public DoctoreHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
//        View v = LayoutInflater.from(parent.getContext()).inflate(R.layout.doctor_item,
//                parent, false);
//        return new DoctoreHolder(v);
//    }
//
//    @Override
//    public void onBindViewHolder(@NonNull Doctor doctor, int i) {
//
//    }
//
//    @Override
//    public int getItemCount() {
//        return 0;
//    }
//
//
//    class DoctoreHolder extends RecyclerView.ViewHolder {
//        Button appointemenBtn;
//        TextView title;
//        TextView specialitate;
//        ImageView image;
//        Button addDoc;
//        Button load;
//        public DoctoreHolder(@NonNull View itemView) {
//            super(itemView);
//            addDoc = itemView.findViewById(R.id.addDocBtn);
//            title= itemView.findViewById(R.id.doctor_view_title);
//            specialitate=itemView.findViewById(R.id.text_view_description);
//            image=itemView.findViewById(R.id.doctor_item_image);
//            appointemenBtn=itemView.findViewById(R.id.appointemenBtn);
//        }
//    }
//    private void openPage(Context wf){
//        Intent i = new Intent(wf, TestActivity.class);
//        wf.startActivity(i);
//    }


//}
