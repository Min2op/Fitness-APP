package com.example.testing;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;

public class Update_Profile extends AppCompatActivity {

    FirebaseDatabase rootNode = FirebaseDatabase.getInstance(); // connect to fire base real time data base
    DatabaseReference reference; //Just  making this for later
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser(); //gets the current user based of the auth which would be google

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_update_profile);

        Button addProfile = findViewById(R.id.updateProfilebtn);
        addProfile.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                updateProfile();
            }
        });

    }

    public void updateProfile(){
        reference = rootNode.getReference("User"); //Here we assign reference to the Node User in the Json tree.

        //All this below is just writing the data from the text

        User newUser = new User();
        EditText Age = findViewById(R.id.uAge);
        EditText Name = findViewById(R.id.uName);
        EditText Height = findViewById(R.id.uHeight);
        EditText Weight = findViewById(R.id.uweight);

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
        newUser.setbmi(0);

        reference.child(user.getUid()).setValue(newUser);
        Intent face = new Intent(Update_Profile.this, MainMenu.class);
        face.putExtra("users", newUser);
        startActivity(face);


    }
}