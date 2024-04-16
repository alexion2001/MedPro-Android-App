package com.ensias.hygieia;

import android.app.AlertDialog;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuItem;
import android.widget.ImageView;
import android.widget.TextView;

import com.bumptech.glide.Glide;
import com.ensias.hygieia.fireStoreApi.DatabaseHelper;
import com.ensias.hygieia.fireStoreApi.DoctorHelper;
import com.ensias.hygieia.fireStoreApi.UploadImageHelper;
import com.ensias.hygieia.fireStoreApi.UserHelper;
import com.ensias.hygieia.model.Doctor;
import com.google.android.material.textview.MaterialTextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import java.io.File;

import dmax.dialog.SpotsDialog;

public class ProfileDoctorActivity extends AppCompatActivity {
    private MaterialTextView doctorName;
    private MaterialTextView doctorSpe;
    private MaterialTextView doctorPhone;
    private MaterialTextView doctorEmail;
    private MaterialTextView doctorAddress;

    private MaterialTextView doctorBirthday;
    private MaterialTextView doctorAbout;
    private ImageView doctorImage;
    private Uri selectedImageUri;

    //final String userId = FirebaseAuth.getInstance().getCurrentUser().getEmail().toString();
//    FirebaseFirestore db = FirebaseFirestore.getInstance();
//    DocumentReference docRef = db.collection("User").document("" + userId + "");
    private DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_doctor);
        SharedPreferences sharedPrefs = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        final String userId = sharedPrefs.getString("email", "");
        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        UserHelper.init(database);
        DoctorHelper.init(database);
        UploadImageHelper.init(databaseHelper);
        doctorImage = findViewById(R.id.image_profile);
        doctorName = findViewById(R.id.doctor_name);
        doctorSpe = findViewById(R.id.doctor_specialitate);
        doctorPhone = findViewById(R.id.doctor_phone);
        doctorEmail = findViewById(R.id.doctor_email);
        doctorAddress = findViewById(R.id.doctor_address);
        doctorAbout = findViewById(R.id.doctor_about);
        String imageUri = sharedPrefs.getString("profile_image", null);
        if (imageUri != null) {
            selectedImageUri = Uri.parse(imageUri);
            Glide.with(this)
                    .load(selectedImageUri)
                    .into(doctorImage);
        }
       AlertDialog dialog = new SpotsDialog.Builder().setContext(this).setCancelable(true).build();
       dialog.show();



//        final String imageUri = sharedPrefs.getString("poza", "");
//
//        if (!TextUtils.isEmpty(imageUri)) {
//            File imgFile = new File(imageUri);
//            if (imgFile.exists()) {
//                Bitmap myBitmap = BitmapFactory.decodeFile(imgFile.getAbsolutePath());
//                doctorImage.setImageBitmap(myBitmap);
//            } else {
//                doctorImage.setImageResource(R.drawable.index);
//            }
//        } else {
//            doctorImage.setImageResource(R.drawable.index);
//        }



//        final String imageUri = sharedPrefs.getString("poza", "");
//
//        if (!TextUtils.isEmpty(imageUri)) {
//            Picasso.with(this)
//                    .load(Uri.parse(imageUri))
//                    .fit()
//                    .centerCrop()
//                    .into(doctorImage, new Callback() {
//                        @Override
//                        public void onSuccess() {
//                            // Image loaded successfully, do nothing
//                        }
//
//                        @Override
//                        public void onError() {
//                            // Error occurred while loading image, show default image instead
//                            doctorImage.setImageResource(R.drawable.index);
//                        }
//                    });
//        } else {
//            // No image URI found in SharedPreferences, show default image instead
//            doctorImage.setImageResource(R.drawable.index);
//        }


        Doctor doctor = DoctorHelper.getDoctor(userId);
                if (doctor != null) {

                    doctorName.setText(doctor.getName());
                    doctorSpe.setText(doctor.getSpecialitate());
                    doctorPhone.setText(doctor.getTel());
                    doctorEmail.setText(doctor.getEmail());
                    doctorAddress.setText(doctor.getAdresse());
                }


        // Find the toolbar view inside the activity layout
        Toolbar toolbar = (Toolbar) findViewById(R.id.toolbar);
        // Sets the Toolbar to act as the ActionBar for this Activity window.
        // Make sure the toolbar exists in the activity and is not null
        setSupportActionBar(toolbar);

        getSupportActionBar().setDisplayShowHomeEnabled(true);


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
        Intent intent = new Intent(this, DoctorHomeActivity.class);
        startActivity(intent);
        finish();
    }

    private void startEditActivity() {
        Intent intent = new Intent(this, EditProfileDoctorActivity.class);
        intent.putExtra("CURRENT_NAME", doctorName.getText().toString());
        intent.putExtra("CURRENT_PHONE", doctorPhone.getText().toString());
        intent.putExtra("CURRENT_ADDRESS", doctorAddress.getText().toString());
        startActivity(intent);
        finish();
    }
}
