package com.example.mobileprogfinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;

import com.google.android.material.textfield.TextInputLayout;

public class DetailsPage extends AppCompatActivity {

    private DatabaseHelper database;

    private Passwords passwords;
    private Intent intent;
    private int id;
    private TextInputLayout titleField, accountField, usernameField, passwordField, websiteField, notesField;
    private ImageView generateButton;


    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.page_details);

        initializeWidgets();
        setStrings();
        getAccountID();


        database = new DatabaseHelper(this);
        passwords = database.getPasswords(id);

        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



    }



    private void getAccountID(){
        intent = getIntent();
        id = intent.getIntExtra("ID",0);
    }

    private void setStrings(){

        titleField.getEditText().setText(passwords.getTitle());
        accountField.getEditText().setText(passwords.getAccount());
        usernameField.getEditText().setText(passwords.getUsername());
        passwordField.getEditText().setText(passwords.getPassword());
        websiteField.getEditText().setText(passwords.getWebsite());
        notesField.getEditText().setText(passwords.getNotes());
    }

    private void initializeWidgets(){
        generateButton = findViewById(R.id.detailsGenerateButton);

        titleField = findViewById(R.id.detailsTitleField);
        accountField = findViewById(R.id.detailsAccountField);
        usernameField = findViewById(R.id.detailsUsernameField);
        passwordField = findViewById(R.id.detailsPasswordField);
        websiteField = findViewById(R.id.detailsWebsiteField);
        notesField = findViewById(R.id.detailsNotesField);
    }


    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
