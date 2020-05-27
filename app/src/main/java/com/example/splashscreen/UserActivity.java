package com.example.splashscreen;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.ProgressBar;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.bumptech.glide.Glide;

import java.net.Inet4Address;

import retrofit2.Call;
import retrofit2.Callback;
import retrofit2.Response;

public class UserActivity extends AppCompatActivity {

    ImageView avatarImg;
    TextView usernameTV;
    TextView followersTV;
    TextView followingTV;
    TextView fullName;
    TextView imageurl;
    TextView email;
    Button ownedRepos;
    Bundle extras;                  //load stuff passed with intent
    String newString;
    String imgurl;
    ProgressBar progressBar;
    Button homeButton;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_user);

        initialise();

        extras = getIntent().getExtras();
        newString = extras.getString("usernameIntent");

        System.out.println(newString);              //to print the obtained username in logcat

        usernameTV.setText(newString);


        loadData();         //method to retrieve data from server


        imageurl.setText(imgurl);
        Glide.with(this).load(imgurl).into(avatarImg);



        ownedRepos.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this, Repos.class);

                Pair<View, String> pair1 = Pair.create(findViewById(R.id.repositories), "buttonAnim");

                ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(UserActivity.this, pair1);
                intent.putExtra("username", newString);
                startActivity(intent, activityOptions.toBundle());
            }
        });


        followersTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this, Followers.class);
                intent.putExtra("username", newString);
                intent.putExtra("buttonClicked", "followers");
                startActivity(intent);
            }
        });

        followingTV.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this, Followers.class);
                intent.putExtra("username", newString);
                intent.putExtra("buttonClicked", "following");
                startActivity(intent);
            }
        });

        homeButton.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(UserActivity.this, Login.class);
                startActivity(intent);
            }
        });

    }

    void initialise(){
        avatarImg = findViewById(R.id.avatar);
        usernameTV = findViewById(R.id.username2);
        followersTV = findViewById(R.id.followers);
        followingTV = findViewById(R.id.following);
        email = findViewById(R.id.email);
        fullName = findViewById(R.id.fullname);
        ownedRepos = findViewById(R.id.repositories);
        imageurl = findViewById(R.id.imgurl);
        progressBar = findViewById(R.id.progressBar);
        homeButton = findViewById(R.id.homeButton);


    }

    public void loadData(){

        final GithubUserEndPoints apiService = APIClient.getClient().create(GithubUserEndPoints.class);

        Call<GithubUser> call = apiService.getUser(newString);
        call.enqueue(new Callback<GithubUser>() {
            @Override
            public void onResponse(Call<GithubUser> call, Response<GithubUser> response) {
                progressBar.setVisibility(View.INVISIBLE);

                if(response.body() == null)
                {
                    usernameTV.setText("User DNE");
                    fullName.setVisibility(View.GONE);
                    followersTV.setVisibility(View.GONE);
                    followingTV.setVisibility(View.GONE);
                    ownedRepos.setVisibility(View.GONE);
                    email.setVisibility(View.GONE);

                }
                else
                {
                    if(response.body().getName() == null){
                        fullName.setVisibility(View.INVISIBLE);
                    }
                    else {
                        fullName.setText("a.k.a "+response.body().getName());
                    }

                    followersTV.setText(response.body().getFollowers()+"\nfollowers");
                    followingTV.setText(response.body().getFollowing()+"\nfollowing");
                    //usernameTV.setText(response.body().getLogin());

                    if(response.body().getEmail() == null){
                        email.setText("Email : Not provided");
                    }
                    else {

                        email.setText("Email : "+response.body().getEmail());
                    }

                    imgurl = response.body().getAvatar();
                    Glide.with(UserActivity.this).load(imgurl).into(avatarImg);         //3rd party interface which allows to display image using url

                }

            }

            @Override
            public void onFailure(Call<GithubUser> call, Throwable t) {

                usernameTV.setText("Error! ");

            }
        });

    }
}
