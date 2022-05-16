package com.example.moca.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moca.Models.InstructionsResponse;
import com.example.moca.R;

import java.util.List;

public class InstructionsAdapter extends RecyclerView.Adapter<InstructionsViewHolder>{

    Context context;
    List<InstructionsResponse> list;

    public InstructionsAdapter(Context context, List<InstructionsResponse> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InstructionsViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionsViewHolder(LayoutInflater.from(context).inflate(R.layout.list_instructions, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionsViewHolder holder, int position) {

        holder.instruction_name.setText(list.get(position).name);
        holder.instruction_steps.setHasFixedSize(true);
        holder.instruction_steps.setLayoutManager(new LinearLayoutManager(context, LinearLayoutManager.VERTICAL, false));
        InstructionStepAdapter instructionStepAdapter = new InstructionStepAdapter(context, list.get(position).steps);
        holder.instruction_steps.setAdapter(instructionStepAdapter);

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class InstructionsViewHolder extends RecyclerView.ViewHolder {

    TextView instruction_name;
    RecyclerView instruction_steps;

    public InstructionsViewHolder(@NonNull View itemView) {
        super(itemView);
        instruction_name = itemView.findViewById(R.id.instruction_name);
        instruction_steps = itemView.findViewById(R.id.recycler_instruction_steps);
    }
}
