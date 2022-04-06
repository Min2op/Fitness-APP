package com.example.testing;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class bmiCalc extends AppCompatActivity {
    User RealUser = new User();

    FirebaseDatabase rootNode = FirebaseDatabase.getInstance(); // connect to fire base real time data base
    DatabaseReference reference; //Just  making this for later
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); //gets the current user based of the auth which would be google

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_bmi_calc);
        RealUser = getIntent().getParcelableExtra("users");
        reference = rootNode.getReference("User");

        TextView read = findViewById(R.id.bmiValue);
        Button event = findViewById(R.id.calcualteBmi);


        event.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Double Bmi, Height, Weight;
                Weight = RealUser.getWeight();
                Height = RealUser.getHeight();
                Bmi = (Weight / Height / Height) * 10000;
                Bmi = (double) Math.round(Bmi * 100) / 100;
                read.setText(Bmi.toString());
                RealUser.setbmi(Bmi);

                Intent face = new Intent(bmiCalc.this, MainMenu.class);
                startActivity(face);


            }


        });

    }
}

