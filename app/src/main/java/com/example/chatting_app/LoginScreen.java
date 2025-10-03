package com.example.chatting_app;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.EditText;
import android.widget.Toast;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;

import java.util.ArrayList;

public class LoginScreen extends AppCompatActivity {


    EditText UserName, UserPass;
    Button SignIn, SignUp;
    FirebaseFirestore fStore;
    FirebaseAuth fAuth;
    ArrayList<ContactModel> userData = new ArrayList<>();

    USER_LOGIN_DATA userLoginData = new USER_LOGIN_DATA(this);
    ContactModel data = new ContactModel();

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_login_screen);


        data = userLoginData.getLoggedInUser();


        UserName = findViewById(R.id.UserName);
        UserPass = findViewById(R.id.UserPass);
        SignIn = findViewById(R.id.Loginbtn);
        SignUp = findViewById(R.id.Regbtn);


        fStore = FirebaseFirestore.getInstance();
        fetchUserData();

        SignUp.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                Intent intent = new Intent(LoginScreen.this, RegisterScreen.class);
                startActivity(intent);
            }
        });

        SignIn.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                String userid = UserName.getText().toString();
                String passid = UserPass.getText().toString();


                if (userid.isEmpty()) {
                    UserName.setError("Enter User Name");
                    UserName.requestFocus();
                } else if (passid.isEmpty()) {
                    UserPass.setError("Enter Password");
                    UserPass.requestFocus();
                } else {
                    boolean result = checkUser(userid,passid);

                    if(result==true){
                        Intent intent = new Intent(LoginScreen.this, MainActivity.class);
                        Toast.makeText(LoginScreen.this, "Login Successfully", Toast.LENGTH_SHORT).show();
                        startActivity(intent);
                    }else{
                        Toast.makeText(LoginScreen.this, "Wrong UserName OR Password", Toast.LENGTH_SHORT).show();
                    }
                }
            }
        });


    }

    public void fetchUserData() {
        fStore.collection("ChatGroup")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String username = document.getString("username");
                            String password = document.getString("password");
                            int avatarIndex = document.getLong("avatarIndex").intValue();
                            String documentId = document.getId();
                            userData.add(new ContactModel(username,password,avatarIndex,documentId));
                        }
                    }
                });
    }

    boolean checkUser(String userid, String passid) {
        for (ContactModel user : userData) {
            if(user.username.equals(userid) && user.password.equals(passid)){
                USER_LOGIN_DATA userLoginData = new USER_LOGIN_DATA(this);
                userLoginData.saveLoginData(userid,passid,user.documentId,user.avatarIndex,true);
                return true;
            }
        }
        return false;

    }

    @SuppressLint({"MissingSuperCall", "GestureBackNavigation"})
    @Override
    public void onBackPressed() {
        super.finishAffinity();
    }
}

