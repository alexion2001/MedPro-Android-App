package com.ensias.hygieia;

import android.annotation.SuppressLint;
import android.content.ContentResolver;
import android.content.Intent;
import android.net.Uri;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.webkit.MimeTypeMap;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;

import com.google.android.material.textfield.TextInputEditText;

import com.squareup.picasso.Picasso;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

public class EditProfilePatientActivity extends AppCompatActivity {
    private static final int PICK_IMAGE_REQUEST = 1;
    private static final String TAG = "EditProfileDoctorActivity";
    private ImageView profileImage;
    private ImageButton selectImage;
    private Button updateProfile;
    private TextInputEditText doctorName;
    private TextInputEditText doctorEmail;
    private TextInputEditText doctorPhone;
    private TextInputEditText doctorAddress;
    private Uri uriImage;



    @Override
    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_edit_profile_patient);
        profileImage = findViewById(R.id.image_profile);
        selectImage = findViewById(R.id.select_image);
        updateProfile = findViewById(R.id.update);
        doctorName = findViewById(R.id.nameText);
        doctorPhone = findViewById(R.id.phoneText);
        ///doctorEmail = findViewById(R.id.emailText);
        doctorAddress = findViewById(R.id.addressText);


        //get the default doctor's informations from ProfileDoctorActivity
        Intent intent = getIntent(); //get the current intent
        String current_name = intent.getStringExtra("CURRENT_NAME");
        String current_phone = intent.getStringExtra("CURRENT_PHONE");
        String current_address = intent.getStringExtra("CURRENT_ADDRESS");

        //Set the default informtions in he text fields
        doctorName.setText(current_name);
        doctorPhone.setText(current_phone);
        doctorAddress.setText(current_address);
        /*
        currentUserImg = FirebaseDatabase.getInstance().getReference("DoctorProfile").child("1590965871687");
        Glide.with(this)
                .load(currentUserImg)
                .into(profileImage);
                   */




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
                uploadProfileImage();
                updateDoctorInfos(updateName, updateAddress, updatePhone);
            }
        });
    }


    /* Update the doctor info in the database */
    private void updateDoctorInfos(String name, String address, String phone) {

    }

    /* Used to choose a file */
    private void openFileChooser() {
        Intent intent = new Intent();
        intent.setType("image/*");
        intent.setAction(Intent.ACTION_GET_CONTENT);
        startActivityForResult(intent, PICK_IMAGE_REQUEST);
    }

    /* used to get the data back */
    @Override
    protected void onActivityResult(int requestCode, int resultCode, @Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        if (requestCode == PICK_IMAGE_REQUEST && resultCode == RESULT_OK
                && data != null && data.getData() != null) {
            uriImage = data.getData();
            Picasso.with(this).load(uriImage).into(profileImage);
        }
    }

    /* Retrieve the extension of the file to upload */
    private String getFileExtension(Uri uri) {
        ContentResolver cR = getContentResolver();
        MimeTypeMap mime = MimeTypeMap.getSingleton();
        return mime.getExtensionFromMimeType(cR.getType(uri));
    }

    /* Used to upload the doctor image in the DataBase */
    private void uploadProfileImage() {
        /* check if the image is not null */
          }

                    /*
                    if (uriImage != null) {
                        StorageReference fileReference = pStorageRef.child(System.currentTimeMillis()
                                + "." + getFileExtension(uriImage));
                        fileReference.putFile(uriImage)
                                .addOnSuccessListener(new OnSuccessListener<UploadTask.TaskSnapshot>() {
                                    @Override
                                    public void onSuccess(UploadTask.TaskSnapshot taskSnapshot) {
                                        Toast.makeText(EditProfileDoctorActivity.this, "Update Successful", Toast.LENGTH_SHORT)
                                                .show();
                                        //Upload the image to the database
                                        UploadImage uploadImage = new UploadImage(currentDoctorUID, taskSnapshot.getDownloadUrl().toString());
                                    }
                                })
                                .addOnFailureListener(new OnFailureListener() {
                                    @Override
                                    public void onFailure(@NonNull Exception e) {
                                        Toast.makeText(EditProfileDoctorActivity.this, e.getLocalizedMessage(), Toast.LENGTH_SHORT)
                                                .show();
                                    }
                                });
                    }*/



        }


