package com.example.mobileprogfinalproject;

import android.accounts.Account;
import android.app.AlertDialog;
import android.content.DialogInterface;
import android.content.Intent;
import android.database.Cursor;
import android.os.Bundle;
import android.util.Log;
import android.view.View;
import android.widget.Button;
import android.widget.TextView;
import android.widget.Toast;

import androidx.appcompat.app.AppCompatActivity;

public class AccountPage extends AppCompatActivity {

    private Button deleteAccount;
    private DatabaseHelper database;
    private Accounts account;
    private Intent intent;
    private Bundle extras;
    private int accountID;
    private TextView username, fullname, email;
    private Cursor res;
    private AlertDialog.Builder confirmDeleteBuilder;
    private AlertDialog confirmDelete;

    public void onCreate(Bundle savedInstanceState){
        super.onCreate(savedInstanceState);
        setContentView(R.layout.page_account);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);
        getSupportActionBar().setTitle(" ");


        deleteAccount = findViewById(R.id.deleteAccountButton);


        database = new DatabaseHelper(this);
        initializeWidgets();
        getAccountID();
        getDetails();

        account = new Accounts(accountID);





        deleteAccount.setOnClickListener(new View.OnClickListener() {
            @Override
            public void onClick(View v) {
                delete();

            }
        });
    }

    private void getAccountID(){
        extras = getIntent().getExtras();
        if(extras != null){
            accountID = extras.getInt("ACCOUNT_ID");
        }
    }

    private void initializeWidgets(){
        username = findViewById(R.id.profileUsernameField);
        fullname = findViewById(R.id.profileFullnameField);
        email = findViewById(R.id.profileEmailField);
    }



    private void getDetails(){
        res = database.getProfileDetails();


        while(res.moveToNext()){
            fullname.setText(res.getString(0));
            username.setText(res.getString(1));
            email.setText(res.getString(2));
        }
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return  true;
    }

    private void delete(){
        confirmDeleteBuilder = new AlertDialog.Builder(this);
        confirmDeleteBuilder.setMessage("Are you sure you want to delete this account?");
        confirmDeleteBuilder.setNegativeButton("No", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {

            }
        });

        confirmDeleteBuilder.setPositiveButton("Yes", new DialogInterface.OnClickListener() {
            @Override
            public void onClick(DialogInterface dialog, int which) {
                database.deleteAccount(accountID);
                intent = new Intent(getApplicationContext(),LoginPage.class);
                startActivity(intent);
                finish();
            }
        });

        confirmDelete = confirmDeleteBuilder.create();
        confirmDelete.show();
    }
}
