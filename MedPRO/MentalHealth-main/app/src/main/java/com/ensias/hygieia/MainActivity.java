package com.ensias.hygieia;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.database.sqlite.SQLiteDatabase;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.ensias.hygieia.fireStoreApi.DatabaseHelper;
import com.ensias.hygieia.fireStoreApi.DoctorHelper;
import com.ensias.hygieia.fireStoreApi.PatientHelper;
import com.ensias.hygieia.fireStoreApi.UserHelper;
import com.ensias.hygieia.model.Doctor;
import com.ensias.hygieia.model.MockTokenManager;
import com.ensias.hygieia.model.Patient;
import com.ensias.hygieia.model.User;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.common.SignInButton;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.Task;



import java.nio.charset.StandardCharsets;
import java.security.MessageDigest;
import java.security.NoSuchAlgorithmException;
import java.security.SecureRandom;
import java.util.regex.Pattern;


public class MainActivity extends AppCompatActivity {
    private static int RC_SIGN_IN = 100;
    private static final String TAG = "MainActivity";

    private Button signUpBtn;
    private EditText emailText;
    private EditText passwordText;
    private Button loginBtn;
    private Button creatBtn;
    private EditText secondPass;
    private EditText confirme;
    SignInButton signInButton;
    private MockTokenManager mockTokenManager;

    private DatabaseHelper databaseHelper;


