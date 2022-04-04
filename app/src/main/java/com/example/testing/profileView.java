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
        Button btn2 = findViewById(R.id.button8);
        btn.setText(user.getName());
        btn2.setText(user.getAge().toString());

    }
}