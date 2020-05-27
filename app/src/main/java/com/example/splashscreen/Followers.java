package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.os.Bundle;
import android.util.Log;
import android.widget.TextView;

import java.util.ArrayList;
import java.util.List;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class Followers extends AppCompatActivity {

    String receivedUsername;
    TextView usernameFollow;
    TextView testFollow;
    RecyclerView mRecyclerView;
    TextView buttonClickedTV;

    List<GithubUser> mydataSource = new ArrayList<>();
    RecyclerView.Adapter myAdapter;
    String buttonClicked;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_followers);

        Bundle extras = getIntent().getExtras();
        receivedUsername = extras.getString("username");
        buttonClicked = extras.getString("buttonClicked");

        usernameFollow = findViewById(R.id.usernameFollow);
        testFollow = findViewById(R.id.testFollow);
        mRecyclerView = findViewById(R.id.recyclerViewFollow);
        buttonClickedTV = findViewById(R.id.buttonClickedTV);

        mRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        myAdapter = new FollowersAdapter(mydataSource, R.layout.list_item_follow, getApplicationContext());
        mRecyclerView.setAdapter(myAdapter);

        usernameFollow.setText(receivedUsername);

        if(buttonClicked.equals("followers")){

            buttonClickedTV.setText("Followers list");
            loadFollowersList();
        }
        if (buttonClicked.equals("following")){

            buttonClickedTV.setText("Following list");

            loadFollowingList();
        }



    }

    public void loadFollowersList(){

        FollowersEndPoint apiService = APIClient.getClient().create(FollowersEndPoint.class);
        Call<List<GithubUser>> call = apiService.getFollowersList(receivedUsername);

        call.enqueue(new Callback<List<GithubUser>>() {
            @Override
            public void onResponse(Call<List<GithubUser>> call, Response<List<GithubUser>> response) {
                if(response.body()==null)
                {
                    usernameFollow.setText("Null response from server");
                }
                else
                {
                    mydataSource.clear();;
                    mydataSource.addAll(response.body());
                    myAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<List<GithubUser>> call, Throwable t) {
                Log.e("Followers bt", t.toString());

            }
        });


    }

    public void loadFollowingList(){

        FollowingEndPoint apiService = APIClient.getClient().create(FollowingEndPoint.class);
        Call<List<GithubUser>> call = apiService.getFollowingList(receivedUsername);

        call.enqueue(new Callback<List<GithubUser>>() {
            @Override
            public void onResponse(Call<List<GithubUser>> call, Response<List<GithubUser>> response) {
                if(response.body()==null)
                {
                    usernameFollow.setText("Null response from server");
                }
                else
                {
                    mydataSource.clear();;
                    mydataSource.addAll(response.body());
                    myAdapter.notifyDataSetChanged();
                }

            }

            @Override
            public void onFailure(Call<List<GithubUser>> call, Throwable t) {
                Log.e("Following bt", t.toString());

            }
        });


    }
}
