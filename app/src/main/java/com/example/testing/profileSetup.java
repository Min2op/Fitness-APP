package com.example.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.TextView;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;


public class profileSetup extends AppCompatActivity{
    private FirebaseAuth mAuth;
    FirebaseDatabase rootNode = FirebaseDatabase.getInstance();
    DatabaseReference reference;
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
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
        reference = rootNode.getReference("User");
        User newUser = new User();
        EditText Age = findViewById(R.id.Age);
        EditText Name = findViewById(R.id.Name);
        EditText Height = findViewById(R.id.Height);
        EditText Weight = findViewById(R.id.weight);

        String s_name = Name.getText().toString();
        Integer s_age = Integer.parseInt(Age.getText().toString());

       // Double s_height = Double.parseDouble(Height.getText().toString());
       // Double s_weight = Double.parseDouble(Weight.getText().toString());

        newUser.setUID(user.getUid().toString());
        newUser.setName(s_name);
        newUser.setAge(s_age);
        newUser.setHeight(182.2);
        newUser.setWeight(16.7);
        newUser.setEmail(user.getEmail());
        newUser.setbmi(23.9);
        reference.child(user.getUid()).setValue(newUser);
        Intent face = new Intent(profileSetup.this, MainMenu.class);
        face.putExtra("users", newUser);
        startActivity(face);

    }

}