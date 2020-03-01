package com.example.mobileprogfinalproject;

import android.app.ActionBar;
import android.content.Intent;
import android.graphics.Typeface;
import android.os.Bundle;
import android.view.LayoutInflater;
import android.view.View;
import android.view.ViewGroup;
import android.widget.RelativeLayout;
import android.widget.TextView;
import android.widget.Toast;

import androidx.annotation.Nullable;
import androidx.appcompat.app.AppCompatActivity;
import androidx.fragment.app.Fragment;
import androidx.viewpager.widget.ViewPager;

import com.google.android.material.floatingactionbutton.FloatingActionButton;


public class SettingsFragment extends Fragment {

   private TextView notifications, general, account, help, about;
   private Typeface arialFont;
   private FloatingActionButton floatingActionButton;
   private RelativeLayout accountContainer;
   private Intent intent;
   private int accountID;




    @Nullable
    @Override
    public View onCreateView(LayoutInflater inflater, @Nullable ViewGroup container, @Nullable Bundle savedInstanceState){

        return inflater.inflate(R.layout.fragment_settings, container, false);
    }

    @Override
    public void onViewCreated(View view, Bundle savedInstanceState){


        initializeWidgets();
//        arialFont = Typeface.createFromAsset(getActivity().getAssets(), "fonts/arial.ttf");
//        setTypeFace();

        accountContainer.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                intent = new Intent(getActivity(),AccountPage.class);
                intent.putExtra("ACCOUNT_ID",accountID);
                startActivity(intent);
            }
        });
        if (floatingActionButton != null) {
            floatingActionButton.hide();
        }



    }


    private void setTypeFace(){
        notifications.setTypeface(arialFont);
        general.setTypeface(arialFont);
        account.setTypeface(arialFont);
        help.setTypeface(arialFont);
        about.setTypeface(arialFont);

    }

    private void initializeWidgets(){

//        notifications = getView().findViewById(R.id.notificationsText);
//        general = getView().findViewById(R.id.generalText);
//        account = getView().findViewById(R.id.accountText);
//        help = getView().findViewById(R.id.helpText);
//        about = getView().findViewById(R.id.aboutText);
        accountID = ((HomePage) getActivity()).retrieveAccountID();
        floatingActionButton = ((HomePage) getActivity()).getFloatingActionButton();
        accountContainer = getActivity().findViewById(R.id.accountContainer);

    }


}
