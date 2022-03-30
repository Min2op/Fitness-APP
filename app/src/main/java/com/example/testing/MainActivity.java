package com.example.testing;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.TextView;
import android.widget.Toast;

import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.common.api.GoogleApiClient;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;

public class MainActivity extends AppCompatActivity {
    GoogleSignInOptions GSO;
    GoogleSignInClient GSC;

    @Override
    protected void onCreate(Bundle savedInstanceState) {

        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GSO = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestEmail()
                .build();

        GSC = GoogleSignIn.getClient(this, GSO);

        TextView username = (TextView) findViewById(R.id.username);
        TextView password = (TextView) findViewById(R.id.Password);

        MaterialButton log = (MaterialButton) findViewById(R.id.loginBtn);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                /* Without firebase
                if(username.getText().toString().equals("admin") && password.getText().toString().equals("admin")){
                    Toast.makeText(MainActivity.this, "Logged in!", Toast.LENGTH_SHORT).show();
                    openMainMenu();
                }
                else{
                    Toast.makeText(MainActivity.this, "Log in Failed! Try again", Toast.LENGTH_SHORT).show();
                }
                */
                signIn();

            }

        });
    }
    public void openMainMenu(){
        finish();
        Intent intent = new Intent(this, MainMenu.class);
        startActivity(intent);
    }
    public void signIn(){
        Intent signIn = GSC.getSignInIntent();
        startActivityForResult(signIn, 100);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,@Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == 100){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
                task.getResult(ApiException.class);
                openMainMenu();
            } catch (ApiException e) {
                e.printStackTrace();
            }

        }
    }
}