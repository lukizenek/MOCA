package com.example.moca.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moca.Models.Equipment;
import com.example.moca.Models.Ingredient;
import com.example.moca.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class InstructionEqAdapter extends RecyclerView.Adapter<InstructionEqViewHolder>{

    Context context;
    List<Equipment> list;

    public InstructionEqAdapter(Context context, List<Equipment> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InstructionEqViewHolder  onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionEqViewHolder(LayoutInflater.from(context).inflate(R.layout.list_instructions_step_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionEqViewHolder  holder, int position) {

        holder.instructions_item_name.setText(list.get(position).name);
        holder.instructions_item_name.setSelected(true);
        Picasso.get().load("https://spoonacular.com/cdn/equipment_100x100/" + list.get(position).image).into(holder.image_instruction_items);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}

class InstructionEqViewHolder extends RecyclerView.ViewHolder {

    ImageView image_instruction_items;
    TextView instructions_item_name;
    public InstructionEqViewHolder(@NonNull View itemView) {
        super(itemView);
        instructions_item_name = itemView.findViewById(R.id.instructions_item_name);
        image_instruction_items = itemView.findViewById(R.id.image_instruction_items);
    }
}