package com.example.splashscreen;


import com.example.splashscreen.GithubUser;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FollowersEndPoint {

    @GET("/users/{user}/followers")
    Call<List<GithubUser>> getFollowersList(@Path("user") String name);
}
