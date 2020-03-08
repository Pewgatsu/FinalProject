package com.example.mobileprogfinalproject;

import androidx.appcompat.app.AppCompatActivity;
import androidx.core.app.NotificationCompat;

import android.app.NotificationManager;
import android.app.PendingIntent;
import android.content.Intent;
import android.database.Cursor;
import android.graphics.BitmapFactory;
import android.net.Uri;
import android.os.Bundle;
import android.widget.Toast;

public class SplashScreen extends AppCompatActivity {

    private DatabaseHelper database;
    private int state;
    private Cursor res;


    @Override
    protected void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.splash_screen);

        database = new DatabaseHelper(this);

        //state = database.getSettings();


        Thread splash = new Thread(){

            @Override
            public void run(){
                try{
                    sleep(1000);
                    Intent intent = new Intent(getApplicationContext(),LoginPage.class);
                    startActivity(intent);
                    finish();
                }catch(Exception e){
                    e.printStackTrace();
                }
            }
        };
        splash.start();



    }




}
