package com.ensias.hygieia;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.ensias.hygieia.adapter.ConfirmedAppointmentsAdapter;
import com.ensias.hygieia.adapter.DoctorAppointementAdapter;
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
    private Context context;

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
        List<ApointementInformation> appointments = ApointementInformationHelper.getAcceptedAppointmentInformation(doctorID);


        List<ApointementInformation> allAppointments = new ArrayList<>();
        allAppointments.addAll(appointments);
        ConfirmedAppointmentsAdapter adapter = new ConfirmedAppointmentsAdapter(allAppointments, this);

        //List current appointments
        RecyclerView recyclerView = findViewById(R.id.confirmed_appointements_list);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }


}
