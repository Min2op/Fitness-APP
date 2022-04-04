package com.example.testing;

import static android.widget.Toast.LENGTH_LONG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.Query;
import com.google.firebase.database.ValueEventListener;


public class MainMenu extends AppCompatActivity {
    User RealUser;
    FirebaseUser firebaseUseruser = FirebaseAuth.getInstance().getCurrentUser(); // Get the instance of firebase
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        User users = new User(); // New user
        super.onCreate(savedInstanceState);
        LoadUser();
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
        bmiCacl.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                bmiCacl.setText(firebaseUseruser.getEmail());
                //openBmiCal();
            }
        });
    }

    private void openBmiCal() {
        //to be added
    }
    public void openProfileSetup(){
        Intent face = new Intent(this, profileSetup.class);
        startActivity(face);
    }
    public void openProfView(){
        Intent test = new Intent(this, profileView.class);
        test.putExtra("users", RealUser);
        startActivity(test);
    }
    public void LoadUser(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User"); //We are creating a reference at the Node User
        Query checkUser = reference.orderByChild("uid").equalTo(firebaseUseruser.getUid()); //The child node of user is the users UID we check this

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){  //If there is a UID that matches the firebase user then we have a profile to get data from
                    User tempUser = new User();
                    String nameFromSnap = snapshot.child(firebaseUseruser.getUid()).child("name").getValue(String.class);
                    Integer ageFromSnap = snapshot.child(firebaseUseruser.getUid()).child("age").getValue(Integer.class);
                    Double heightFromSnap = snapshot.child(firebaseUseruser.getUid()).child("height").getValue(Double.class);
                    Double weightFromSnap = snapshot.child(firebaseUseruser.getUid()).child("weight").getValue(Double.class);
                    Double bmiFromSnap = snapshot.child(firebaseUseruser.getUid()).child("bmi").getValue(Double.class);

                    tempUser.setAge(ageFromSnap);
                    tempUser.setName(nameFromSnap);
                    tempUser.setbmi(bmiFromSnap);
                    tempUser.setUID(firebaseUseruser.getUid());
                    tempUser.setHeight(heightFromSnap);
                    tempUser.setEmail(firebaseUseruser.getEmail());
                    tempUser.setWeight(weightFromSnap);
                    RealUser = tempUser;

                    Toast test = Toast.makeText(MainMenu.this, "Profile Already in Place" ,LENGTH_LONG);
                    test.show();
                }
                else{
                    //If there is no profile set up for this fire base user we direct them to theset up profile page
                    Intent intent = new Intent(MainMenu.this, profileSetup.class);
                    startActivity(intent);
                }

            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}