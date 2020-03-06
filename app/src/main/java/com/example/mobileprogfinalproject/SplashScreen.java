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

    }


    public void sendNotification() {
        NotificationCompat.Builder builder = new NotificationCompat.Builder(this);
        builder.setSmallIcon(R.drawable.app_icon_test);
        Intent intent = new Intent(this, LoginPage.class);
        PendingIntent pendingIntent = PendingIntent.getActivity(this, 0, intent, 0);
        builder.setContentIntent(pendingIntent);
        builder.setContentTitle("Notifications Title");
        builder.setContentText("Password Manager is Running.");
        builder.setSubText("Tap to open App.");
        NotificationManager notificationManager = (NotificationManager) getSystemService(NOTIFICATION_SERVICE);

        // Will display the notification in the notification bar
        notificationManager.notify(1, builder.build());
    }

    public int retrieveSettings(){
        database = new DatabaseHelper(this);
        res = database.getSettings();
        state = res.getInt(1);

        return state;
    }
}
