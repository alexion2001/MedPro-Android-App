package com.ensias.hygieia;

import android.os.Bundle;

import androidx.fragment.app.Fragment;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;

import com.ensias.hygieia.adapter.ConsultationAdapter;


public class ConsultationFragmentPage extends Fragment {


    private ConsultationAdapter adapter;
    View result;

    public ConsultationFragmentPage() {
        // Required empty public constructor
    }

    public static ConsultationFragmentPage newInstance() {
        ConsultationFragmentPage fragment = new ConsultationFragmentPage();
        return fragment;
    }


    @Override
    public View onCreateView(LayoutInflater inflater, ViewGroup container,
                             Bundle savedInstanceState) {
        // Inflate the layout for this fragment
        result = inflater.inflate(R.layout.fragment_consultation_page, container, false);
        setUpRecyclerView();
        return result;
    }

    private void setUpRecyclerView() {

        String email_id = getActivity().getIntent().getExtras().getString("patient_email");


        RecyclerView recyclerView = result.findViewById(R.id.conslutationRecycleView);
        recyclerView.setHasFixedSize(true);
        recyclerView.setLayoutManager(new LinearLayoutManager(this.getContext()));
    }

    @Override
    public void onStart() {
        super.onStart();
    }

    @Override
    public void onStop() {
        super.onStop();
    }
}