package com.example.logbook;

import androidx.appcompat.app.AppCompatActivity;

import android.os.Bundle;
import android.view.View;
import android.widget.Button;
import android.widget.ImageView;

public class LogBook2 extends AppCompatActivity {

    private ImageView imageView;
    private Button btnPrevious, btnNext;
    private int currentIndex = 0;
    private int[] imageResources = {R.drawable.amime, R.drawable.chainsaw, R.drawable.beastars};
    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_log_book2);

        imageView = findViewById(R.id.imageView);
        btnNext = findViewById(R.id.btnNext);
        btnPrevious = findViewById(R.id.btnPrevious);
        updateImage();

        btnPrevious.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentIndex == 0){
                    currentIndex = imageResources.length - 1;
                }else{
                    currentIndex -= 1;
                }
                updateImage();
            }
        });
        btnNext.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View view) {
                if(currentIndex == imageResources.length - 1){
                    currentIndex = 0;
                }else{
                    currentIndex += 1;
                }
                updateImage();
            }
        });

    }

    private void updateImage(){
        imageView.setImageResource(imageResources[currentIndex]);
    }
}