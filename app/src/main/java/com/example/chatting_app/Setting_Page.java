package com.example.chatting_app;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.graphics.RenderEffect;
import android.graphics.Shader;
import android.os.Build;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

public class Setting_Page extends AppCompatActivity {

    ImageButton Homepage, ProfilePage, SettingPage;
    Button Log_Out;
    USER_LOGIN_DATA userLoginData = new USER_LOGIN_DATA(Setting_Page.this);
    setProfilePic setAvtarToList = new setProfilePic();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_setting_page);

        ContactModel data = new ContactModel();
        data = userLoginData.getLoggedInUser();

        Homepage = findViewById(R.id.homePage);
        ProfilePage = findViewById(R.id.ProfilePage);
        SettingPage = findViewById(R.id.SettingPage);
        Log_Out = findViewById(R.id.Log_Out);

        ProfilePage.setImageResource(setAvtarToList.setAvtar(data.avatarIndex));

        Homepage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Setting_Page.this, MainActivity.class);
                startActivity(intent);
            }
        });
        ProfilePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Setting_Page.this, ProfilePage.class);
                startActivity(intent);
            }
        });
        Log_Out.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(Setting_Page.this);
                dialog.setContentView(R.layout.log_out_box);
                Button Yes = dialog.findViewById(R.id.ExitYes);
                Button No = dialog.findViewById(R.id.ExitNo);
                Yes.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {

                        userLoginData.saveLoginData("d", "d", "d", 0, false);
                        Intent intent = new Intent(Setting_Page.this, LoginScreen.class);
                        Toast.makeText(Setting_Page.this, "Log_Out Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                        dialog.dismiss();
                    }
                });
                No.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        Toast.makeText(Setting_Page.this, "Log_Out Cancel", Toast.LENGTH_SHORT).show();
                        dialog.dismiss();
                    }
                });
                dialog.show();
            }
        });
    }

    @SuppressLint({"GestureBackNavigation", "MissingSuperCall"})
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Setting_Page.this,MainActivity.class);
        startActivity(intent);
    }
}