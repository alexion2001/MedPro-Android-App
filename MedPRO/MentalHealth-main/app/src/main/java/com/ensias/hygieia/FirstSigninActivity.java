package com.ensias.hygieia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.AdapterView;
import android.widget.ArrayAdapter;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Spinner;

import com.ensias.hygieia.fireStoreApi.DatabaseHelper;
import com.ensias.hygieia.fireStoreApi.DoctorHelper;
import com.ensias.hygieia.fireStoreApi.PatientHelper;
import com.ensias.hygieia.fireStoreApi.UploadImageHelper;
import com.ensias.hygieia.fireStoreApi.UserHelper;
import com.ensias.hygieia.model.Doctor;
import com.ensias.hygieia.model.Patient;
import com.ensias.hygieia.model.UploadImage;

import static android.widget.AdapterView.*;

public class FirstSigninActivity extends AppCompatActivity {
    private static final String TAG = "FirstSigninActivity";
    private EditText fullName;
    private EditText birthday;
    private EditText teL;
    private Button btn;
    private DatabaseHelper databaseHelper;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_first_signin);
        btn = (Button) findViewById(R.id.confirmeBtn);
        fullName = (EditText) findViewById(R.id.firstSignFullName);
        birthday = (EditText) findViewById(R.id.firstSignBirthDay);
        teL = (EditText) findViewById(R.id.firstSignTel);
        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        DoctorHelper.init(database);
        UploadImageHelper.init(databaseHelper);
        final Spinner spinner = (Spinner) findViewById(R.id.spinner);
        ArrayAdapter<CharSequence> adapter = ArrayAdapter.createFromResource(this,
                R.array.planets_array, android.R.layout.simple_spinner_item);
        adapter.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        spinner.setAdapter(adapter);

        final Spinner specialitateList = (Spinner) findViewById(R.id.specialitate_spinner);
        ArrayAdapter<CharSequence> adapterSpecialitateList = ArrayAdapter.createFromResource(this,
                R.array.specialitate_spinner, android.R.layout.simple_spinner_item);
        adapterSpecialitateList.setDropDownViewResource(android.R.layout.simple_spinner_dropdown_item);
        specialitateList.setAdapter(adapterSpecialitateList);
        String newAccountType = spinner.getSelectedItem().toString();

        spinner.setOnItemSelectedListener(new OnItemSelectedListener() {
            @Override
            public void onItemSelected(AdapterView<?> parent, View view, int position, long id) {
                String selected = spinner.getSelectedItem().toString();
                Log.e(TAG, "onItemSelected:" + selected);
                if (selected.equals("Doctor")) {
                    specialitateList.setVisibility(View.VISIBLE);
                } else {
                    specialitateList.setVisibility(View.GONE);
                }
            }

            @Override
            public void onNothingSelected(AdapterView<?> adapterView) {
                specialitateList.setVisibility(View.GONE);
            }
        });

        btn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {

                String fullname, birthDay, tel, type, specialitate;
                fullname = fullName.getText().toString();
                birthDay = birthday.getText().toString();
                tel = teL.getText().toString();
                type = spinner.getSelectedItem().toString();
                specialitate = specialitateList.getSelectedItem().toString();
                SharedPreferences sharedPrefs = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                String email = sharedPrefs.getString("email", "");
                String defaultImageUri = "android.resource://com.ensias.hygieia/drawable/index.png";
                UserHelper.updateUserType(type, email);
                UploadImage defaultImage = new UploadImage(email, defaultImageUri);
                UploadImageHelper.addImage(defaultImage);
                if (type.equals("Pacient")) {
                    Patient user = new Patient( email, fullname,  tel, birthDay);
                    PatientHelper.addPatient(user);
                   //UserHelper.addUser(fullname, "address", tel, type, birthDay);
                    System.out.println("Pacientul " + fullname + " adaugat cu succes");

                } else {
                    Doctor user = new Doctor(fullname, "test", tel, email, birthDay, specialitate);
                    DoctorHelper.addDoctor(user);
                    //UserHelper.addUser(fullname, "address", tel, type, birthDay);

                }
                Intent k = new Intent(FirstSigninActivity.this, MainActivity.class);
                startActivity(k);
            }


        });
    }

}
