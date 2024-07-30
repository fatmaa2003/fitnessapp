package com.example.myapplication.adapter;

import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.myapplication.Exercise;
import com.example.myapplication.R;

import java.util.List;

public class ExerciseAdapter extends RecyclerView.Adapter<ExerciseAdapter.ExerciseViewHolder> {
    private List<Exercise> exerciseList;

    public ExerciseAdapter(List<Exercise> exerciseList) {
        this.exerciseList = exerciseList;
    }

    @NonNull
    @Override
    public ExerciseViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view = LayoutInflater.from(parent.getContext()).inflate(R.layout.item_exercise, parent, false);
        return new ExerciseViewHolder(view);
    }

    @Override
    public void onBindViewHolder(@NonNull ExerciseViewHolder holder, int position) {
        Exercise exercise = exerciseList.get(position);
        holder.nameTextView.setText(exercise.getName());
        holder.typeTextView.setText(exercise.getType());
        holder.muscleTextView.setText(exercise.getMuscle());
        holder.equipmentTextView.setText(exercise.getEquipment());
        holder.difficultyTextView.setText(exercise.getDifficulty());
        holder.instructionsTextView.setText(exercise.getInstructions());
    }

    @Override
    public int getItemCount() {
        return exerciseList.size();
    }

    static class ExerciseViewHolder extends RecyclerView.ViewHolder {
        TextView nameTextView, typeTextView, muscleTextView, equipmentTextView, difficultyTextView, instructionsTextView;

        public ExerciseViewHolder(@NonNull View itemView) {
            super(itemView);
            nameTextView = itemView.findViewById(R.id.nameTextView);
            typeTextView = itemView.findViewById(R.id.typeTextView);
            muscleTextView = itemView.findViewById(R.id.muscleTextView);
            equipmentTextView = itemView.findViewById(R.id.equipmentTextView);
            difficultyTextView = itemView.findViewById(R.id.difficultyTextView);
            instructionsTextView = itemView.findViewById(R.id.instructionsTextView);
        }
    }
}
