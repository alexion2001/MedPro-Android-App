package com.ensias.hygieia;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.ensias.hygieia.adapter.MyPatientsAdapter;
import com.ensias.hygieia.fireStoreApi.ApointementInformationHelper;
import com.ensias.hygieia.fireStoreApi.DatabaseHelper;
import com.ensias.hygieia.fireStoreApi.DoctorHelper;
import com.ensias.hygieia.fireStoreApi.PatientHelper;
import com.ensias.hygieia.fireStoreApi.UserHelper;
import com.ensias.hygieia.model.Patient;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class MyPatientsActivity extends AppCompatActivity {

    private DatabaseHelper databaseHelper;
    private MyPatientsAdapter adapter;
    private Context context;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        UserHelper.init(database);
        DoctorHelper.init(database);
        PatientHelper.init(database);
        ApointementInformationHelper.init(database);
        setContentView(R.layout.activity_my_patients);
        setUpRecyclerView();

    }

    public void setUpRecyclerView(){
        SharedPreferences sharedPrefs = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        final String doctorID = sharedPrefs.getString("email", "");
        final String nume = sharedPrefs.getString("nume", "");

//        List<ApointementInformation> appointments = ApointementInformationHelper.getConfirmedAppointmentInformation(doctorID);
//        List<Patient> myPatientsList = new ArrayList<>();
//
//        for (ApointementInformation appointment : appointments) {
//            Patient patient = PatientHelper.getMyPatients(appointment.getPatientId());
//            myPatientsList.add(patient);
//        }


        List<Patient> otherappointments = new ArrayList<>();
        Patient appointment1 = new Patient("Julia Popescu", "0835675554", "julia_popescu@yahoo.com", "12.12.2001");
        Patient appointment2 = new Patient("Perseus Cozma", "0513202399", "perseus_cozma@yahoo.com", "10.09.2004");
        otherappointments.add(appointment1);
        otherappointments.add(appointment2);

        List<Patient> allAppointments = new ArrayList<>();
        allAppointments.addAll(otherappointments);

        adapter = new MyPatientsAdapter(allAppointments);
        //ListMyPatients
        RecyclerView recyclerView = findViewById(R.id.ListMyPatients);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }


}
