package com.example.listnameapp;

import android.app.Application;
import android.arch.lifecycle.AndroidViewModel;
import android.arch.lifecycle.LiveData;
import android.util.Log;

import java.util.List;

public class UserViewModel extends AndroidViewModel {
    private LiveData<List<User>> mAllUsers;
    private UserRepository mUserRepository;

    public UserViewModel(Application application){
        super(application);
        mUserRepository = new UserRepository(application);
        mAllUsers = mUserRepository.getAllUsers();

    }

    public LiveData<List<User>> getAllUsers(){
        if(mAllUsers != null){
            List<User> users = mAllUsers.getValue();
            if (users != null){
                for(int i=0;i<=users.size();i++){
                    Log.i("users_vic", mAllUsers.getValue().get(i).getFirstName());
                }
            }
        }
        return mAllUsers;
    }
    public void insertUser(User user){
        mUserRepository.insert(user);
    }
}
