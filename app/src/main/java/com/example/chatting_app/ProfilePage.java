package com.example.chatting_app;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class ProfilePage extends AppCompatActivity {

    ImageView avtar;
    TextView UserName,UserPassword;
    Button Change;
    setProfilePic profilePic = new setProfilePic();

    ImageButton Homepage, ProfilePage, SettingPage;

    USER_LOGIN_DATA userLoginData = new USER_LOGIN_DATA(this);
    ContactModel data = new ContactModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_profile_page);

        data = userLoginData.getLoggedInUser();


        Homepage = findViewById(R.id.homePage);
        ProfilePage = findViewById(R.id.ProfilePage);
        SettingPage = findViewById(R.id.SettingPage);
        avtar = findViewById(R.id.image);
        UserName = findViewById(R.id.Name);
        UserPassword = findViewById(R.id.Pass);
        Change = findViewById(R.id.Change);


        avtar.setImageResource(profilePic.setAvtar(data.avatarIndex));
        UserName.setText(data.username);
        UserPassword.setText(data.password);





        ProfilePage.setImageResource(profilePic.setAvtar(data.avatarIndex));


        Change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfilePage.this,Change_ProfilePage.class);
                intent.putExtra("documentId",data.documentId);
                startActivity(intent);
            }
        });

        Homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfilePage.this,MainActivity.class);
                startActivity(intent);
            }
        });
        SettingPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(ProfilePage.this,Setting_Page.class);
                startActivity(intent);
            }
        });
    }

    @SuppressLint({"GestureBackNavigation", "MissingSuperCall"})
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(ProfilePage.this,MainActivity.class);
        startActivity(intent);
    }
}