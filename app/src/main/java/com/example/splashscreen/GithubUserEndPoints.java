package com.example.splashscreen;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubUserEndPoints {


    @GET("/users/{user}")
    Call<GithubUser> getUser(@Path("user") String user);        //@Path is a variable substitution for the API endpoint
}
