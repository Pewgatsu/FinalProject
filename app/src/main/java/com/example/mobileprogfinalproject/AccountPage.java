package com.example.mobileprogfinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AccountPage extends AppCompatActivity {

    private Button deleteAccount;

    private DatabaseHelper database;
    private Intent intent;
    private Bundle extras;
    private int accountID;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_account);

        deleteAccount = findViewById(R.id.deleteAccountButton);


        database = new DatabaseHelper(this);

        getAccountID();




        deleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                database.deleteAccount(accountID);
                intent = new Intent(getApplicationContext(),LoginPage.class);
                startActivity(intent);

            }
        });
    }

    private void getAccountID(){
        extras = getIntent().getExtras();
        if(extras != null){
            accountID = extras.getInt("ACCOUNT_ID");
        }
    }
}
