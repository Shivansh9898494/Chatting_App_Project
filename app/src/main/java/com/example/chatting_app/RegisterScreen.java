package com.example.chatting_app;

import android.annotation.SuppressLint;
import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.core.graphics.Insets;
import androidx.core.view.ViewCompat;
import androidx.core.view.WindowInsetsCompat;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.OnFailureListener;
import com.google.android.gms.tasks.OnSuccessListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.AuthResult;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.firestore.DocumentReference;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.auth.User;

public class RegisterScreen extends AppCompatActivity {

    EditText UserName, UserPass, UserPass2;
    Button SignIn,SignUp;
    FirebaseFirestore firestore;
    ImageButton addAvtar;

    int avatarIndex = 0;
    SignUpModel signUpModel;

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_register_screen);

        UserName = findViewById(R.id.UserName);
        UserPass = findViewById(R.id.UserPass);
        UserPass2 = findViewById(R.id.UserPass2);
        SignIn = findViewById(R.id.SignIn);
        SignUp = findViewById(R.id.SignUp);
        addAvtar = findViewById(R.id.image);


        addAvtar.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Dialog dialog = new Dialog(RegisterScreen.this);
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


        firestore = FirebaseFirestore.getInstance();

        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(RegisterScreen.this, LoginScreen.class);
                startActivity(intent);
            }
        });

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String username = UserName.getText().toString();
                String password = UserPass.getText().toString();
                String password2 = UserPass2.getText().toString();

                if(username.isEmpty()){
                    UserName.setError("Enter User Name");
                }else if (password.isEmpty()){
                    UserPass.setError("Enter Password");
                }else if (password2.isEmpty()){
                    UserPass2.setError("Re-Enter Password");
                }else if(!password.equals(password2)){
                    UserPass2.setError("Password Not Match");
                }else{
                    //SignUpModel signUpModel = new SignUpModel(username, password,avatarIndex);
                    signUpModel = new SignUpModel(username,password,avatarIndex);
                    saveIDPassToFirestore(signUpModel);
                }

            }
        });
    }
    private void saveIDPassToFirestore(SignUpModel signUpModel) {

        firestore.collection("ChatGroup")
                .add(signUpModel)
                .addOnSuccessListener(new OnSuccessListener<DocumentReference>() {
                    @Override
                    public void onSuccess(DocumentReference documentReference) {
                        Toast toast = Toast.makeText(RegisterScreen.this, " Sign Up Successfully", Toast.LENGTH_SHORT);
                        toast.show();
                        Intent intent = new Intent(RegisterScreen.this, LoginScreen.class);
                        startActivity(intent);
                    }
                })
                .addOnFailureListener(new OnFailureListener() {
                    @Override
                    public void onFailure(@NonNull Exception e) {
                        Toast.makeText(RegisterScreen.this, "Something Wnet Wrong", Toast.LENGTH_SHORT).show();
                    }
                });
    }

    @SuppressLint({"GestureBackNavigation", "MissingSuperCall"})
    @Override
    public void onBackPressed() {
        Intent intent = new Intent(RegisterScreen.this,LoginScreen.class);
        startActivity(intent);
    }

}