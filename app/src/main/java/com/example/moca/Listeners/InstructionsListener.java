package com.example.moca.Listeners;

import com.example.moca.Models.InstructionsResponse;

import java.util.List;

public interface InstructionsListener {
    void didFetch(List<InstructionsResponse> resposnse, String message);
    void didError(String message);
}
