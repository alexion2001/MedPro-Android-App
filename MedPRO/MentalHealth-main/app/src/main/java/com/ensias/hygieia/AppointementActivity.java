package com.ensias.hygieia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.DatePickerDialog;
import android.app.TimePickerDialog;
import android.content.ContentValues;
import android.content.Context;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;
import android.widget.DatePicker;
import android.widget.TimePicker;

import com.ensias.hygieia.fireStoreApi.DatabaseHelper;

import java.util.Calendar;

public class AppointementActivity extends AppCompatActivity {

    private EditText editTextPatientId, editTextPatientName, editTextDoctorId, editTextDoctorName, editTextDateTime, editTextAppointmentType;
    private Button buttonSubmit;
    private SQLiteDatabase database;
    private DatabaseHelper dbHelper;
    private Calendar calendar;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_patient_appointments);

        editTextPatientId = findViewById(R.id.editTextPatientId);
        editTextPatientName = findViewById(R.id.editTextPatientName);
        editTextDoctorId = findViewById(R.id.editTextDoctorId);
        editTextDoctorName = findViewById(R.id.editTextDoctorName);
        editTextDateTime = findViewById(R.id.editTextDateTime);
        editTextAppointmentType = findViewById(R.id.editTextAppointmentType);
        buttonSubmit = findViewById(R.id.buttonSubmit);

        dbHelper = new DatabaseHelper(this);
        database = dbHelper.getWritableDatabase();
        SharedPreferences sharedPreferences = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        String patientEmail = sharedPreferences.getString("email", "");
        String patientName = sharedPreferences.getString("nume", "");

        editTextPatientId.setText(patientEmail);
        editTextPatientName.setText(patientName);

        editTextDateTime.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                showDateTimePicker();
            }
        });

        buttonSubmit.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                addAppointmentInformation();
            }
        });
    }

    private void showDateTimePicker() {
        calendar = Calendar.getInstance();
        DatePickerDialog datePickerDialog = new DatePickerDialog(AppointementActivity.this, new DatePickerDialog.OnDateSetListener() {
            @Override
            public void onDateSet(DatePicker view, int year, int monthOfYear, int dayOfMonth) {
                calendar.set(Calendar.YEAR, year);
                calendar.set(Calendar.MONTH, monthOfYear);
                calendar.set(Calendar.DAY_OF_MONTH, dayOfMonth);

                TimePickerDialog timePickerDialog = new TimePickerDialog(AppointementActivity.this, new TimePickerDialog.OnTimeSetListener() {
                    @Override
                    public void onTimeSet(TimePicker view, int hourOfDay, int minute) {
                        calendar.set(Calendar.HOUR_OF_DAY, hourOfDay);
                        calendar.set(Calendar.MINUTE, minute);

                        String dateTime = String.format("%04d-%02d-%02d %02d:%02d", year, monthOfYear + 1, dayOfMonth, hourOfDay, minute);
                        editTextDateTime.setText(dateTime);
                    }
                }, calendar.get(Calendar.HOUR_OF_DAY), calendar.get(Calendar.MINUTE), true);
                timePickerDialog.show();
            }
        }, calendar.get(Calendar.YEAR), calendar.get(Calendar.MONTH), calendar.get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    private void addAppointmentInformation() {
        String patientId = editTextPatientId.getText().toString();
        String patientName = editTextPatientName.getText().toString();
        String doctorId = editTextDoctorId.getText().toString();
        String doctorName = editTextDoctorName.getText().toString();
        String dateTime = editTextDateTime.getText().toString();
        String appointmentType = editTextAppointmentType.getText().toString();

        ContentValues values = new ContentValues();
        values.put("patientName", patientName);
        values.put("date", dateTime);
        values.put("doctorId", doctorId);
        values.put("doctorName", doctorName);
        values.put("patientId", patientId);
        values.put("status", "In_Asteptare");
        values.put("appointementType", appointmentType);

        long result = database.insert("appointmentInfo", null, values);

        if (result == -1) {
            Toast.makeText(this, "Failed to add appointment", Toast.LENGTH_SHORT).show();
        } else {
            Toast.makeText(this, "Appointment added successfully", Toast.LENGTH_SHORT).show();
        }
    }
}
