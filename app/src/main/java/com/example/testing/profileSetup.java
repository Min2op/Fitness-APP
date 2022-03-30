package com.example.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

public class profileSetup extends AppCompatActivity{
    User holder = new User();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_profile_setup);

        Button addProfile = findViewById(R.id.addProfilebtn);
        addProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
            finished();

            }
        });

    }
    public void finished(){

        TextView age = findViewById(R.id.Age);
        //TextView height = findViewById(R.id.Height);
        //TextView weight = findViewById(R.id.weight);
        TextView name = findViewById(R.id.Name);
        String Name = name.getText().toString();
        holder.setAge(Integer.parseInt(age.getText().toString()));
        //holder.setHeight(Double.parseDouble(height.getText().toString()));
        //holder.setWeight(Double.parseDouble(weight.getText().toString()));
        holder.setName(Name);
        holder.setWeight(22.3);
        //holder.setAge(2);
        holder.setHeight(32.3);
        Intent face = new Intent(profileSetup.this, MainMenu.class);
        face.putExtra("users", holder);
        startActivity(face);
        Toast.makeText(profileSetup.this, "Profile Added!", Toast.LENGTH_SHORT);
    }

}