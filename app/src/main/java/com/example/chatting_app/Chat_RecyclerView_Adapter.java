package com.example.chatting_app;


import static android.content.ContentValues.TAG;

import android.app.AlertDialog;
import android.content.Context;
import android.content.DialogInterface;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import com.example.chatting_app.Chat_Model;
import com.example.chatting_app.ContactModel;
import com.example.chatting_app.R;
import com.example.chatting_app.USER_LOGIN_DATA;
import com.google.firebase.auth.FirebaseAuth;


import java.util.ArrayList;



public class Chat_RecyclerView_Adapter extends RecyclerView.Adapter {
    Context context;
    ArrayList<Chat_Model> messagesAdpterArrayList;
    int ITEM_SEND=1;
    int ITEM_RECIVE=2;

    public Chat_RecyclerView_Adapter(Context context, ArrayList<Chat_Model> messagesAdpterArrayList) {
        this.context = context;
        this.messagesAdpterArrayList = messagesAdpterArrayList;
    }

    @NonNull
    @Override
    public RecyclerView.ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        if (viewType == ITEM_SEND){
            View view = LayoutInflater.from(context).inflate(R.layout.sender_layout, parent, false);
            return new senderVierwHolder(view);
        }else {
            View view = LayoutInflater.from(context).inflate(R.layout.reciver_layout, parent, false);
            return new reciverViewHolder(view);
        }

    }

    @Override
    public void onBindViewHolder(@NonNull RecyclerView.ViewHolder holder, int position) {
        Chat_Model messages = messagesAdpterArrayList.get(position);
        setProfilePic setProfilePic = new setProfilePic();

        if (holder.getClass()==senderVierwHolder.class){
            senderVierwHolder viewHolder = (senderVierwHolder) holder;
            viewHolder.msgtxt.setText(messages.getMessage());
            viewHolder.avtar.setImageResource(messagesAdpterArrayList.get(position).Sender_AvtarIndex);

            //Picasso.get().load(senderImg).into(viewHolder.circleImageView);
        }else { reciverViewHolder viewHolder = (reciverViewHolder) holder;
            viewHolder.msgtxt.setText(messages.getMessage());
            viewHolder.avtar.setImageResource(messagesAdpterArrayList.get(position).Sender_AvtarIndex);


        }
    }

    @Override
    public int getItemCount() {
        return messagesAdpterArrayList.size();
    }

    @Override
    public int getItemViewType(int position) {
        USER_LOGIN_DATA userLoginData = new USER_LOGIN_DATA(context);
        ContactModel data = new ContactModel();
        data = userLoginData.getLoggedInUser();
        String userid = data.documentId;
        Chat_Model messages = messagesAdpterArrayList.get(position);
        if (userid.equals(messages.getSender_documentID())) {
            return ITEM_SEND;
        } else {
            return ITEM_RECIVE;
        }
    }

    class  senderVierwHolder extends RecyclerView.ViewHolder {

        TextView msgtxt;
        ImageView avtar;
        public senderVierwHolder(@NonNull View itemView) {
            super(itemView);

            msgtxt = itemView.findViewById(R.id.Message_ChatPage);
            avtar = itemView.findViewById(R.id.UserAvtar_ChatPage);



        }
    }
    class reciverViewHolder extends RecyclerView.ViewHolder {
        TextView msgtxt;
        ImageView avtar;
        public reciverViewHolder(@NonNull View itemView) {
            super(itemView);

            msgtxt = itemView.findViewById(R.id.Message_ChatPage);
            avtar = itemView.findViewById(R.id.UserAvtar_ChatPage);

        }
    }
}