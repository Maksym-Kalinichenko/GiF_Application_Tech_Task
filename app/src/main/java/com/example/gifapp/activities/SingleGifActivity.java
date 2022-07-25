package com.example.gifapp.activities;

import androidx.appcompat.app.AppCompatActivity;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import com.bumptech.glide.Glide;
import com.example.gifapp.R;

public class SingleGifActivity extends AppCompatActivity {

    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.activity_single_gif);

        ImageView fView = findViewById(R.id.fullImg);
        Intent receiver = getIntent();
        String sourceUrl = receiver.getStringExtra("imageUrl");
        Glide.with(this).load(sourceUrl).into(fView);

    }
}