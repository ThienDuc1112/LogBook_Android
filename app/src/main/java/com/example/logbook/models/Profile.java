package com.example.logbook.models;

import androidx.room.Entity;
import androidx.room.PrimaryKey;

@Entity(tableName = "profile")
public class Profile {
    @PrimaryKey(autoGenerate = true)
    public int id;
    public String name;
    public String dob;
    public String email;
    public int idImage;

    public Profile(String name, String dob, String email, int idImage) {
        this.name = name;
        this.dob = dob;
        this.email = email;
        this.idImage = idImage;
    }
    public Profile(){}

    public int getId() {
        return id;
    }

    public void setId(int id) {
        this.id = id;
    }

    public String getName() {
        return name;
    }

    public void setName(String name) {
        this.name = name;
    }

    public String getDob() {
        return dob;
    }

    public void setDob(String dob) {
        this.dob = dob;
    }

    public String getEmail() {
        return email;
    }

    public void setEmail(String email) {
        this.email = email;
    }

    public int getIdImage() {
        return idImage;
    }

    public void setIdImage(int idImage) {
        this.idImage = idImage;
    }
}
