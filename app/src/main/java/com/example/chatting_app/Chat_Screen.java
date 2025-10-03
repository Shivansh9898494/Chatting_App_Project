

package com.example.chatting_app;

import static android.content.ContentValues.TAG;

import androidx.annotation.NonNull;
import androidx.appcompat.app.AppCompatActivity;
import androidx.cardview.widget.CardView;
import androidx.recyclerview.widget.LinearLayoutManager;
import androidx.recyclerview.widget.RecyclerView;

import android.annotation.SuppressLint;
import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.EditText;
import android.widget.ImageButton;
import android.widget.ImageView;
import android.widget.LinearLayout;
import android.widget.TextView;
import android.widget.Toast;

import com.example.chatting_app.R;

import com.google.android.gms.tasks.OnCompleteListener;
import com.google.android.gms.tasks.Task;
import com.google.firebase.Firebase;
import com.google.firebase.auth.FirebaseAuth;
import com.google.firebase.database.DataSnapshot;
import com.google.firebase.database.DatabaseError;
import com.google.firebase.database.DatabaseReference;
import com.google.firebase.database.FirebaseDatabase;
import com.google.firebase.database.ValueEventListener;
import com.google.firebase.firestore.FirebaseFirestore;

import java.util.ArrayList;
import java.util.Arrays;
import java.util.Date;



public class Chat_Screen extends AppCompatActivity {

    ImageButton Chat_Back;
    ImageView Chat_Avatar;
    TextView Chat_Username;
    RecyclerView Chat_RecyclerView;
    EditText Chat_Message;
    ImageButton Chat_Send;
    FirebaseDatabase firebaseDatabase;
    DatabaseReference databaseReference;
    ArrayList<Chat_Model> chatList = new ArrayList<>();
    Chat_RecyclerView_Adapter adapter;
    int myindex = 0;
    USER_LOGIN_DATA userLoginData = new USER_LOGIN_DATA(this);
    ContactModel data = new ContactModel();
    setProfilePic setProfilePic = new setProfilePic();
    String DataBaseID = "";
    Chat_Model chatModel;
    String Message;
    String Sender_Name;
    String Sender_documentID;
    int Sender_AvtarIndex;
    String Reciver_Name;
    String Reciver_documentID;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_chat_screen);
        data = userLoginData.getLoggedInUser();

        Chat_Back = findViewById(R.id.Chat_Back);
        Chat_Username = findViewById(R.id.Chat_Username);
        Chat_RecyclerView = findViewById(R.id.Chat_RecyclerView);
        Chat_Message = findViewById(R.id.Chat_Message);
        Chat_Send = findViewById(R.id.Chat_Send);
        Chat_Avatar = findViewById(R.id.Chat_Avatar);

        Chat_Back.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                Intent intent = new Intent(Chat_Screen.this,MainActivity.class);
                startActivity(intent);
            }
        });


        Intent intent = getIntent();

        Reciver_Name = intent.getStringExtra("username");
        int Reciver_AvtarIndex = intent.getIntExtra("avatar",0);
        Reciver_documentID = intent.getStringExtra("documentId");
        Sender_Name = data.username;
        Sender_documentID = data.documentId;
        Sender_AvtarIndex = data.avatarIndex;



        Integer intent_avatar = setProfilePic.setAvtar(Reciver_AvtarIndex);
        Chat_Avatar.setImageResource(intent_avatar);
        Chat_Username.setText(Reciver_Name);


        String docA = data.documentId;
        String docB = Reciver_documentID;

        String originalString =docA+docB;
        char[] charArray = originalString.toCharArray();
        Arrays.sort(charArray);
        DataBaseID = String.valueOf(charArray);


        addMessagesToArray();

        LinearLayoutManager linearLayoutManager = new LinearLayoutManager(this);
        linearLayoutManager.setStackFromEnd(true);
        Chat_RecyclerView.setLayoutManager(linearLayoutManager);
        adapter = new Chat_RecyclerView_Adapter(Chat_Screen.this,chatList);
        Chat_RecyclerView.setAdapter(adapter);


        Chat_Send.setOnClickListener(new View.OnClickListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onClick(View v) {

                String mymessage = Chat_Message.getText().toString();
                if(mymessage.isEmpty()){
                    Chat_Message.setError("Enter Message");
                }else{
                    chatModel = new Chat_Model(mymessage,Sender_Name,Sender_documentID,setProfilePic.setAvtar(Sender_AvtarIndex),Reciver_Name,Reciver_documentID);
                    chatList.add(chatModel);

                    adapter.notifyItemInserted(chatList.size()-1);
                    Chat_RecyclerView.scrollToPosition(chatList.size()-1);

                    Chat_Message.setText("");
                    AddMessage(mymessage,Sender_Name,Sender_documentID,Sender_AvtarIndex,Reciver_Name,Reciver_documentID);
                }
            }
        });

    }


    void AddMessage(String Message, String Sender_Name, String Sender_documentID, int Sender_AvtarIndex, String Reciver_Name, String Reciver_documentID) {
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        chatModel = new Chat_Model(Message,Sender_Name,Sender_documentID,Sender_AvtarIndex,Reciver_Name,Reciver_documentID);
        databaseReference.child(DataBaseID).push().setValue(chatModel);
    }



    public void addMessagesToArray(){
        firebaseDatabase = FirebaseDatabase.getInstance();
        databaseReference = firebaseDatabase.getReference();
        databaseReference = databaseReference.child(DataBaseID);
        databaseReference.addValueEventListener(new ValueEventListener() {
            @SuppressLint("NotifyDataSetChanged")
            @Override
            public void onDataChange(@NonNull DataSnapshot snapshot) {
                chatList.clear();
                for (DataSnapshot dataSnapshot:snapshot.getChildren()){
                    chatModel = dataSnapshot.getValue(Chat_Model.class);

                    chatList.add(new Chat_Model(chatModel.Message,chatModel.Sender_Name,chatModel.Sender_documentID,setProfilePic.setAvtar(chatModel.Sender_AvtarIndex),chatModel.Reciver_Name,chatModel.Reciver_documentID));

                    //chatList.add(chatModel);

                }
                adapter.notifyDataSetChanged();
                Chat_RecyclerView.scrollToPosition(chatList.size()-1);


            }

            @Override
            public void onCancelled(@NonNull DatabaseError error) {

            }
        });
    }

}

