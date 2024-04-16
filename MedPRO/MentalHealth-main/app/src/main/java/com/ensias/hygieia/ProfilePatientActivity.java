package com.ensias.hygieia;

import android.app.AlertDialog;
import android.content.Intent;
import android.graphics.drawable.Drawable;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.material.textview.MaterialTextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;
import dmax.dialog.SpotsDialog;


public class ProfilePatientActivity extends AppCompatActivity {
    private MaterialTextView doctorName;
    private MaterialTextView doctorSpe;
    private MaterialTextView doctorPhone;
    private MaterialTextView doctorEmail;
    private MaterialTextView doctorAddress;
    private MaterialTextView doctorAbout;
    private ImageView doctorImage;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_doctor);

        doctorName = findViewById(R.id.doctor_name);
        doctorSpe = findViewById(R.id.doctor_specialitate);
        doctorPhone = findViewById(R.id.doctor_phone);
        doctorEmail = findViewById(R.id.doctor_email);
        doctorAddress = findViewById(R.id.doctor_address);
        doctorAbout = findViewById(R.id.doctor_about);
        Drawable defaultImage = getResources().getDrawable(R.drawable.userprofile); //default user image
        AlertDialog dialog = new SpotsDialog.Builder().setContext(this).setCancelable(true).build();
        dialog.show();


        //display profile image

        // Find the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);
        //getSupportActionBar().setLogo(R.mipmap.ic_launcher);
        //getSupportActionBar().setDisplayUseLogoEnabled(true);

        getSupportActionBar().setDisplayShowTitleEnabled(false);
        // Get access to the custom title view
        TextView mTitle = (TextView) toolbar.findViewById(R.id.toolbar_title);
    }

    // Menu icons are inflated just as they were with actionbar
    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        // Inflate the menu; this adds items to the action bar if it is present.
        getMenuInflater().inflate(R.menu.top_app_bar, menu);
        return true;
    }

    //Handling Action Bar button click
    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        // Handle item selection
        switch (item.getItemId()) {
            //Back button
            case R.id.back:
                //If this activity started from other activity
                finish();
                startHomeActivity();
                return true;

            case R.id.edit:
                //If the edit button is clicked.
                startEditActivity();
                return true;
            default:
                return super.onOptionsItemSelected(item);
        }
    }

    private void startHomeActivity() {
        Intent intent = new Intent(this, HomeActivity.class);
        startActivity(intent);
        finish();
    }

    private void startEditActivity() {
        Intent intent = new Intent(this, EditProfilePatientActivity.class);
        intent.putExtra("CURRENT_NAME", doctorName.getText().toString());
        intent.putExtra("CURRENT_PHONE", doctorPhone.getText().toString());
        intent.putExtra("CURRENT_ADDRESS", doctorAddress.getText().toString());
        startActivity(intent);
        finish();
    }
}
