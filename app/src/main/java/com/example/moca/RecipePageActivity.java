package com.example.moca;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moca.Adapters.IngredientsAdapter;
import com.example.moca.Listeners.RecipeDetailsListener;
import com.example.moca.Models.RecipeDetailsResponse;
import com.squareup.picasso.Picasso;

public class RecipePageActivity extends AppCompatActivity {

    int id;
    TextView text_meal_name, meal_source, text_meal_details;
    ImageView image_meal_details;
    RecyclerView recycle_meal_ingredients;
    RequestManager manager;
    ProgressDialog dialog;
    IngredientsAdapter ingredientsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_page);
        findViews();

        id = Integer.parseInt(getIntent().getStringExtra("id"));
        manager = new RequestManager(this);
        manager.getRecipeDetails(recipeDetailsListener, id);
        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading Details");
        dialog.show();
    }

    private void findViews() {
        text_meal_name = findViewById(R.id.text_meal_name);
        meal_source = findViewById(R.id.meal_source);
        text_meal_details = findViewById(R.id.text_meal_details);
        image_meal_details = findViewById(R.id.image_meal_details);
        recycle_meal_ingredients = findViewById(R.id.recycle_meal_ingredients);
    }
    private final RecipeDetailsListener recipeDetailsListener = new RecipeDetailsListener() {
        @Override
        public void didError(String message) {
            Toast.makeText(RecipePageActivity.this, message, Toast.LENGTH_SHORT).show();

        }

        @Override
        public void didFetch(RecipeDetailsResponse body, String message) {
            dialog.dismiss();
            text_meal_name.setText(body.title);
            meal_source.setText(body.sourceName);
            text_meal_details.setText(body.summary);
            Picasso.get().load(body.image).into(image_meal_details);

            recycle_meal_ingredients.setHasFixedSize(true);
            recycle_meal_ingredients.setLayoutManager(new GridLayoutManager(RecipePageActivity.this, 2));
            ingredientsAdapter = new IngredientsAdapter(RecipePageActivity.this, body.extendedIngredients);
            recycle_meal_ingredients.setAdapter(ingredientsAdapter);


        }
    };
}