package com.example.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;


public class MainMenu extends AppCompatActivity {
    User users;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main_menu);
        users = getIntent().getParcelableExtra("users");
        Button setupProfile = findViewById(R.id.profileSetup);
        Button bmiCacl = findViewById(R.id.bmiCalc);
        Button viewProfile = findViewById(R.id.viewProfile);

        viewProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                openProfView();

            }
        });
        setupProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            openProfileSetup();

            }
        });
    }


    public void openProfileSetup(){
        Intent face = new Intent(this, profileSetup.class);
        startActivity(face);
    }
    public void openProfView(){
        Intent test = new Intent(this, profileView.class);
        test.putExtra("users", users);
        startActivity(test);
    }
}