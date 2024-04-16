package com.ensias.hygieia;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import com.bumptech.glide.Glide;
import com.ensias.hygieia.fireStoreApi.DatabaseHelper;
import com.ensias.hygieia.fireStoreApi.DoctorHelper;
import com.ensias.hygieia.fireStoreApi.UploadImageHelper;
import com.ensias.hygieia.fireStoreApi.UserHelper;
import com.ensias.hygieia.model.Doctor;

import com.google.android.material.textfield.TextInputEditText;




import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EditProfileDoctorActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final String TAG = "EditProfileDoctorActivity";
    private ImageView profileImage;
    private ImageButton selectImage;
    private Button updateProfile;
    private TextInputEditText doctorName;
    private TextInputEditText doctorEmail;
    private DatabaseHelper databaseHelper;
    private Uri selectedImageUri;
    private TextInputEditText doctorPhone;
    private TextInputEditText doctorAddress;
//    final String currentDoctorUID = FirebaseAuth.getInstance().getCurrentUser().getEmail().toString();
//    final String doctorID = FirebaseAuth.getInstance().getCurrentUser().getEmail().toString();
    private Uri uriImage;




    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_doctor);
        SharedPreferences sharedPrefs = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
        final String doctorID = sharedPrefs.getString("email", "");
        //doctorRef = FirebaseFirestore.getInstance();
        profileImage = findViewById(R.id.image_profile);
        selectImage = findViewById(R.id.select_image);
        updateProfile = findViewById(R.id.update);
        doctorName = findViewById(R.id.nameText);
        doctorPhone = findViewById(R.id.phoneText);
        //doctorEmail = findViewById(R.id.emailText);
        doctorAddress = findViewById(R.id.addressText);
        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        UserHelper.init(database);
        DoctorHelper.init(database);
        UploadImageHelper.init(databaseHelper);

        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();
            }
        });
        //get the default doctor's informations from ProfileDoctorActivity
        Intent intent = getIntent(); //get the current intent
        String current_name = intent.getStringExtra("CURRENT_NAME");
        String current_phone = intent.getStringExtra("CURRENT_PHONE");
        String current_address = intent.getStringExtra("CURRENT_ADDRESS");

        //Set the default informtions in he text fields
        doctorName.setText(current_name);
        doctorPhone.setText(current_phone);
        doctorAddress.setText(current_address);

        // Load image from SharedPreferences if available
        String imageUri = sharedPrefs.getString("profile_image", null);
        if (imageUri != null) {
            selectedImageUri = Uri.parse(imageUri);
            Glide.with(this)
                    .load(selectedImageUri)
                    .into(profileImage);
        }
        selectImage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openFileChooser();

            }
        });

        updateProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String updateAddress = doctorAddress.getText().toString();
                String updateName = doctorName.getText().toString();
                //String updateEmail = doctorEmail.getText().toString();
                String updatePhone = doctorPhone.getText().toString();
                if (selectedImageUri != null) {
                    SharedPreferences shared = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = shared.edit();
                    edit.putString("profile_image", selectedImageUri.toString());
                    edit.apply();
                }

                UploadImageHelper.updateImage(String.valueOf(selectedImageUri), doctorID);

                Doctor doctor = new Doctor(updateName, updateAddress, updatePhone);
                DoctorHelper.updateDoctor(doctor);
                Toast.makeText(EditProfileDoctorActivity.this, "Infos Updated", Toast.LENGTH_LONG).show();
            }
//               UploadImageHelper.updateImage(String.valueOf(selectedImageUri), doctorID);
//
//                //uploadProfileImage(doctorID);
//                Doctor doctor = new Doctor(updateName, updateAddress, updatePhone);
//                DoctorHelper.updateDoctor(doctor);
//                Toast.makeText(EditProfileDoctorActivity.this, "Infos Updated", Toast.LENGTH_LONG).show();
//
//            }
        });
    }

    /* Used to choose a file */
    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }


    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
            selectedImageUri = data.getData();
            SharedPreferences shared = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
            SharedPreferences.Editor edit = shared.edit();
            edit.putString("poza", selectedImageUri.toString());
            edit.apply();
            Glide.with(this)
                    .load(selectedImageUri)
                    .into(profileImage);
        }
    }

    /* used to get the data back */
//    @Override
//    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
//        super.onActivityResult(requestCode, resultCode, data);
//
//        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK && data != null) {
//            selectedImageUri = data.getData();
//            SharedPreferences shared = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
//            SharedPreferences.Editor edit = shared.edit();
//            edit.putString("poza", selectedImageUri.toString());
//            edit.apply();
//            Picasso.with(this)
//                    .load(selectedImageUri)
//                    .placeholder(R.drawable.doctor)
//                    .fit()
//                    .centerCrop()
//                    .into(profileImage);
//        }
//    }

    /* Retrieve the extension of the file to upload */
    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

}