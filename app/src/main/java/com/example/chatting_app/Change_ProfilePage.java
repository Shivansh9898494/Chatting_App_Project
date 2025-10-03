package com.example.chatting_app;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.HashMap;
import java.util.Map;

public class Change_ProfilePage extends AppCompatActivity {

    ImageButton addAvtar;
    EditText UserName,UserPass;
    Button Change;
    setProfilePic profilePic = new setProfilePic();
    FirebaseFirestore firestore;
    public int avatarIndex = 0;
    USER_LOGIN_DATA userLoginData = new USER_LOGIN_DATA(this);
    ContactModel data = new ContactModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_change_profile_page);

        addAvtar = findViewById(R.id.image);
        UserName = findViewById(R.id.ChangeUserName);
        UserPass = findViewById(R.id.ChangeUserPass);
        Change = findViewById(R.id.ChangeSave);

        firestore = FirebaseFirestore.getInstance();



        addAvtar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(Change_ProfilePage.this);
                dialog.setContentView(R.layout.add_avatar);
                ImageButton avtar1 = dialog.findViewById(R.id.avtar1);
                ImageButton avtar2 = dialog.findViewById(R.id.avtar2);
                ImageButton avtar3 = dialog.findViewById(R.id.avtar3);
                ImageButton avtar4 = dialog.findViewById(R.id.avtar4);
                ImageButton avtar5 = dialog.findViewById(R.id.avtar5);
                ImageButton avtar6 = dialog.findViewById(R.id.avtar6);
                ImageButton avtar7 = dialog.findViewById(R.id.avtar7);
                ImageButton avtar8 = dialog.findViewById(R.id.avtar8);
                ImageButton avtar9 = dialog.findViewById(R.id.avtar9);
                ImageButton avtar10 = dialog.findViewById(R.id.avtar10);
                ImageButton avtar11 = dialog.findViewById(R.id.avtar11);
                ImageButton avtar12 = dialog.findViewById(R.id.avtar12);
                ImageButton avtar13 = dialog.findViewById(R.id.avtar13);
                ImageButton avtar14 = dialog.findViewById(R.id.avtar14);
                ImageButton avtar15 = dialog.findViewById(R.id.avtar15);

                dialog.show();

                avtar1.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        avatarIndex = 1;
                        addAvtar.setImageResource(R.drawable.avtar1);
                        dialog.dismiss();
                    }
                });
                avtar2.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        avatarIndex = 2;
                        addAvtar.setImageResource(R.drawable.avtar2);
                        dialog.dismiss();
                    }
                });

                avtar3.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        avatarIndex = 3;
                        addAvtar.setImageResource(R.drawable.avtar3);
                        dialog.dismiss();
                    }
                });
                avtar4.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        avatarIndex = 4;
                        addAvtar.setImageResource(R.drawable.avtar4);
                        dialog.dismiss();
                    }
                });

                avtar5.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        avatarIndex = 5;
                        addAvtar.setImageResource(R.drawable.avtar5);
                        dialog.dismiss();
                    }
                });
                avtar6.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        avatarIndex = 6;
                        addAvtar.setImageResource(R.drawable.avtar6);
                        dialog.dismiss();
                    }
                });

                avtar7.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        avatarIndex = 7;
                        addAvtar.setImageResource(R.drawable.avtar7);
                        dialog.dismiss();
                    }
                });
                avtar8.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        avatarIndex = 8;
                        addAvtar.setImageResource(R.drawable.avtar8);
                        dialog.dismiss();
                    }
                });

                avtar9.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        avatarIndex = 9;
                        addAvtar.setImageResource(R.drawable.avtar9);
                        dialog.dismiss();
                    }
                });
                avtar10.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        avatarIndex = 10;
                        addAvtar.setImageResource(R.drawable.avtar10);
                        dialog.dismiss();
                    }
                });

                avtar11.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        avatarIndex = 11;
                        addAvtar.setImageResource(R.drawable.avtar11);
                        dialog.dismiss();
                    }
                });
                avtar12.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        avatarIndex = 12;
                        addAvtar.setImageResource(R.drawable.avtar12);
                        dialog.dismiss();
                    }
                });

                avtar13.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        avatarIndex = 13;
                        addAvtar.setImageResource(R.drawable.avtar13);
                        dialog.dismiss();
                    }
                });
                avtar14.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        avatarIndex = 14;
                        addAvtar.setImageResource(R.drawable.avtar14);
                        dialog.dismiss();
                    }
                });
                avtar15.setOnClickListener(new View.OnClickListener() {
                    @Override
                    public void onClick(View v) {
                        avatarIndex = 15;
                        addAvtar.setImageResource(R.drawable.avtar15);
                        dialog.dismiss();

                    }
                });



            }

        });



        Change.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                String name = UserName.getText().toString();
                String pass = UserPass.getText().toString();

                if(name.isEmpty()){
                    UserName.setError("Enter User Name");
                }else if (pass.isEmpty()){
                    UserPass.setError("Enter Password");
                }else {
                    updateData(name,pass,avatarIndex);
                }
            }
        });

    }

    private void updateData(String name,String pass,int avatarIndex) {
        data = userLoginData.getLoggedInUser();
        Map<String, Object> userData = new HashMap<>();
        userData.put("username", name);
        userData.put("password", pass);
        userData.put("avatarIndex", avatarIndex);

        //Toast.makeText(this, "Updating profile...", Toast.LENGTH_SHORT).show();

        firestore.collection("ChatGroup").document(data.documentId)
                .update(userData)
                .addOnSuccessListener(new OnSuccessListener<Void>() {
                    @Override
                    public void onSuccess(Void aVoid) {
                        Toast.makeText(Change_ProfilePage.this, "Profile Updated Successfully!", Toast.LENGTH_SHORT).show();
                        Intent intent = new Intent(Change_ProfilePage.this, LoginScreen.class);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(Change_ProfilePage.this, "Error updating profile: " + e.getMessage(), Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @SuppressLint({"GestureBackNavigation", "MissingSuperCall"})
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(Change_ProfilePage.this,MainActivity.class);
        startActivity(intent);
    }


}