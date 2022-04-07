package com.example.testing;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.auth.api.identity.BeginSignInRequest;
import com.google.android.gms.auth.api.identity.SignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.common.api.ApiException;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.android.material.button.MaterialButton;
import com.google.firebase.auth.AuthCredential;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.auth.FirebaseUser;
import com.google.firebase.auth.GoogleAuthProvider;
//Sign in page dont mess with this
public class MainActivity extends AppCompatActivity {
    private SignInClient oneTapClient; //Google sign in api
    private BeginSignInRequest signUpRequest; //Googgle sign in requrest api
    private static final int RC_SING_IN = 9001; //Tag for signing in
    private static final String TAG = "GoogleActivity"; // tag for actiivty
    private FirebaseAuth mAuth; //setting up the authentiaction for firebase
    private GoogleSignInClient gsc; //Googsignin


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_main);

        GoogleSignInOptions GSO = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getString(R.string.defualt_web_client_id))
                .requestEmail()
                .build();
       gsc = GoogleSignIn.getClient(this, GSO);
       mAuth = FirebaseAuth.getInstance();


        TextView username = (TextView) findViewById(R.id.username);
        TextView password = (TextView) findViewById(R.id.Password);

        MaterialButton log = (MaterialButton) findViewById(R.id.loginBtn);
        log.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
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
        Intent signIn = gsc.getSignInIntent();
        startActivityForResult(signIn, RC_SING_IN);
    }

    @Override
    protected void onActivityResult(int requestCode, int resultCode,@Nullable Intent data) {
        super.onActivityResult(requestCode, resultCode, data);
        if(requestCode == RC_SING_IN){
            Task<GoogleSignInAccount> task = GoogleSignIn.getSignedInAccountFromIntent(data);
            try {
               GoogleSignInAccount account = task.getResult(ApiException.class);
                firebaseAuthWithGoogle(account.getIdToken());
                openMainMenu();
            } catch (ApiException e) {
                e.printStackTrace();
            }
        }


    }

    private void firebaseAuthWithGoogle(String idToken) {
        AuthCredential credential = GoogleAuthProvider.getCredential(idToken, null);
        mAuth.signInWithCredential(credential)
                .addOnCompleteListener(this, new OnCompleteListener<AuthResult>() {
                    @Override
                    public void onComplete(@NonNull Task<AuthResult> task) {
                        if(task.isSuccessful()){
                            Log.d(TAG, "signInWithCredential:success");
                            FirebaseUser user = mAuth.getCurrentUser();
                        }else{
                            Log.w(TAG, "singInWithCredential:failure");
                        }
                    }
                });
    }


}

