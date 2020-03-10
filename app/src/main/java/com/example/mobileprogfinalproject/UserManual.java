package com.example.mobileprogfinalproject;

import android.os.Bundle;

import androidx.appcompat.app.AppCompatActivity;

public class UserManual extends AppCompatActivity {

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_user_manual);


        getSupportActionBar().setTitle(" ");
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return  true;
    }
}
