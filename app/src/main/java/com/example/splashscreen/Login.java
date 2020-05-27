package com.example.splashscreen;

import androidx.appcompat.app.AppCompatActivity;

import android.app.ActivityOptions;
import android.content.Intent;
import android.os.Bundle;
import android.util.Pair;
import android.view.View;
import android.widget.Button;

import com.google.android.material.button.MaterialButton;
import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class Login extends AppCompatActivity {

    MaterialButton viewDetails;
    TextInputLayout usernameLayout;
    TextInputEditText username;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_login);

        init();

        viewDetails.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                if(username.getText().toString().isEmpty()) usernameLayout.setError("Please enter username");
                else {
                    Intent intent = new Intent(Login.this, UserActivity.class);

                    Pair<View, String> pair1 = Pair.create(findViewById(R.id.viewDetails), "buttonAnim");

                    ActivityOptions activityOptions = ActivityOptions.makeSceneTransitionAnimation(Login.this, pair1);
                    intent.putExtra("usernameIntent", username.getText().toString());
                    startActivity(intent, activityOptions.toBundle());
                }
            }
        });

    }
    public void init(){

        viewDetails = findViewById(R.id.viewDetails);
        usernameLayout = findViewById(R.id.usernameLayout);
        username = findViewById(R.id.username);

    }
}
