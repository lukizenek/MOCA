package com.example.moca.Adapters;

import android.view.View;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moca.R;

public class RandomRecipeViewHolder extends RecyclerView.ViewHolder {

    CardView random_list_container;
    TextView texView_title, textView_servings, textView_likes, textView_prepTime;
    ImageView image_food;

    public RandomRecipeViewHolder(@NonNull View itemView) {
        super(itemView);
        random_list_container = itemView.findViewById(R.id.random_list_container);
        texView_title = itemView.findViewById(R.id.texView_title);
        textView_servings = itemView.findViewById(R.id.textView_servings);
        textView_likes = itemView.findViewById(R.id.textView_likes);
        textView_prepTime = itemView.findViewById(R.id.textView_prepTime);
        image_food = itemView.findViewById(R.id.image_food);
    }
}
