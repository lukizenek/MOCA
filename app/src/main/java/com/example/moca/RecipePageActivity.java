package com.example.moca;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.GridLayoutManager;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.app.ProgressDialog;
import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;
import android.widget.TextView;
import android.widget.Toast;

import com.example.moca.Adapters.IngredientsAdapter;
import com.example.moca.Adapters.InstructionsAdapter;
import com.example.moca.Adapters.SimilarRecipeAdapter;
import com.example.moca.Listeners.InstructionsListener;
import com.example.moca.Listeners.RecipeDetailsListener;
import com.example.moca.Listeners.RecipeListener;
import com.example.moca.Listeners.SimilarRecipesListener;
import com.example.moca.Models.InstructionsResponse;
import com.example.moca.Models.RecipeDetailsResponse;
import com.example.moca.Models.SimilarRecipeResponse;
import com.squareup.picasso.Picasso;

import java.util.List;

public class RecipePageActivity extends AppCompatActivity {

    int id;
    TextView text_meal_name, meal_source;
    ImageView image_meal_details;
    RecyclerView recycle_meal_ingredients, recycler_meal_summary, recycler_meal_instructions;
    RequestManager manager;
    ProgressDialog dialog;
    IngredientsAdapter ingredientsAdapter;
    SimilarRecipeAdapter similarRecipeAdapter;
    InstructionsAdapter instructionsAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_recipe_page);
        findViews();

        id = Integer.parseInt(getIntent().getStringExtra("id"));
        manager = new RequestManager(this);
        manager.getRecipeDetails(recipeDetailsListener, id);
        manager.getSimilarRecipes(similarRecipesListener, id);
        manager.getInstructions(instructionsListener, id);
        dialog = new ProgressDialog(this);
        dialog.setTitle("Loading Details");
        dialog.show();
    }

    private void findViews() {
        text_meal_name = findViewById(R.id.text_meal_name);
        meal_source = findViewById(R.id.meal_source);
        image_meal_details = findViewById(R.id.image_meal_details);
        recycle_meal_ingredients = findViewById(R.id.recycle_meal_ingredients);
        recycler_meal_summary = findViewById(R.id.recycler_meal_summary);
        recycler_meal_instructions = findViewById(R.id.recycler_meal_instructions);
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
            Picasso.get().load(body.image).into(image_meal_details);

            recycle_meal_ingredients.setHasFixedSize(true);
            recycle_meal_ingredients.setLayoutManager(new GridLayoutManager(RecipePageActivity.this, 2));
            ingredientsAdapter = new IngredientsAdapter(RecipePageActivity.this, body.extendedIngredients);
            recycle_meal_ingredients.setAdapter(ingredientsAdapter);


        }
    };

    private final SimilarRecipesListener similarRecipesListener = new SimilarRecipesListener() {
        @Override
        public void didFetch(List<SimilarRecipeResponse> response, String message) {
            recycler_meal_summary.setHasFixedSize(true);
            recycler_meal_summary.setLayoutManager(new LinearLayoutManager(RecipePageActivity.this, LinearLayoutManager.HORIZONTAL, false));
            similarRecipeAdapter = new SimilarRecipeAdapter(RecipePageActivity.this, response, recipeListener);
            recycler_meal_summary.setAdapter(similarRecipeAdapter);

        }

        @Override
        public void didError(String message) {
            Toast.makeText(RecipePageActivity.this, message, Toast.LENGTH_SHORT).show();

        }
    };

    private final RecipeListener recipeListener = new RecipeListener() {
        @Override
        public void onRecipeClick(String id) {
            startActivity(new Intent(RecipePageActivity.this, RecipePageActivity.class)
            .putExtra("id", id));
        }
    };

    private final InstructionsListener instructionsListener = new InstructionsListener() {
        @Override
        public void didFetch(List<InstructionsResponse> resposnse, String message) {

            recycler_meal_instructions.setHasFixedSize(true);
            recycler_meal_instructions.setLayoutManager(new LinearLayoutManager(RecipePageActivity.this, LinearLayoutManager.VERTICAL, false));
            instructionsAdapter = new InstructionsAdapter(RecipePageActivity.this, resposnse);
            recycler_meal_instructions.setAdapter(instructionsAdapter);
        }

        @Override
        public void didError(String message) {

        }
    };
}