package com.example.anthony_pc.guidetocr.Class;

import retrofit2.Call;
import retrofit2.http.Body;
import retrofit2.http.GET;
import retrofit2.http.Headers;
import retrofit2.http.POST;

/**
 * Created by Anthony-PC on 10/6/2018.
 */

public interface RedditAPI {

    @POST("word")
    Call<Palabra> create_word(@Body Palabra palabra);

}
