package com.ensias.hygieia;

import androidx.appcompat.app.AppCompatActivity;

import android.app.AlarmManager;
import android.app.DatePickerDialog;
import android.app.NotificationChannel;
import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.DatePicker;
import android.widget.Toast;

import com.ensias.hygieia.Common.Common;
import com.ensias.hygieia.fireStoreApi.DatabaseHelper;
import com.ensias.hygieia.fireStoreApi.DoctorHelper;
import com.ensias.hygieia.fireStoreApi.UserHelper;
import com.ensias.hygieia.databinding.ActivityDoctorHomeBinding;
import com.ensias.hygieia.model.MockTokenManager;

import java.util.Calendar;

public class DoctorHomeActivity extends AppCompatActivity implements DatePickerDialog.OnDateSetListener {
    static String doc;

    Button drugsBtn;
    private MockTokenManager mockTokenManager;

    private DatabaseHelper databaseHelper;
    private ActivityDoctorHomeBinding binding;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        binding = ActivityDoctorHomeBinding.inflate(getLayoutInflater());
        setContentView(binding.getRoot());
        mockTokenManager = new MockTokenManager(this);

        // Set up database
        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        UserHelper.init(database);
        DoctorHelper.init(database);

        // Set up notification channel
        if (Build.VERSION.SDK_INT >= Build.VERSION_CODES.O) {
            NotificationChannel channel = new NotificationChannel(
                    "channel_id", "channel_name", NotificationManager.IMPORTANCE_HIGH
            );
            channel.setDescription("channel_description");

            NotificationManager manager = getSystemService(NotificationManager.class);
            manager.createNotificationChannel(channel);
        }

        // Set up notification intent
//        Intent intent = new Intent(this, NotificationReceiver.class);
//        PendingIntent pendingIntent = PendingIntent.getBroadcast(
//                this, 0, intent, PendingIntent.FLAG_UPDATE_CURRENT
//        );

        // Set up alarm
        SharedPreferences sharedPrefs = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        final String doctorID = sharedPrefs.getString("email", "");
//        if (doctorID != null && !doctorID.isEmpty()) {
//            AlarmManager alarmManager = (AlarmManager) getSystemService(ALARM_SERVICE);
//            alarmManager.setRepeating(
//                    AlarmManager.RTC_WAKEUP,
//                    System.currentTimeMillis() + 1200, // Start after 2 minutes
//                    1200, // Repeat every 2 minutes
//                    pendingIntent
//            );
//        }

        // Set up click listeners
        binding.profile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(DoctorHomeActivity.this, ProfileDoctorActivity.class);
                startActivity(k);
            }
        });

        drugsBtn = findViewById(R.id.drugsBtn);
        drugsBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(DoctorHomeActivity.this, DrugsActivity.class);
                startActivity(k);
            }
        });



        binding.signOutBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                SharedPreferences shared = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                SharedPreferences.Editor edit = shared.edit();
                edit.remove("email");
                edit.remove("rol");
                edit.remove("profile_image");
                edit.remove("nume");
                edit.apply();
                mockTokenManager.clearTokens();

                Toast.makeText(getApplicationContext(), "You exit your account", Toast.LENGTH_SHORT).show();
                Intent intent = new Intent(getApplicationContext(), MainActivity.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });

        binding.btnRequst.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(DoctorHomeActivity.this, ConfirmedAppointmensActivity.class);
                startActivity(k);
            }
        });

        binding.listPatients.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(DoctorHomeActivity.this, MyPatientsActivity.class);
                startActivity(k);
            }
        });

        binding.appointement.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                // doc = FirebaseAuth.getInstance().getCurrentUser().getEmail().toString();
                //showDatePickerDialog(v.getContext());
                Intent k = new Intent(DoctorHomeActivity.this, DoctorAppointementActivity.class);
                startActivity(k);
            }
        });
    }

    public void showDatePickerDialog(Context wf) {
        DatePickerDialog datePickerDialog = new DatePickerDialog(
                wf,
                this,
                Calendar.getInstance().get(Calendar.YEAR),
                Calendar.getInstance().get(Calendar.MONTH),
                Calendar.getInstance().get(Calendar.DAY_OF_MONTH));
        datePickerDialog.show();
    }

    @Override
    public void onDateSet(DatePicker view, int year, int month, int dayOfMonth) {
        String date = "month_day_year: " + month + "_" + dayOfMonth + "_" + year;
        openPage(view.getContext(), doc, date);
    }

    private void openPage(Context wf, String d, String day) {
        Intent i = new Intent(wf, AppointementActivity.class);
        i.putExtra("key1", d + "");
        i.putExtra("key2", day);
        i.putExtra("key3", "doctor");
        wf.startActivity(i);
    }
}
