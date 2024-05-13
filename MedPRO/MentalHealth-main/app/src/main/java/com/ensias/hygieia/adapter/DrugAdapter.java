package com.ensias.hygieia.adapter;


import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.constraintlayout.widget.ConstraintLayout;
import androidx.recyclerview.widget.RecyclerView;

import com.ensias.hygieia.DrugsActivity;
import com.ensias.hygieia.R;
import com.ensias.hygieia.model.DrugModel;

import java.util.List;

public class DrugAdapter extends RecyclerView.Adapter<DrugAdapter.TaskViewHolder> {

    private List<DrugModel> localDataSet;


    public DrugAdapter(List<DrugModel> localDataSet, DrugsActivity drugsFragment) {
        this.localDataSet = localDataSet;
    }
    public void setData(List<DrugModel> newData) {
        this.localDataSet = newData;
        Log.d("Drugs Adapter", "Response data adapter: " + newData);
      //  notifyDataSetChanged();
    }

    @NonNull
    @Override
    public TaskViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.layout_medicine_item, parent, false);
        return new TaskViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull TaskViewHolder holder, int position) {
        holder.bind(localDataSet.get(position));
    }

    @Override
    public int getItemCount() {
        return localDataSet.size();
    }

    public static class TaskViewHolder extends RecyclerView.ViewHolder {


        private final TextView taskTextNume;
        private final TextView taskTextKey;

        private final ConstraintLayout layout;

        public TaskViewHolder(@NonNull View itemView) {
            super(itemView);

            taskTextNume = itemView.findViewById(R.id.drugName);
            taskTextKey = itemView.findViewById(R.id.drugKey);


            layout = itemView.findViewById(R.id.container);
        }


        public void bind(DrugModel item) {
            Log.d("DrugsActivity", "Response data hereee: " + item.getKeywords());

            taskTextKey.setText(item.getKeywords());
            taskTextNume.setText(item.getName());


        }

    }
}