    @Override
    protected void onCreate(Bundle savedInstanceState) {

        databaseHelper = new DatabaseHelper(this);
        SQLiteDatabase database = databaseHelper.getWritableDatabase();
        UserHelper.init(database);
        DoctorHelper.init(database);
        PatientHelper.init(database);

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        confirme = (EditText)findViewById(R.id.editText3);
        confirme.setVisibility(View.INVISIBLE);
        signInButton = findViewById(R.id.sign_in_button);

        TextView textView = (TextView) signInButton.getChildAt(0);
        textView.setText("Or Sign in with Google");

        emailText= (EditText) findViewById(R.id.editText2);
        passwordText= (EditText) findViewById(R.id.editText);
        secondPass= (EditText) findViewById(R.id.editText3);
        signUpBtn =(Button)findViewById(R.id.SignUpBtn);
        loginBtn = (Button)findViewById(R.id.LoginBtn);
        creatBtn = findViewById(R.id.CreateAccount);
        signUpBtn.setVisibility(View.GONE);
        mockTokenManager = new MockTokenManager(this);



        signUpBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email=emailText.getText().toString();
                String password=passwordText.getText().toString();
                String confirmPass = secondPass.getText().toString();
                String salt = generateSalt();
                String hashPassword = hashPassword(password, salt);
                if(!email.isEmpty() && !password.isEmpty() && password.equals(confirmPass) && isValidEmail(email)){
                    User user = new User(hashPassword, salt, email, "-");
                    UserHelper.addUser(user);
                    SharedPreferences shared = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                    SharedPreferences.Editor edit = shared.edit();
                    edit.putString("email", email);
                    edit.apply();
                    Intent k = new Intent(MainActivity.this, FirstSigninActivity.class);
                    startActivity(k);

            }else{
                    Toast.makeText(MainActivity.this, "Trebuie sa completati toate campurile",
                            Toast.LENGTH_SHORT).show();
                    if(!password.equals(confirmPass)){
                        Toast.makeText(MainActivity.this, "Parolele nu corespund",
                                Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });



        loginBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String email = emailText.getText().toString();
                String password = passwordText.getText().toString();

                if (!email.isEmpty() && !password.isEmpty()) {
                    User user = UserHelper.getUser(email);
                    if (user != null) {
                        String hashPassword = hashPassword(password, user.getSalt());
                        if (hashPassword.equals(user.getPassword())) {
                            Log.d(TAG, "signInWithEmail:success");

                            mockTokenManager.saveTokens("mockAccessToken", "mockRefreshToken", user.getType(), user.getEmail());

                            SharedPreferences shared = getSharedPreferences("shared_prefs", Context.MODE_PRIVATE);
                            SharedPreferences.Editor edit = shared.edit();
                            edit.putString("email", email);
                            edit.putString("rol", user.getType());
                            edit.apply();

                            if (user.getType().equals("Pacient")) {
                                Patient patient = PatientHelper.getPatient(email);
                                edit.putString("nume", patient.getName());
                                edit.apply();
                                Intent k = new Intent(MainActivity.this, HomeActivity.class);
                                startActivity(k);
                            } else {
                                Doctor doctor = DoctorHelper.getDoctor(email);
                                edit.putString("nume", doctor.getName());
                                edit.apply();
                                Intent k = new Intent(MainActivity.this, DoctorHomeActivity.class);
                                startActivity(k);
                            }
                        } else {
                            Toast.makeText(MainActivity.this, "Invalid email or password", Toast.LENGTH_SHORT).show();
                        }
                    } else {
                        Log.w(TAG, "signInWithEmail:failure");
                        Toast.makeText(MainActivity.this, "No account found with the provided details", Toast.LENGTH_SHORT).show();
                    }
                } else {
                    Toast.makeText(MainActivity.this, "Please fill in all fields", Toast.LENGTH_SHORT).show();
                }
            }
        });

        creatBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                emailText.setText("");
                passwordText.setText("");
                if (creatBtn.getText().toString().equals("Create Account")){
                    confirme.setVisibility(View.VISIBLE);
                    signUpBtn.setVisibility(View.VISIBLE);
                    loginBtn.setVisibility(View.INVISIBLE);
                    creatBtn.setText("Back to login");
                    signInButton.setVisibility(View.GONE);
                }
                else{
                    confirme.setVisibility(View.INVISIBLE);
                    signUpBtn.setVisibility(View.INVISIBLE);
                    loginBtn.setVisibility(View.VISIBLE);
                    creatBtn.setText("Create Account");
                    signInButton.setVisibility(View.VISIBLE);
                }
            }
        });

    }

    @Override
    protected void onDestroy() {
        super.onDestroy();
        // Close the database connection when the activity is destroyed
            databaseHelper.close();

    }

    @Override
    public void onActivityResult(int requestCode, int resultCode, Intent data) {
        super.onActivityResult(requestCode, resultCode, data);

        // Result returned from launching the Intent from GoogleSignInApi.getSignInIntent(...);
        if (requestCode == RC_SIGN_IN) {
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                // Google Sign In was successful, authenticate with Firebase
                GoogleSignInAccount account = task.getResult(ApiException.class);
            } catch (ApiException e) {
                // Google Sign In failed, update UI appropriately
                Log.w("TAG", "Google sign in failed", e);
                // ...
            }
        }
    }



    public static boolean isValidEmail(String email) {
        String emailRegex = "^[a-zA-Z0-9_+&*-]+(?:\\." +
                "[a-zA-Z0-9_+&*-]+)*@" +
                "(?:[a-zA-Z0-9-]+\\.)+[a-z" +
                "A-Z]{2,7}$";
        Pattern pattern = Pattern.compile(emailRegex);
        return pattern.matcher(email).matches();
    }

    public String hashPassword(String password, String salt) {
        String generatedPassword = null;
        try {
            // Create MessageDigest instance for SHA-512
            MessageDigest md = MessageDigest.getInstance("SHA-512");

            // Add salt bytes to digest
            md.update(salt.getBytes(StandardCharsets.UTF_8));

            // Get the hash's bytes
            byte[] bytes = md.digest(password.getBytes(StandardCharsets.UTF_8));

            // This bytes[] has bytes in decimal format;
            // Convert it to hexadecimal format
            StringBuilder sb = new StringBuilder();
            for(int i=0; i< bytes.length ;i++){
                sb.append(Integer.toString((bytes[i] & 0xff) + 0x100, 16).substring(1));
            }

            // Get complete hashed password in hex format
            generatedPassword = sb.toString();
        } catch (NoSuchAlgorithmException e) {
            e.printStackTrace();
        }
        return generatedPassword;
    }
    public String generateSalt() {
        SecureRandom random = new SecureRandom();
        byte[] saltBytes = new byte[16];
        random.nextBytes(saltBytes);
        return android.util.Base64.encodeToString(saltBytes, android.util.Base64.DEFAULT);
    }
}
