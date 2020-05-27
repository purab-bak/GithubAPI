package com.example.splashscreen;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface GithubrRepoEndpoint {


    @GET("/users/{user}/repos")
    Call<List<GithubRepo>> getRepo(@Path("user") String name);

}
