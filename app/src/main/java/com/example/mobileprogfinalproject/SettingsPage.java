package com.example.mobileprogfinalproject;

import android.app.ActionBar;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.TextView;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class SettingsPage extends AppCompatActivity {

   private TextView notifications, general, account, help, about;
   private Typeface arialFont;
   private FloatingActionButton floatingActionButton;


    public void onCreate(Bundle savedInstanceState) {
        super.onCreate(savedInstanceState);
        setContentView(R.layout.fragment_settings);

        initializeWidgets();



    }






    private void setTypeFace(){
        notifications.setTypeface(arialFont);
        general.setTypeface(arialFont);
        account.setTypeface(arialFont);
        help.setTypeface(arialFont);
        about.setTypeface(arialFont);

    }

    private void initializeWidgets(){




    }


}
