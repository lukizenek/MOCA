package com.example.moca.Adapters;

import android.content.Context;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.RecyclerView;

import com.example.moca.Listeners.RecipeListener;
import com.example.moca.Models.SimilarRecipeResponse;
import com.example.moca.R;
import com.squareup.picasso.Picasso;

import java.util.List;

public class SimilarRecipeAdapter extends RecyclerView.Adapter<SimilarRecipeViewHolder>{

    Context context;
    List<SimilarRecipeResponse> list;
    RecipeListener listener;


    public SimilarRecipeAdapter(Context context, List<SimilarRecipeResponse> list, RecipeListener listener) {
        this.context = context;
        this.list = list;
        this.listener = listener;
    }

    @NonNull
    @Override
    public SimilarRecipeViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        return new SimilarRecipeViewHolder(LayoutInflater.from(context).inflate(R.layout.list_similar_recipe, parent, false));
    }

    @Override
    public void onBindViewHolder(@NonNull SimilarRecipeViewHolder holder, int position) {
        holder.similar_title.setText(list.get(position).title);
        holder.similar_title.setSelected(true);
        holder.similar_serving.setText(list.get(position).servings+ " Persons");
        Picasso.get().load("https://spoonacular.com/recipeImages/" + list.get(position).id + "-556x370." + list.get(position).imageType).into(holder.image_similar);

        holder.similar_card.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                listener.onRecipeClick(String.valueOf(list.get(holder.getAdapterPosition()).id));
            }
        });

    }

    @Override
    public int getItemCount() {
        return list.size();
    }
}
class SimilarRecipeViewHolder extends RecyclerView.ViewHolder {
    CardView similar_card;
    TextView similar_title, similar_serving;
    ImageView image_similar;

    public SimilarRecipeViewHolder(@NonNull View itemView) {
        super(itemView);
        similar_card = itemView.findViewById(R.id.similar_card);
        similar_title = itemView.findViewById(R.id.similar_title);
        similar_serving = itemView.findViewById(R.id.similar_serving);
        image_similar = itemView.findViewById(R.id.image_similar);
    }
}