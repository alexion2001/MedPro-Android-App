package com.ensias.hygieia;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ProgressBar;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.ensias.hygieia.adapter.DrugAdapter;
import com.ensias.hygieia.model.DrugModel;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;
import retrofit2.Retrofit;
import retrofit2.converter.gson.GsonConverterFactory;

public class DrugsActivity extends AppCompatActivity {

    // Retrofit instance
    private ApiService apiService;

    // RecyclerView
    private RecyclerView rv;
    private ImageButton closeBtn;
    private DrugAdapter adapter;
    private ProgressBar spinner;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_drugs);

        closeBtn = findViewById(R.id.close);
        closeBtn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent k = new Intent(DrugsActivity.this, HomeActivity.class);
                startActivity(k);
            }
        });
        // Initialize spinner
       spinner = findViewById(R.id.spinner);

        // Initialize Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.apimedic.ro/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create ApiService instance
        apiService = retrofit.create(ApiService.class);
        List<DrugModel> drugList = new ArrayList<>();

        // Add drugs to the list
        drugList.add(new DrugModel("Acid nicotinic, comprimate", "circulatory disorders, cerebral atherosclerosis, ramolisment, hypertensives, retinal circulatory disorders, spasms, embolism, central retinal vein, central retinal vein thrombosis, angiospastic retinas, ent sphere, meniere's syndrome, migraines, peripheral, peripheral circulatory disorders, arteritis, raynaud syndrome, frostbite, hyperlipoproteinemias, v."));
        drugList.add(new DrugModel("Accupro 5, 10, 20 comprimate filmate", "hypertension, heart, heart failure, diuretics, digitalis"));

        fetchData(new DataCallback() {
            @Override
            public void onDataReceived(List<DrugModel> drugModels) {
                // Hide spinner
                spinner.setVisibility(View.GONE);
                // Initialize RecyclerView and adapter
                rv = findViewById(R.id.recycler_view);
                adapter = new DrugAdapter(drugModels, DrugsActivity.this);
                rv.setLayoutManager(new LinearLayoutManager(DrugsActivity.this));
                rv.setAdapter(adapter);
                // Print the response data
                Log.d("DrugsActivity", "Response data: " + drugModels.toString());
            }

            @Override
            public void onFailure(String errorMessage) {
                Log.e("DrugsActivity", "Failed to fetch data: " + errorMessage);
            }
        });






    }

    private void fetchData(final DataCallback callback) {
        // Make network call
        Call<List<DrugModel>> call = apiService.getDrugs();
        call.enqueue(new Callback<List<DrugModel>>() {
            @Override
            public void onResponse(Call<List<DrugModel>> call, Response<List<DrugModel>> response) {
                if (response.isSuccessful()) {
                    List<DrugModel> drugModels = response.body();
                    if (drugModels != null) {
                        callback.onDataReceived(drugModels);
                    } else {
                        callback.onFailure("Response body is null");
                    }
                } else {
                    callback.onFailure("Response is not successful. Code: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<DrugModel>> call, Throwable t) {
                callback.onFailure(t.getMessage());
            }
        });
    }

    // Interface to handle response
    interface DataCallback {
        void onDataReceived(List<DrugModel> drugModels);
        void onFailure(String errorMessage);
    }
}
