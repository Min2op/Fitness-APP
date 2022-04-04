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
    FirebaseDatabase rootNode = FirebaseDatabase.getInstance(); // connect to fire base real time data base
    DatabaseReference reference; //Just  making this for later
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); //gets the current user based of the auth which would be google

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
        reference = rootNode.getReference("User"); //Here we assign reference to the Node User in the Json tree.
        /*
        All this below is just writing the data from the textView fields into the data base
         */
        User newUser = new User();
        EditText Age = findViewById(R.id.Age);
        EditText Name = findViewById(R.id.Name);
        EditText Height = findViewById(R.id.Height);
        EditText Weight = findViewById(R.id.weight);

        String s_name = Name.getText().toString();
        Integer s_age = Integer.parseInt(Age.getText().toString());

        Double s_height = Double.parseDouble(Height.getText().toString());
        Double s_weight = Double.parseDouble(Weight.getText().toString());

        newUser.setUID(user.getUid().toString());
        newUser.setName(s_name);
        newUser.setAge(s_age);
        newUser.setHeight(s_height);
        newUser.setWeight(s_weight);
        newUser.setEmail(user.getEmail());

        reference.child(user.getUid()).setValue(newUser);
        Intent face = new Intent(profileSetup.this, MainMenu.class);
        face.putExtra("users", newUser);
        startActivity(face);

    }

}