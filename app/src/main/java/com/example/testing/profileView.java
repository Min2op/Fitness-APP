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

        Button btn = findViewById(R.id.button);
        btn.setText(user.getName());

    }
}