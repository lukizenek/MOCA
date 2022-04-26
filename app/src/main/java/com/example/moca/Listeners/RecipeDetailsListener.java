package com.example.moca.Listeners;

import com.example.moca.Models.RecipeDetailsResponse;

public interface RecipeDetailsListener {
    void didError(String message);

    void didFetch(RecipeDetailsResponse body, String message);
}
