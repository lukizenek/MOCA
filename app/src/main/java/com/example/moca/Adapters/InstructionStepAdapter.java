package com.example.moca.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moca.Models.Step;
import com.example.moca.R;

import java.util.List;

public class InstructionStepAdapter extends RecyclerView.Adapter<InstructionStepViewHolder> {

    Context context;
    List<Step> list;

    public InstructionStepAdapter(Context context, List<Step> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InstructionStepViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionStepViewHolder(LayoutInflater.from(context).inflate(R.layout.liste_instructions_steps, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionStepViewHolder holder, int position) {

        holder.instructions_step_number.setText(String.valueOf(list.get(position).number));
        holder.instructions_step_title.setText(list.get(position).step);

        holder.recycler_instructions_ingredients.setHasFixedSize(true);
        holder.recycler_instructions_ingredients.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        InstructionIngredientsAdapter instructionIngredientsAdapter = new InstructionIngredientsAdapter(context, list.get(position).ingredients);
        holder.recycler_instructions_ingredients.setAdapter(instructionIngredientsAdapter);

        holder.recycler_instructions_eq.setHasFixedSize(true);
        holder.recycler_instructions_eq.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.HORIZONTAL, false));
        InstructionEqAdapter instructionEqAdapter = new InstructionEqAdapter(context, list.get(position).equipment);
        holder.recycler_instructions_eq.setAdapter(instructionEqAdapter);
    }


    @Override
    public int getItemCount() {
        return list.size();
    }
}
class InstructionStepViewHolder extends RecyclerView.ViewHolder {

    TextView instructions_step_number, instructions_step_title;
    RecyclerView recycler_instructions_eq, recycler_instructions_ingredients;
    public InstructionStepViewHolder(@NonNull View itemView) {
        super(itemView);
        instructions_step_number = itemView.findViewById(R.id.instructions_step_number);
        instructions_step_title = itemView.findViewById(R.id.instructions_step_title);
        recycler_instructions_eq = itemView.findViewById(R.id.recycler_instructions_eq);
        recycler_instructions_ingredients = itemView.findViewById(R.id.recycler_instructions_ingredients);
    }
}
