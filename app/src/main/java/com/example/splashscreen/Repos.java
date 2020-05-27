package com.example.splashscreen;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Repos extends AppCompatActivity {
    String receivedUsername;
    TextView usernameTV;
    RecyclerView mRecyclerView;
    List<GithubRepo> mydataSource = new ArrayList<>();
    RecyclerView.Adapter myAdapter;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_repos);

        Bundle extras = getIntent().getExtras();
        receivedUsername = extras.getString("username");

        usernameTV = findViewById(R.id.usernameRepo);
        usernameTV.setText(receivedUsername);
        mRecyclerView = findViewById(R.id.recyclerViewRepo);


        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new ReposAdapter(mydataSource, R.layout.list_item_repo, getApplicationContext());
        mRecyclerView.setAdapter(myAdapter);

        loadRepositories();

    }
    public void loadRepositories(){

        GithubrRepoEndpoint apiService = APIClient.getClient().create(GithubrRepoEndpoint.class);
        Call<List<GithubRepo>> call = apiService.getRepo(receivedUsername);

        call.enqueue(new Callback<List<GithubRepo>>() {
            @Override
            public void onResponse(Call<List<GithubRepo>> call, Response<List<GithubRepo>> response) {

                mydataSource.clear();;
                mydataSource.addAll(response.body());
                myAdapter.notifyDataSetChanged();

            }

            @Override
            public void onFailure(Call<List<GithubRepo>> call, Throwable t) {
                Log.e("Repos", t.toString());

            }
        });
    }
}
