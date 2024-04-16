package com.ensias.hygieia;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.LinearLayout;




public class AppointementActivity extends AppCompatActivity {

    private LinearLayout lis;
    //final List<String> fruits_list = new ArrayList<String>(Arrays.asList(fruits));

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_appointement);
        lis = findViewById(R.id.listDate);
        String patient_email = getIntent().getStringExtra("key1");
        String day = getIntent().getStringExtra("key2");


        LinearLayout.LayoutParams layoutParams = new LinearLayout.LayoutParams(140, 398);
        layoutParams.setMargins(200, 0, 300, 0);


    }


}