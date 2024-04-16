package com.ensias.hygieia;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.ensias.hygieia.adapter.ConfirmedAppointmentsAdapter;
import com.ensias.hygieia.fireStoreApi.ApointementInformationHelper;
import com.ensias.hygieia.fireStoreApi.DatabaseHelper;
import com.ensias.hygieia.fireStoreApi.DoctorHelper;
import com.ensias.hygieia.fireStoreApi.UserHelper;
import com.ensias.hygieia.model.ApointementInformation;


import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class ConfirmedAppointmensActivity extends AppCompatActivity {
    private DatabaseHelper databaseHelper;
    private ConfirmedAppointmentsAdapter adapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        UserHelper.init(database);
        DoctorHelper.init(database);
        ApointementInformationHelper.init(database);
        setContentView(R.layout.activity_confirmed_appointements);

        setUpRecyclerView();
    }

    public void setUpRecyclerView(){
        SharedPreferences sharedPrefs = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        final String doctorID = sharedPrefs.getString("email", "");
        final String nume = sharedPrefs.getString("nume", "");

       // List<ApointementInformation> appointments = ApointementInformationHelper.getConfirmedAppointmentInformation(doctorID);


        List<ApointementInformation> otherappointments = new ArrayList<>();
        ApointementInformation appointment1 = new ApointementInformation("Julia Popescu", "05.11.2023", doctorID, nume, "julia_popescu@yahoo.com", "Confirmat", "Consultatie Online", 0);
        ApointementInformation appointment2 = new ApointementInformation("Perseus Cozma", "05.13.2023", doctorID, nume, "perseus_cozma@yahoo.com", "Confirmat", "Consultatie Online", 0);
        otherappointments.add(appointment1);
        otherappointments.add(appointment2);

        List<ApointementInformation> allAppointments = new ArrayList<>();
        allAppointments.addAll(otherappointments);
        //allAppointments.addAll(appointments);

        adapter = new ConfirmedAppointmentsAdapter(allAppointments);
        //List current appointments
        RecyclerView recyclerView = findViewById(R.id.confirmed_appointements_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }


}
