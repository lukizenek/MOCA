package com.example.moca.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moca.Models.Ingredient;
import com.example.moca.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class InstructionIngredientsAdapter extends RecyclerView.Adapter<InstructionIngredientsViewHolder>{

    Context context;
    List<Ingredient> list;

    public InstructionIngredientsAdapter(Context context, List<Ingredient> list) {
        this.context = context;
        this.list = list;
    }

    @NonNull
    @Override
    public InstructionIngredientsViewHolder  onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new InstructionIngredientsViewHolder(LayoutInflater.from(context).inflate(R.layout.list_instructions_step_items, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull InstructionIngredientsViewHolder  holder, int position) {

        holder.instructions_item_name.setText(list.get(position).name);
        holder.instructions_item_name.setSelected(true);
        Picasso.get().load("https://spoonacular.com/cdn/ingredients_100x100/" + list.get(position).image).into(holder.image_instruction_items);
    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class InstructionIngredientsViewHolder extends RecyclerView.ViewHolder {

    ImageView image_instruction_items;
    TextView instructions_item_name;
    public InstructionIngredientsViewHolder(@NonNull View itemView) {
        super(itemView);
        instructions_item_name = itemView.findViewById(R.id.instructions_item_name);
        image_instruction_items = itemView.findViewById(R.id.image_instruction_items);
    }
}