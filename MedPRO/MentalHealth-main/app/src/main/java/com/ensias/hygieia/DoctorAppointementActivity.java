package com.ensias.hygieia;

import android.app.Activity;

import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;

import com.ensias.hygieia.adapter.DoctorAppointementAdapter;
import com.ensias.hygieia.fireStoreApi.ApointementInformationHelper;
import com.ensias.hygieia.fireStoreApi.DatabaseHelper;
import com.ensias.hygieia.fireStoreApi.DoctorHelper;
import com.ensias.hygieia.fireStoreApi.UserHelper;
import com.ensias.hygieia.model.ApointementInformation;

import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

public class DoctorAppointementActivity extends Activity {
    private DoctorAppointementAdapter adapter;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);

        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        UserHelper.init(database);
        DoctorHelper.init(database);
        ApointementInformationHelper.init(database);
        setContentView(R.layout.activity_doctor_appointement);
        setUpRecyclerView(database);
    }

    public void setUpRecyclerView(SQLiteDatabase database) {
        // Get the doctors by patient id
        SharedPreferences sharedPrefs = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        final String doctorID = sharedPrefs.getString("email", "");
        final String nume = sharedPrefs.getString("nume", "");

        List<ApointementInformation> appointments = ApointementInformationHelper.getAppointmentInformation(doctorID);

        List<ApointementInformation> allAppointments = new ArrayList<>();
        allAppointments.addAll(appointments);
        DoctorAppointementAdapter adapter = new DoctorAppointementAdapter(allAppointments, this, database);

        // Set up RecyclerView
        RecyclerView recyclerView = findViewById(R.id.DoctorAppointement);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this));
        recyclerView.setAdapter(adapter);
    }
}





