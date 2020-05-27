package com.example.splashscreen;

import java.util.List;

import retrofit2.Call;
import retrofit2.http.GET;
import retrofit2.http.Path;

public interface FollowingEndPoint {
    @GET("/users/{user}/following")
    Call<List<GithubUser>> getFollowingList(@Path("user") String name);
}
