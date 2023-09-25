package com.example.logbook;

import android.content.Context;

import androidx.room.Database;
import androidx.room.Room;
import androidx.room.RoomDatabase;

import com.example.logbook.DAOs.ProfileDAO;
import com.example.logbook.models.Profile;

@Database(entities = {Profile.class}, version = 1)
public abstract class ProfileDatabase extends RoomDatabase {
    private static final String DATABASE_NAME = "profileUserDatabase";
    private static ProfileDatabase instance;

    public static synchronized ProfileDatabase getInstance(Context context){
        if(instance == null){
            instance = Room.databaseBuilder(context.getApplicationContext(),ProfileDatabase.class,DATABASE_NAME)
                    .allowMainThreadQueries()
                    .build();
        }
        return instance;
    }

    public abstract ProfileDAO profileDAO();
}
