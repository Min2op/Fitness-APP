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
    User users = new User();
    FirebaseUser user = FirebaseAuth.getInstance().getCurrentUser();
    @Override
    protected void onCreate(Bundle savedInstanceState) {
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
                bmiCacl.setText(user.getEmail());
                //openBmiCal();
            }
        });
    }
    private void openBmiCal() {

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
    public void LoadUser(){
        DatabaseReference reference = FirebaseDatabase.getInstance().getReference("User");
        Query checkUser = reference.orderByChild("uid").equalTo(user.getUid());

        checkUser.addListenerForSingleValueEvent(new ValueEventListener() {
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                if(snapshot.exists()){

                    String nameFromSnap = snapshot.child(user.getUid()).child("name").getValue(String.class);
                    Integer ageFromSnap = snapshot.child(user.getUid()).child("age").getValue(Integer.class);
                    Double heightFromSnap = snapshot.child(user.getUid()).child("height").getValue(Double.class);
                    Double weightFromSnap = snapshot.child(user.getUid()).child("weight").getValue(Double.class);

                    Button set = findViewById(R.id.bmiCalc);
                    set.setText(ageFromSnap.toString());
                    //users.setWeight(weightFromSnap);
                    //users.setbmi(0.00);
                    //users.setAge(ageFromSnap);
                    //users.setName(nameFromSnap);
                    //users.setHeight(heightFromSnap);

                    Toast test = Toast.makeText(MainMenu.this, "Test" ,LENGTH_LONG);
                    test.show();
                }
                else{
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