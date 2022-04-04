package com.example.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.widget.Button;
import android.widget.TextView;

public class profileView extends AppCompatActivity {


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_view);
        User user = getIntent().getParcelableExtra("users");
        TextView welcomeView = findViewById(R.id.profileWelcome);
        TextView changeName = findViewById(R.id.profile_nameTorRep);
        TextView changeHeight = findViewById(R.id.profile_heightToRep);
        TextView changeAge = findViewById(R.id.profile_ageToRep);
        TextView changeWeight = findViewById(R.id.profile_wegithToRep);
        TextView changeBmi = findViewById(R.id.profile_bmiToRep);
        welcomeView.setText("Welcome " + user.getEmail().toString());
        changeName.setText(user.getName().toString());
        changeAge.setText(user.getAge().toString());
        changeHeight.setText(user.getHeight().toString());
        changeWeight.setText(user.getWeight().toString());
        changeBmi.setText(user.getBmi().toString());


    }
}