package com.example.logbook.DAOs;

import androidx.room.Dao;
import androidx.room.Insert;
import androidx.room.Query;

import com.example.logbook.models.Profile;

import java.util.List;

@Dao
public interface ProfileDAO {
    @Insert
    void insertProfile(Profile profile);
    @Query("SELECT * FROM profile")
    List<Profile> getListProfile();
}
