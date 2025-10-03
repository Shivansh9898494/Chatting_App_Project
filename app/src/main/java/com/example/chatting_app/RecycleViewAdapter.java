package com.example.chatting_app;

import static android.content.ContentValues.TAG;

import android.annotation.SuppressLint;
import android.content.Context;
import android.content.Intent;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.ImageView;
import android.widget.RelativeLayout;
import android.widget.TextView;

import androidx.annotation.NonNull;
import androidx.recyclerview.widget.RecyclerView;

import java.util.ArrayList;
import java.util.Objects;

public class RecycleViewAdapter extends RecyclerView.Adapter<RecycleViewAdapter.ViewHolder> {

    Context context;
    ArrayList<Recycle_List_Model> Userlist;
    setProfilePic setProfilePic=new setProfilePic();

    public RecycleViewAdapter(Context context,ArrayList<Recycle_List_Model> Userlist) {
        this.context=context;
        this.Userlist=Userlist;
    }

    @NonNull
    @Override
    public ViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View view= LayoutInflater.from(context).inflate(R.layout.chat_user_layout,parent,false);
        ViewHolder holder=new ViewHolder(view);
        return holder;
    }

    @Override
    public void onBindViewHolder(@NonNull ViewHolder holder, @SuppressLint("RecyclerView") int position) {


            holder.UserName.setText(Userlist.get(position).username);
            Integer ab=setProfilePic.setAvtar(Userlist.get(position).avatarIndex);
            holder.UserAvtar.setImageResource(ab);
            holder.User.setOnClickListener(new View.OnClickListener() {
                @Override
                public void onClick(View v) {
                    Intent intent=new Intent(context,Chat_Screen.class);
                    intent.putExtra("username",Userlist.get(position).username);
                    intent.putExtra("avatar",Userlist.get(position).avatarIndex);
                    intent.putExtra("documentId",Userlist.get(position).documentId);
                    context.startActivity(intent);
                }
            });



    }

    @Override
    public int getItemCount() {
        return Userlist.size();
    }
    public class ViewHolder extends RecyclerView.ViewHolder {
        TextView UserName;
        ImageView UserAvtar;
        RelativeLayout User;
        public ViewHolder(@NonNull View itemView) {
            super(itemView);
            UserName=itemView.findViewById(R.id.UserName_HomePage);
            UserAvtar=itemView.findViewById(R.id.UserAvtar_HomePage);
            User=itemView.findViewById(R.id.User);

        }
    }
}
