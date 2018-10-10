package com.example.listnameapp;

import android.app.Application;
import android.arch.lifecycle.LiveData;
import android.os.AsyncTask;

import java.util.List;

public class UserRepository {
    private UserDao mUserDao;
    private LiveData<List<User>> mAllUsers;

    UserRepository(Application application){
        UserRoomDatabase db = UserRoomDatabase.getDatabase(application.getApplicationContext());
        mUserDao = db.userDao();
        mAllUsers = mUserDao.getAllUsers();
    }

    public LiveData<List<User>> getAllUsers() {
        return mAllUsers;
    }

    public void insert(User user){
        new insertAsyncTask(mUserDao).execute(user);
    }

    private static class insertAsyncTask extends AsyncTask<User, Void, Void>{
        private UserDao mAsyncTaskDao;

        insertAsyncTask(UserDao userDao){
            mAsyncTaskDao = userDao;
        }

        @Override
        protected Void doInBackground(User... params) {
            mAsyncTaskDao.insert(params[0]);
            return null;
        }
    }
}
