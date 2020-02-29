package com.example.mobileprogfinalproject;

import android.content.Intent;
import android.os.Bundle;
import android.view.Menu;
import android.view.MenuInflater;
import android.view.MenuItem;
import android.widget.EditText;
import android.widget.ImageView;

import androidx.appcompat.app.AppCompatActivity;
import androidx.appcompat.widget.Toolbar;

import com.google.android.material.textfield.TextInputEditText;
import com.google.android.material.textfield.TextInputLayout;

public class DetailsPage extends AppCompatActivity {

    private Toolbar toolbar;
    private DatabaseHelper database;
    private Passwords passwords;
    private Intent intent;
    private int id;
    private EditText titleField, accountField, usernameField, websiteField, notesField;
    private TextInputLayout passwordField;


    public void onCreate(Bundle saveInstanceState) {
        super.onCreate(saveInstanceState);
        setContentView(R.layout.app_bar_details);

        initializeWidgets();
        setSupportActionBar(toolbar);
        getSupportActionBar().setDisplayHomeAsUpEnabled(true);



        getAccountID();

        database = new DatabaseHelper(this);
        passwords = database.getPasswords(id);

        getSupportActionBar().setTitle(passwords.getTitle());
        setStrings();
        disableFields();
    }



    private void getAccountID(){
        intent = getIntent();
        id = intent.getIntExtra("ID",0);
    }


    private void disableFields(){
        titleField.setKeyListener(null);
        accountField.setKeyListener(null);
        usernameField.setKeyListener(null);
        passwordField.getEditText().setKeyListener(null);
        websiteField.setKeyListener(null);
        notesField.setKeyListener(null);
    }

    private void setStrings(){
        titleField.setText(passwords.getTitle());
        accountField.setText(passwords.getAccount());
        usernameField.setText(passwords.getUsername());
        passwordField.getEditText().setText(passwords.getPassword());
        websiteField.setText(passwords.getWebsite());
        notesField.setText(passwords.getNotes());
    }

    private void initializeWidgets(){


        toolbar = findViewById(R.id.detailsToolbar);


        titleField = findViewById(R.id.detailsTitleField);
        accountField = findViewById(R.id.detailsAccountField);
        usernameField = findViewById(R.id.detailsUsernameField);
        passwordField = findViewById(R.id.detailsPasswordField);
        websiteField = findViewById(R.id.detailsWebsiteField);
        notesField = findViewById(R.id.detailsNotesField);
    }

    @Override
    public boolean onCreateOptionsMenu(Menu menu) {
        MenuInflater inflater = getMenuInflater();
        inflater.inflate(R.menu.edit_menu,menu);
        return true;
    }

    @Override
    public boolean onOptionsItemSelected(MenuItem item) {
        if(item.getItemId() == R.id.edit){
            intent = new Intent(this, EditPage.class);
            intent.putExtra("ID",id);
            startActivity(intent);
            finish();
        }
        return super.onOptionsItemSelected(item);
    }

    @Override
    public boolean onSupportNavigateUp(){
        finish();
        return  true;
    }

    @Override
    public void onBackPressed() {
        super.onBackPressed();
    }
}
