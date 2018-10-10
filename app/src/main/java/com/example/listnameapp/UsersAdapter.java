package com.example.listnameapp;

import android.content.Context;
import android.support.annotation.NonNull;
import android.support.v7.widget.RecyclerView;
import android.util.Log;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import java.util.List;

public class UsersAdapter extends RecyclerView.Adapter<UsersAdapter.UsersViewHolder> {

    private final LayoutInflater inflater;
    private List<User> mUsers;

    public UsersAdapter(Context context){
        inflater = LayoutInflater.from(context);
    }

    public class UsersViewHolder extends RecyclerView.ViewHolder{
        private TextView first_name;
        public UsersViewHolder(View itemView){
            super(itemView);
            first_name = itemView.findViewById(R.id.first_name_text);
        }
        public void bindUser(User user){
            first_name.setText(user.getFullName());
        }

    }

    public void setmUsers(List<User> mUsers) {
        this.mUsers = mUsers;
        notifyDataSetChanged();
    }

    @NonNull
    @Override
    public UsersViewHolder onCreateViewHolder(@NonNull ViewGroup parent, int viewType) {
        View itemView = inflater.inflate(R.layout.user_item, parent, false);
        return new UsersViewHolder(itemView);
    }

    @Override
    public void onBindViewHolder(@NonNull UsersViewHolder holder, int position) {
        User user = mUsers.get(position);
        holder.bindUser(user);
        Log.i("users_vic", user.getFullName());
    }

    @Override
    public int getItemCount() {
        if(mUsers != null){
            return mUsers.size();
        }
        return 0;
    }
}
