package com.example.chatting_app;

import static android.content.ContentValues.TAG;

import android.app.Dialog;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.ImageButton;
import android.widget.ImageView;

import androidx.activity.EdgeToEdge;
import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.firestore.FirebaseFirestore;
import com.google.firebase.firestore.QueryDocumentSnapshot;
import com.google.firebase.firestore.QuerySnapshot;
import com.google.firebase.firestore.auth.User;

import java.util.ArrayList;

public class MainActivity extends AppCompatActivity {


    ImageButton Homepage, ProfilePage, SettingPage;
    RecyclerView UserListRecyclerView;
    ArrayList<Recycle_List_Model> Userlist = new ArrayList<>();
    RecycleViewAdapter adapter;
    ArrayList<Integer> avtar = new ArrayList<>();
    FirebaseFirestore firestore;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    setProfilePic profilePic = new setProfilePic();
    USER_LOGIN_DATA userLoginData = new USER_LOGIN_DATA(this);
    ContactModel data = new ContactModel();


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        EdgeToEdge.enable(this);
        setContentView(R.layout.activity_main);

        data = userLoginData.getLoggedInUser();







        Homepage = findViewById(R.id.homePage);
        ProfilePage = findViewById(R.id.ProfilePage);
        SettingPage = findViewById(R.id.SettingPage);
        UserListRecyclerView = findViewById(R.id.UserListRecyclerView);
        firestore = FirebaseFirestore.getInstance();
        fetchUserData();



        ProfilePage.setImageResource(setAvtarToList(data.avatarIndex));



        adapter = new RecycleViewAdapter(this, Userlist);
        UserListRecyclerView.setLayoutManager(new LinearLayoutManager(this));
        UserListRecyclerView.setAdapter(adapter);


        ProfilePage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this, ProfilePage.class);
                startActivity(intent);
            }
        });
        SettingPage.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(MainActivity.this,Setting_Page.class);
                startActivity(intent);
            }
        });

    }

    Integer setAvtarToList(int index){
        return profilePic.setAvtar(index);
    }


    public void fetchUserData() {
        firestore.collection("ChatGroup")
                .get()
                .addOnCompleteListener(new OnCompleteListener<QuerySnapshot>() {
                    @Override
                    public void onComplete(@NonNull Task<QuerySnapshot> task) {

                        for (QueryDocumentSnapshot document : task.getResult()) {
                            String username = document.getString("username");
                            int Users_Avtar = document.getLong("avatarIndex").intValue();
                            String documentId = document.getId();
                            String password = document.getString("password");

                            Userlist.add(new Recycle_List_Model(username,password,Users_Avtar,documentId));

                        }
                        for(int i=0;i<Userlist.size();i++){
                            if(Userlist.get(i).documentId.equals(data.documentId)){
                                Userlist.remove(i);
                            }
                        }
                        adapter.notifyItemInserted(Userlist.size());
                    }
                });
    }

    @Override
    public void onBackPressed() {
        super.finishAffinity();
    }
}