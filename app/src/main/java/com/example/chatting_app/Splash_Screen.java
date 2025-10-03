package com.example.chatting_app;

import static android.content.ContentValues.TAG;

import android.content.Intent;
import android.os.Bundle;
import android.os.Handler;
import android.util.Log;
import android.window.SplashScreen;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Splash_Screen extends AppCompatActivity {




    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_splash_screen);

        new Handler().postDelayed(new Runnable() {
            @Override
            public void run() {
                USER_LOGIN_DATA userLoginData = new USER_LOGIN_DATA(Splash_Screen.this);
                ContactModel data = userLoginData.getLoggedInUser();
                if(data==null){
                    Intent intent = new Intent(Splash_Screen.this, LoginScreen.class);
                    startActivity(intent);
                    finish();
                }
                else{
                    Intent intent = new Intent(Splash_Screen.this, MainActivity.class);
                    startActivity(intent);
                    finish();
                }
            }
        },1500);
    }
}