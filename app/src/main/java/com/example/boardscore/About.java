package com.example.boardscore;

import android.content.Context;
import android.content.Intent;
import android.content.SharedPreferences;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.bumptech.glide.Glide;
import com.facebook.AccessToken;
import com.facebook.login.LoginManager;
import com.facebook.login.widget.LoginButton;
import com.google.android.gms.auth.api.signin.GoogleSignIn;
import com.google.android.gms.auth.api.signin.GoogleSignInAccount;
import com.google.android.gms.auth.api.signin.GoogleSignInClient;
import com.google.android.gms.auth.api.signin.GoogleSignInOptions;
import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;

import de.hdodenhof.circleimageview.CircleImageView;

public class About extends AppCompatActivity {
    public static final String GOOGLE_ACCOUNT = "google_account";

    private Toolbar toolbar;
    private CircleImageView imageView;
    private TextView nama, email;
    private GoogleSignInClient googleSignInClient;
    private Button SinOut;
    private LoginButton loginButton;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.about);
        imageView = findViewById(R.id.imageview);
        nama = findViewById(R.id.name);
        email = findViewById(R.id.email);
        SinOut = findViewById(R.id.logout);
        GoogleSignInAccount googleSignInAccount = getIntent().getParcelableExtra(GOOGLE_ACCOUNT);
        final AccessToken accessToken = AccessToken.getCurrentAccessToken();
        boolean isLoggedIn = accessToken != null && !accessToken.isExpired();
        if (accessToken != null){
            Glide.with(this)
                    .load(getIntent().getStringExtra("data3"))
                    .into(imageView);
            nama.setText("Name = " + getIntent().getStringExtra("data1"));
            email.setText("Email = " + getIntent().getStringExtra("data2"));
        }else
            {

        }
        GoogleSignInAccount alreadyloggedAccount = GoogleSignIn.getLastSignedInAccount(this);
        if (alreadyloggedAccount != null) {

            Glide.with(this)
                    .load(googleSignInAccount.getPhotoUrl())
                    .into(imageView);
            nama.setText("Name = " + googleSignInAccount.getDisplayName());
            email.setText("Email = " + googleSignInAccount.getEmail());        } else {
        }
 GoogleSignInOptions gso = new GoogleSignInOptions.Builder(GoogleSignInOptions.DEFAULT_SIGN_IN)
                .requestIdToken(getResources().getString(R.string.server_client_id))
                .requestEmail()
                .build();

        googleSignInClient = GoogleSignIn.getClient(this, gso);
        SinOut.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
        LoginManager.getInstance().logOut();

        AccessToken.setCurrentAccessToken(null);
        googleSignInClient.signOut().addOnCompleteListener(new OnCompleteListener<Void>() {
            @Override
            public void onComplete(@NonNull Task<Void> task) {
                Intent intent = new Intent(About.this, Login.class);
                intent.addFlags(Intent.FLAG_ACTIVITY_NEW_TASK | Intent.FLAG_ACTIVITY_CLEAR_TASK | Intent.FLAG_ACTIVITY_CLEAR_TOP);
                startActivity(intent);
            }
        });
    }
});

    }

    public void onClickFacebookLogoutButton(View view) {
        if (view == SinOut) {
        }
    }



}