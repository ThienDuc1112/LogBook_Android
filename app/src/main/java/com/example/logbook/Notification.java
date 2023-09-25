package com.example.logbook;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.os.Bundle;

import java.util.Objects;

public class Notification extends AppCompatActivity {

    private Toolbar myToolbar;
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_notification);

        myToolbar = findViewById(R.id.toolBarMessage);
        setSupportActionBar(myToolbar);
        Objects.requireNonNull(getSupportActionBar()).setTitle("Contact Database");
        myToolbar.setTitleTextColor(getColor(R.color.purple));

        new AlertDialog.Builder(Notification.this)
                .setTitle("Success")
                .setMessage("New Contact is saved")
                .setPositiveButton("OK", new DialogInterface.OnClickListener() {
                    @Override
                    public void onClick(DialogInterface dialog, int which) {
                        Intent intent = new Intent(Notification.this, LogBook3.class);
                        startActivity(intent);
                    }
                })
                .show();
    }
}