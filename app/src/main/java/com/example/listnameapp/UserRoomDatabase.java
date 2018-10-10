package com.example.listnameapp;

import android.arch.persistence.db.SupportSQLiteDatabase;
import android.arch.persistence.room.Database;
import android.arch.persistence.room.Room;
import android.arch.persistence.room.RoomDatabase;
import android.content.Context;
import android.os.AsyncTask;
import android.support.annotation.NonNull;
import android.util.Log;

@Database(entities = {User.class}, version = 1)
public abstract class UserRoomDatabase extends RoomDatabase{

    public abstract UserDao userDao();

    private static volatile UserRoomDatabase INSTANCE;

    static UserRoomDatabase getDatabase(final Context context){
        if (INSTANCE == null){
            synchronized (UserRoomDatabase.class){
                if (INSTANCE == null){
                    /* create db instance */
                    INSTANCE = Room.databaseBuilder(
                            context, UserRoomDatabase.class, "app_db"
                    ).addCallback(dbCallback).build();
                }
            }
        }
        return INSTANCE;
    }
    private static RoomDatabase.Callback dbCallback = new RoomDatabase.Callback(){

        @Override
        public void onOpen(@NonNull SupportSQLiteDatabase db) {
            super.onOpen(db);
            new PrepopulateDbAsync(INSTANCE).execute();


        }
    };
    private static class PrepopulateDbAsync extends AsyncTask<Void, Void, Void>{
        private UserDao mUserdao;

        PrepopulateDbAsync(UserRoomDatabase db){
            mUserdao = db.userDao();
        }

        @Override
        protected Void doInBackground(Void... voids) {
            User user = new User("Victor", "Ngeno");
            Log.i("users_vic", user.getFirstName());
            mUserdao.insert(user);
            return null;
        }
    }

}
