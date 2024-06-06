package com.ensias.hygieia;

import com.ensias.hygieia.model.DrugModel;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;

public interface ApiService {
    @GET("medicine/short")
    Call<List<DrugModel>> getDrugs();

}
