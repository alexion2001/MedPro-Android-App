package com.ensias.hygieia;

import android.os.Bundle;
import android.util.Log;

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
    private DrugAdapter adapter;

    @Override
    protected void onCreate(@Nullable Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_drugs);

        // Initialize Retrofit
        Retrofit retrofit = new Retrofit.Builder()
                .baseUrl("https://www.apimedic.ro/api/v1/")
                .addConverterFactory(GsonConverterFactory.create())
                .build();

        // Create ApiService instance
        apiService = retrofit.create(ApiService.class);

        // Initialize RecyclerView and adapter
        rv = findViewById(R.id.recycler_view);
        adapter = new DrugAdapter(new ArrayList<>(), this);
        rv.setLayoutManager(new LinearLayoutManager(this));
        rv.setAdapter(adapter);


        // Fetch data from API
        fetchData();
    }

    private void fetchData() {
        // Make network call
        Call<List<DrugModel>> call = apiService.getDrugs();
        call.enqueue(new Callback<List<DrugModel>>() {
            @Override
            public void onResponse(Call<List<DrugModel>> call, Response<List<DrugModel>> response) {
                if (response.isSuccessful()) {
                    List<DrugModel> drugModels = response.body();
                    if (drugModels != null) {
                        // Update RecyclerView data
                        adapter.setData(drugModels);
                    }
                } else {
                    Log.e("DrugsActivity", "Error: " + response.code());
                }
            }

            @Override
            public void onFailure(Call<List<DrugModel>> call, Throwable t) {
                Log.e("DrugsActivity", "Failed to fetch data: " + t.getMessage());
            }
        });
    }
}
